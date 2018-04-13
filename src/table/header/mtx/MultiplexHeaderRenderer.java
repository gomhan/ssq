package table.header.mtx;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class MultiplexHeaderRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = -8630700772363445631L;
	
	private static final Font HEADER_FONT = new Font("ËÎÌו", Font.BOLD, 13);
	private static final Color FONT_COLOR = new Color(0, 128, 192);

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JTableHeader header = table.getTableHeader();
		if (header != null) {
//			setForeground(header.getForeground());
			setForeground(FONT_COLOR);
			setBackground(header.getBackground());
//			setFont(header.getFont());
			setFont(HEADER_FONT);
		}
		setHorizontalAlignment(JLabel.CENTER);
		setText((value == null) ? "" : value.toString());
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		return this;
	}
}
