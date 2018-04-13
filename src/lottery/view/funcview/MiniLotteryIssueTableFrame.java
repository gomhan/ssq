package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import lottery.function.midterm.MiniLotteryIssueStatistic.MiniLotteryIssue;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;
import table.header.mtx.MultiplexHeader;
import table.header.mtx.MultiplexHeaderRenderer;
import table.header.mtx.MultiplexTableHeader;
import table.header.mtx.MultiplexTableHeaderUI;

public class MiniLotteryIssueTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;
	private static final String RED_LAYOUT = "X";

	class MajorTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 4797077918623201462L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return mlis.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return mlis.get(0).getIssue().length + 4;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			if (column == 0) {
				return "����";
			} else if (column <= mlis.get(0).getIssue().length) {
				return "" + column;
			} else {
				if (column == mlis.get(0).getIssue().length + 1) {
					return "��©����";
				} else if (column == mlis.get(0).getIssue().length + 2) {
					return "�н�����";
				} else if (column == mlis.get(0).getIssue().length + 3) {
					return "����";
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
			MiniLotteryIssue p = mlis.get(rowIndex);
			if (columnIndex == 0) {
				return p.getBall();
			} else if (columnIndex <= p.getStatus().length) {
				if (p.getStatus()[columnIndex - 1] == -1) {
					return "-";
				} else if (p.getStatus()[columnIndex - 1] == 1) {
					return RED_LAYOUT;
				} else if (p.getStatus()[columnIndex - 1] == -2) {
					return "=";
				} else {
					return null;
				}
			} else {
				if (columnIndex == p.getStatus().length + 1) {
					return p.getMissCount();
				} else if (columnIndex == p.getStatus().length + 2) {
					return p.getWinCount();
				} else if (columnIndex == p.getStatus().length + 3) {
					return p.getBall();
				} else {
					return null;
				}
			}
		}
	}

	JTable table;
	List<MiniLotteryIssue> mlis;
	int issue;
	int averageHotCount;
	int averageMissCount;

	public MiniLotteryIssueTableFrame(List<MiniLotteryIssue> mils, int issue) {
		// TODO Auto-generated constructor stub
		this.mlis = new ArrayList<MiniLotteryIssue>(mils);
		this.issue = issue;
		setTitle("С�Ͳ�Ʊ������_[" + issue + "��]");
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

			@Override
			protected void customizeUnSelectedRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				// TODO Auto-generated method stub
				if (RED_LAYOUT.equals(value)) {
					setBackground(winColor);
				}
			}
		};
		boolean b;
		for (int i = 0; i < table.getColumnCount(); i++) {
			b = i == 0 || i == mlis.get(0).getStatus().length + 1
					|| i == mlis.get(0).getStatus().length + 2
					|| i == mlis.get(0).getStatus().length + 3;
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setPreferredWidth(b ? 80 : 30);
			table.getColumnModel().getColumn(i).setMinWidth(b ? 80 : 30);
		}

		// multiplex header
		List<MultiplexHeader> mhs = new ArrayList<MultiplexHeader>();
		mhs.add(new MultiplexHeader("����", 1, table.getColumnCount() - 4));
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
		sorter.setSortable(2, false);
		table.setRowSorter(null);
	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		getContentPane().add(jsp, BorderLayout.CENTER);

		// JLabel label = new JLabel();
		// label.setText(text());
		// getContentPane().add(label, BorderLayout.SOUTH);
	}

	private String text() {
		StringBuilder buf = new StringBuilder();
		buf.append("<html>").append("\n").append("<body>").append("\n")
				.append(createVerticalTable()).append("\n").append("</body>")
				.append("</html>");
		return buf.toString();
	}

	private String createVerticalTable() {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("����У��", "ĳ����ʱ����©�����֣�����һ���н����ֿ�ʼ��ʱ����©��");
		map.put("����������ת", "�н�������������ڵ����������н���eg. XX, X1X, X12X, X123X");
		map.put("���", "�����н��������ڳ���ʱ���ϵ�������©�������");
		map.put("������", "�����н��������ڳ���ʱ���ϵݼ�����©�������");
		map.put("˫�ף�����...���", "�����н��ڼ�Ķ�β����ȵ���©��");
		map.put("�н���������", "����������ת�Ժ���©�ߴ���ǰ��ÿ�ڶ�ѡ������֡�");
		map.put("��ʽ����", "([XX|X1X|X12X](12345X)+)+");

		StringBuilder buf = new StringBuilder();

		buf.append("<table border=\"1\">").append("\n");

		buf.append("<tr>").append("\n");
		buf.append("<td align=\"center\">").append("����").append("</td>")
				.append("\n");
		buf.append("<td align=\"center\">").append("����").append("</td>")
				.append("\n");
		buf.append("</tr>").append("\n");

		Iterator<String> keys = map.keySet().iterator();
		String key;
		while (keys.hasNext()) {
			key = keys.next();
			buf.append("<tr>").append("\n");
			buf.append("<td align=\"center\">").append(key).append("</td>")
					.append("\n");
			buf.append("<td align=\"left\">").append(map.get(key))
					.append("</td>").append("\n");
			buf.append("</tr>").append("\n");
		}
		buf.append("</table>");

		return buf.toString();
	}

	public void display() {
		// setSize(1024, 876);
		setSize(1670, 900);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
