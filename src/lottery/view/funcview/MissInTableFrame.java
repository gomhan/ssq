package lottery.view.funcview;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lottery.function.shortterm.MissDeviationStatistic.MissNumber;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

public class MissInTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;

	class Func8TableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8975416585186406217L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return missNumbers.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			switch (column) {
			case 0:
				return "遗漏次数";
			case 1:
				return "符合遗漏次数的数字个数";
			case 2:
				return "可能的数字";
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
			MissNumber p = missNumbers.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return p.getMissCount();
			case 1:
				return p.getMissNumberCount();
			case 2:
				return Arrays.toString(p.getPossibleNumber().toArray(
						new Byte[0]));
			default:
				return null;
			}
		}
	}

	JTable table;
	List<MissNumber> missNumbers;
	int issue;
	int averageHotCount;
	int averageMissCount;

	public MissInTableFrame(List<MissNumber> missNumbers, int issue) {
		// TODO Auto-generated constructor stub
		this.missNumbers = new ArrayList<MissNumber>(missNumbers);
		this.issue = issue;
		setTitle("遗漏偏差_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func8TableModel model = new Func8TableModel();
		table = new DefaultTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setMinWidth(150);
		}

		TableRowSorter<Func8TableModel> sorter = new TableRowSorter<Func8TableModel>(
				model);
		sorter.setSortable(2, false);
		table.setRowSorter(sorter);
	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		getContentPane().add(jsp, BorderLayout.CENTER);
	}

	public void display() {
		setSize(1024, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
