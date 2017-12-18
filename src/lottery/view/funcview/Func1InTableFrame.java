package lottery.view.funcview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import lottery.function.common.Function1.SummationCount;
import lottery.view.renderer.LotteryTableRenderer;

public class Func1InTableFrame extends JFrame {
	private static final long serialVersionUID = 6985324648616498984L;

	class Func1TableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1323224085412318456L;

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return scs.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			switch (column) {
			case 0:
				return "和数值";
			case 1:
				return "中奖次数";
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
			SummationCount sc = scs.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return sc.getSummation();
			case 1:
				return sc.getCount();
			default:
				break;
			}
			return null;
		}
	}

	JTable table;
	List<SummationCount> scs;

	public Func1InTableFrame(List<SummationCount> scs) {
		// TODO Auto-generated constructor stub
		this.scs = new ArrayList<>(scs);
		initialize();
		build();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		setTitle("和数值表");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		table = new JTable();
		Func1TableModel model = new Func1TableModel();
		table.setModel(model);

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		LotteryTableRenderer renderer = new LotteryTableRenderer() {
			@Override
			protected void customizeRenderer(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column,
					Component renderComponent) {
				if (column == 1) {
					Integer count = (Integer) value;
					if (count == 0) {
						setForeground(Color.RED);
					}
				}
			}
		};
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
		table.getTableHeader().setReorderingAllowed(false);
	}

	private void build() {
		// TODO Auto-generated method stub
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(table);
		getContentPane().add(jsp, BorderLayout.CENTER);
	}

	public void display() {
		setSize(800, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
