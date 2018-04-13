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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import lottery.function.midterm.WinIntervalIssueStatistic.IntervalIssue;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;
import table.header.mtx.MultiplexHeader;
import table.header.mtx.MultiplexHeaderRenderer;
import table.header.mtx.MultiplexTableHeader;
import table.header.mtx.MultiplexTableHeaderUI;

public class WinIntervalIssueTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;
	private static final String LAST_WIN = "*";

	class MajorTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 4797077918623201462L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return iis.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return iis.get(0).getMissCount().length + 5;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			if (column == 0) {
				return "奖球";
			} else if (column <= iis.get(0).getMissCount().length) {
				return column + "次";
			} else {
				if (column == iis.get(0).getMissCount().length + 1) {
					return "最近遗漏情况";
				} else if (column == iis.get(0).getMissCount().length + 2) {
					return "中奖次数";
				} else if (column == iis.get(0).getMissCount().length + 3) {
					return "奖球";
				} else if (column == iis.get(0).getMissCount().length + 4) {
					return "经历期数";
				} else {
					return null;
				}
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
			IntervalIssue p = iis.get(rowIndex);
			if (columnIndex == 0) {
				return p.getBall();
			} else if (columnIndex <= p.getMissCount().length) {
				return p.getMissCount()[columnIndex - 1];
			} else {
				if (columnIndex == p.getMissCount().length + 1) {
					int lastMiss = p.getLastMiss();
					if (lastMiss == -1) {
						return LAST_WIN;
					} else {
						return lastMiss;
					}
				} else if (columnIndex == p.getMissCount().length + 2) {
					return p.getWinCount();
				} else if (columnIndex == p.getMissCount().length + 3) {
					return p.getBall();
				} else if (columnIndex == p.getMissCount().length + 4) {
					return p.getIssueCount();
				} else {
					return null;
				}
			}
		}
	}

	JTable table;
	List<IntervalIssue> iis;
	int issue;
	int averageHotCount;
	int averageMissCount;

	public WinIntervalIssueTableFrame(List<IntervalIssue> iis, int issue) {
		// TODO Auto-generated constructor stub
		this.iis = new ArrayList<IntervalIssue>(iis);
		this.issue = issue;
		setTitle("中奖间隔期数表_[中奖" + issue + "次]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		MajorTableModel model = new MajorTableModel();
		table = new DefaultTable() {
			// prevent execute default operation
			protected void initTableHeader() {
			}

			protected JTableHeader createDefaultTableHeader() {
				return new MultiplexTableHeader(columnModel);
			}
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			final Color winColor = new Color(181, 230, 29);
			final Color lwColor = new Color(207, 218, 248);

			@Override
			protected void customizeUnSelectedRenderer(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column, Component renderComponent) {
				// TODO Auto-generated method stub
				if (column == 0 || column == table.getColumnCount() - 2) {
					setForeground(Color.red);
					return;
				}

				if (column > 0 && column < table.getColumnCount() - 4) {
					if (value instanceof Integer) {
						if ((Integer) value == 0) {
							setBackground(winColor);
							return;
						}
					}
				}

				if (LAST_WIN.equals(value)) {
					setBackground(lwColor);
				}
			}
		};
		int index = iis.get(0).getMissCount().length;
		boolean b;
		for (int i = 0; i < table.getColumnCount(); i++) {
			b = i > index;
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setPreferredWidth(b ? 100 : 40);
			table.getColumnModel().getColumn(i).setMinWidth(b ? 100 : 40);
		}

		// multiplex header
		int mid = iis.get(0).getMissCount().length / 2;
		List<MultiplexHeader> mhs = new ArrayList<MultiplexHeader>();
		mhs.add(new MultiplexHeader("中奖间隔").addSubHeader(
				new MultiplexHeader("早期中奖间隔", 1, mid)).addSubHeader(
				new MultiplexHeader("最近中奖间隔", mid + 1,
						model.getColumnCount() - 5)));
		MultiplexTableHeader tableHeader = (MultiplexTableHeader) table
				.getTableHeader();
		TableColumnModel tableColumnModel = table.getColumnModel();
		for (MultiplexHeader multiplexHeader : mhs) {
			tableHeader.addMultiplexColumn(multiplexHeader
					.createMultiplexColumn(tableColumnModel));
		}
		for (int i = 0; i < tableColumnModel.getColumnCount(); i++) {
			tableColumnModel.getColumn(i).setHeaderRenderer(
					new MultiplexHeaderRenderer());
		}
		tableHeader.setUI(new MultiplexTableHeaderUI());

		TableRowSorter<MajorTableModel> sorter = new TableRowSorter<MajorTableModel>(
				model);
		int size = table.getColumnCount();
		for (int i = 0; i < size - 1; i++) {
			if (i == 0 || i == size - 1 || i == size - 2 || i == size - 3) {
				sorter.setSortable(i, true);
			} else {
				sorter.setSortable(i, false);
			}
		}
		table.setRowSorter(sorter);
	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		getContentPane().add(jsp, BorderLayout.CENTER);
	}

	public void display() {
		// setSize(1024, 876);
		setSize(1670, 900);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
