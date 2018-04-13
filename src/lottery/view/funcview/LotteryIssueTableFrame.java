package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import lottery.function.midterm.LotteryIssueStatistic.LotteryIssue;
import lottery.util.LotteryConst;
import lottery.view.renderer.LotteryTableRenderer;
import lottery.view.table.DefaultTable;
import table.header.mtx.MultiplexHeader;
import table.header.mtx.MultiplexHeaderRenderer;
import table.header.mtx.MultiplexTableHeader;
import table.header.mtx.MultiplexTableHeaderUI;

public class LotteryIssueTableFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5488159906591486152L;
	private static final String RED_LAYOUT = "##";
	private static final String BLUE_LAYOUT = "**";
	private static final int COLUMN = 51;

	class MajorTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -8975416585186406217L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return lis.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return COLUMN;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			if (column == 0) {
				return "����";
			} else if (column == 1) {
				return "ʱ��";
			} else {
				if (column < 35) {
					return "" + (column - 1);
				} else {
					return "" + (column - 1 - LotteryConst.RED_BALL_COUNT);
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
			LotteryIssue p = lis.get(rowIndex);
			if (columnIndex == 0) {
				return p.getIssue();
			} else if (columnIndex == 1) {
				return p.getTime();
			} else {
				int v = p.getBall()[columnIndex - 2];
				if (columnIndex < 35) {
					return v == -1 ? RED_LAYOUT : v;
				} else {
					return v == -1 ? BLUE_LAYOUT : v;
				}
			}
		}
	}

	JTable table;
	List<LotteryIssue> lis;
	int issue;
	int averageHotCount;
	int averageMissCount;

	public LotteryIssueTableFrame(List<LotteryIssue> lis, int issue) {
		// TODO Auto-generated constructor stub
		this.lis = new ArrayList<LotteryIssue>(lis);
		this.issue = issue;
		setTitle("��Ʊ������_[" + issue + "��]");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		MajorTableModel model = new MajorTableModel();
		table = new DefaultTable() {
			// prevent executing default implementation
			protected void initTableHeader() {
			}

			protected JTableHeader createDefaultTableHeader() {
				return new MultiplexTableHeader(columnModel);
			}
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(model);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			final Color rc = new Color(219, 252, 192);
			final Color bc = new Color(207, 218, 248);

			@Override
			protected void customizeUnSelectedRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				// TODO Auto-generated method stub
				if (RED_LAYOUT.equals(value)) {
					setBackground(rc);
				} else if (BLUE_LAYOUT.equals(value)) {
					setBackground(bc);
				}
			}
		};
		boolean b;
		for (int i = 0; i < table.getColumnCount(); i++) {
			b = i == 0 || i == 1;
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			table.getColumnModel().getColumn(i).setPreferredWidth(b ? 80 : 30);
			table.getColumnModel().getColumn(i).setMinWidth(b ? 80 : 30);
		}

		// multiplex header
		List<MultiplexHeader> mhs = new ArrayList<MultiplexHeader>();
		mhs.add(new MultiplexHeader("����").addSubHeader(
				new MultiplexHeader("С����", 2, 17)).addSubHeader(
				new MultiplexHeader("������", 18, 34)));
		mhs.add(new MultiplexHeader("����").addSubHeader(
				new MultiplexHeader("С����", 35, 42)).addSubHeader(
				new MultiplexHeader("������", 43, COLUMN - 1)));
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

		JLabel label = new JLabel();
		label.setText(text());
		getContentPane().add(label, BorderLayout.SOUTH);
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
