package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lottery.function.shortterm.NumberAreaDeviationStatistic.Area;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

public class NumberAreaInTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;

	class Func6TableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8975416585186406217L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return areas.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return issue + 1;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			if (column == 0) {
				return "数字区间";
			} else {
				return areas.get(0).getIssue()[column - 1];
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return getValueAt(0, columnIndex).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			Area b = areas.get(rowIndex);
			if (columnIndex == 0) {
				return b.getAreaName();
			} else {
				int count = b.getNumberCount()[columnIndex - 1];
				if (count > 0) {
					return String.valueOf(count);
				} else {
					return "-";
				}
			}
		}
	}

	JTable table;
	List<Area> areas;
	int issue;

	public NumberAreaInTableFrame(List<Area> number, int issue) {
		// TODO Auto-generated constructor stub
		this.areas = new ArrayList<Area>(number);
		this.issue = issue;
		setTitle("数字区间偏差_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func6TableModel model = new Func6TableModel();
		table = new DefaultTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer(){
			@Override
			protected void customizeRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				// TODO Auto-generated method stub
				if (value.equals("-")) {
					setBackground(Color.lightGray);
				}
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setMinWidth(100);
		}

		TableRowSorter<Func6TableModel> sorter = new TableRowSorter<Func6TableModel>(
				model);
		sorter.setSortable(0, false);
		table.setRowSorter(null);
	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);

		JLabel label = new JLabel("最有可能在某一起中奖号码中出现的数字区间，是那些在过去5期至10期中最不活跃的数字区间。");
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		getContentPane().add(jsp, BorderLayout.CENTER);
		getContentPane().add(label, BorderLayout.SOUTH);
	}

	public void display() {
		setSize(1024, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
