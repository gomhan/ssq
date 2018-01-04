package lottery.view.funcview;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lottery.function.shortterm.LastPlaceDeviationStatistic.LastNumber;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

public class LastPlaceInTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;

	class Func9TableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8975416585186406217L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return lastNumber.size();
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
				return "末位数字";
			case 1:
				return "出现次数";
			case 2:
				return "奖球";
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
			LastNumber n = lastNumber.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return n.getLastNumber();
			case 1:
				return n.getCount();
			case 2:
				return Arrays.toString(n.getNumbers().toArray(new Integer[0]));
			default:
				return null;
			}
		}
	}

	JTable table;
	List<LastNumber> lastNumber;
	int issue;

	public LastPlaceInTableFrame(List<LastNumber> lastNumber, int issue) {
		// TODO Auto-generated constructor stub
		this.lastNumber = new ArrayList<LastNumber>(lastNumber);
		Collections.sort(this.lastNumber);
		this.issue = issue;
		setTitle("末位数偏差_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func9TableModel model = new Func9TableModel();
		table = new DefaultTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setMinWidth((i + 1) * 70);
		}

		TableRowSorter<Func9TableModel> sorter = new TableRowSorter<Func9TableModel>(
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
