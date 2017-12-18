package lottery.view.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

import lottery.util.LotteryConst;

public class LotteryTableRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 5492462393066542734L;

	public LotteryTableRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		Component component = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		setHorizontalAlignment(SwingUtilities.CENTER);
		if (isSelected) {
			component.setForeground(Color.WHITE);
			return component;
		} else {
			component.setForeground(Color.BLACK);
		}

		if (row % 2 == 0) {
			component.setBackground(Color.WHITE);
		} else {
			component.setBackground(LotteryConst.TABLE_CELL_DEFUALT_BG);
		}

		customizeRenderer(table, value, isSelected, hasFocus, row, column,
				component);

		return component;
	}

	protected void customizeRenderer(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column,
			Component renderComponent) {
	}

}
