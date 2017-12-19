package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import lottery.function.shortterm.Function4.LowHighNumber;
import lottery.view.renderer.LotteryTableRenderer;

public class Func4InTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;

	class Func4TableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8975416585186406217L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return number.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			switch (column) {
			case 0:
				return "期号";
			case 1:
				return "奖球";
			case 2:
				return "小数";
			case 3:
				return "大数 ";
			default:
				break;
			}
			return super.getColumnName(column);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			LowHighNumber b = number.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return b.getIssue();
			case 1:
				return b.getNumberString();
			case 2:
				return b.getLowNumber();
			case 3:
				return b.getHighNumber();
			default:
				break;
			}
			return null;
		}
	}

	JTable table;
	JLabel l3;
	JLabel l1;
	JLabel l2;
	List<LowHighNumber> number;
	int totalLowCount;
	int totalHighCount;

	public Func4InTableFrame(List<LowHighNumber> number, int issue) {
		// TODO Auto-generated constructor stub
		this.number = new ArrayList<LowHighNumber>(number);
		setTitle("大小偏差_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func4TableModel model = new Func4TableModel();
		table = new JTable();
		table.setModel(model);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		LotteryTableRenderer renderer = new LotteryTableRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		table.getTableHeader().setReorderingAllowed(false);

		TableRowSorter<Func4TableModel> sorter = new TableRowSorter<Func4TableModel>(
				model);
		sorter.setSortable(0, false);
		sorter.setSortable(1, false);
		table.setRowSorter(sorter);

		for (LowHighNumber oe : number) {
			totalHighCount += oe.getHighNumber();
			totalLowCount += oe.getLowNumber();
		}

		l1 = new JLabel("小数个数: " + totalLowCount);
		l2 = new JLabel("大数个数: " + totalHighCount);
		l3 = new JLabel("大小偏差： " + getTotalString());
	}

	private String getTotalString() {
		if (totalLowCount > totalHighCount) {
			return "L = +" + (totalLowCount - totalHighCount);
		} else if (totalLowCount < totalHighCount) {
			return "H = +" + (totalHighCount - totalLowCount);
		} else {
			return "H = L = +0";
		}
	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);

		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(l1);
		p.add(Box.createHorizontalStrut(20));
		p.add(l2);
		p.add(Box.createHorizontalStrut(20));
		p.add(l3);

		getContentPane().add(jsp, BorderLayout.CENTER);
		getContentPane().add(p, BorderLayout.SOUTH);
	}

	public void display() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
