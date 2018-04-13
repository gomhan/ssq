package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import lottery.function.common.WinningNumberStatistic;
import lottery.function.common.WinningNumberStatistic.Ball;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;

public class WinningNumberInTableFrame extends JFrame {
	private static final long serialVersionUID = 7149667399562379506L;

	class Func2TableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1461215894264886120L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return balls.size();
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
				return "红球号";
			case 1:
				return "中奖次数";
			case 2:
				return "名次";
			case 3:
				return "比例(%)";
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
			Ball b = balls.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return b.getNumber();
			case 1:
				return b.getCount();
			case 2:
				return b.getPosition();
			case 3:
				return b.getScale();
			default:
				break;
			}
			return null;
		}
	}

	JTable table;
	List<WinningNumberStatistic.Ball> balls;

	public WinningNumberInTableFrame(List<Ball> balls, int issue) {
		// TODO Auto-generated constructor stub
		this.balls = new ArrayList<Ball>(balls);
		setTitle("中奖数字情况表_[" + issue + "期]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		Func2TableModel model = new Func2TableModel();
		table = new DefaultTable();
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			@Override
			protected void customizeUnSelectedRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				if (column == 2) {
					setForeground(Color.RED);
				}
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		TableRowSorter<Func2TableModel> sorter = new TableRowSorter<Func2TableModel>(
				model);
		sorter.setSortable(3, false); // do not sort scale
		table.setRowSorter(sorter);

	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		getContentPane().add(jsp, BorderLayout.CENTER);
	}

	public void display() {
		setSize(400, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
