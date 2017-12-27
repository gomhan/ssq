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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lottery.function.shortterm.Function3;
import lottery.function.shortterm.Function3.OddEvenNumber;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

public class Func3InTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;

	class Func3TableModel extends AbstractTableModel {

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
				return "奇数";
			case 3:
				return "偶数 ";
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
			OddEvenNumber b = number.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return b.getIssue();
			case 1:
				return b.getNumberString();
			case 2:
				return b.getOddNumber();
			case 3:
				return b.getEvenNumber();
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
	List<Function3.OddEvenNumber> number;
	int totalOddCount;
	int totalEvenCount;

	public Func3InTableFrame(List<OddEvenNumber> number, int issue) {
		// TODO Auto-generated constructor stub
		this.number = new ArrayList<OddEvenNumber>(number);
		setTitle("奇偶偏差_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func3TableModel model = new Func3TableModel();
		table = new DefaultTable();
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		TableRowSorter<Func3TableModel> sorter = new TableRowSorter<Func3TableModel>(
				model);
		sorter.setSortable(0, false);
		sorter.setSortable(1, false);
		table.setRowSorter(sorter);
		
		for (OddEvenNumber oe : number) {
			totalEvenCount += oe.getEvenNumber();
			totalOddCount += oe.getOddNumber();
		}

		l1 = new JLabel("奇数个数: " + totalOddCount);
		l2 = new JLabel("偶数个数: " + totalEvenCount);
		l3 = new JLabel("奇偶偏差： " + getTotalString());
	}
	
	private String getTotalString() {
		if (totalOddCount > totalEvenCount) {
			return "O = +"+(totalOddCount - totalEvenCount);
		} else if (totalOddCount < totalEvenCount){
			return "E = +"+(totalEvenCount - totalOddCount);
		} else {
			return "E = O = +0";
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
