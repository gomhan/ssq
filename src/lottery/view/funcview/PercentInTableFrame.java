package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lottery.function.shortterm.PercentDeviationStatistic.PercentNumber;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

public class PercentInTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;

	class Func10TableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8975416585186406217L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return percentNumber.size();
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
				return "奖球";
			case 1:
				return "前5期中奖情况";
			case 2:
				return "后5期中奖情况";
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
			PercentNumber n = percentNumber.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return n.getBall();
			case 1:
				int size = n.getInitial5Issue().size();
				String issue = Arrays.toString(n.getInitial5Issue().toArray(
						new String[0]));
				return size + " - " + issue;
			case 2:
				size = n.getLast5Issue().size();
				issue = Arrays.toString(n.getLast5Issue()
						.toArray(new String[0]));
				return size + " - " + issue;
			default:
				return null;
			}
		}
	}

	JTable table;
	List<PercentNumber> percentNumber;
	int issue;

	public PercentInTableFrame(List<PercentNumber> percentNumber, int issue) {
		// TODO Auto-generated constructor stub
		this.percentNumber = new ArrayList<PercentNumber>(percentNumber);
		Collections.sort(this.percentNumber);
		this.issue = issue;
		setTitle("百分比偏差_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func10TableModel model = new Func10TableModel();
		table = new DefaultTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			final Color c = new Color(219, 252, 173);

			@Override
			protected void customizeUnSelectedRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				// TODO Auto-generated method stub
				PercentNumber n = percentNumber.get(row);
				if (n.isInitial5() && n.isLast5()) {
					setBackground(c);
				} else if (!n.isInitial5() && !n.isLast5()) {
					setBackground(Color.LIGHT_GRAY);
				}
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setMinWidth(200);
		}

		TableRowSorter<Func10TableModel> sorter = new TableRowSorter<Func10TableModel>(
				model);
		sorter.setSortable(1, false);
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
