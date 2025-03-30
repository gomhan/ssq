package table.header.mtx;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * responsible for rendering {@link MultiplexTableHeader}
 * 
 */
public class MultiplexTableHeaderUI extends BasicTableHeaderUI {

	public static final String EMPTY_LABEL = "EMPTY";

	public static boolean isEmpty(TableColumn tableColumn) {
		return EMPTY_LABEL.equals(tableColumn.getHeaderValue());
	}

	public MultiplexTableHeaderUI() {
		super();
	}

	public void paint(Graphics g, JComponent c) {
		TableColumnModel tcm = header.getColumnModel();
		if (tcm == null || tcm.getColumnCount() <= 0) {
			return;
		}

		// clip bounds for rendering
		Rectangle clipBounds = g.getClipBounds();
		// header size
		Dimension size = header.getSize();

		// bound of one cell in header, initialize with size
		Rectangle cellBound = new Rectangle(0, 0, size.width, size.height);
		// map multiplex column and it's bound.
		HashMap<MultiplexColumn, Rectangle> mcBoundMap = new HashMap<>();
		Enumeration<MultiplexColumn> mcs;// group of multi-columns
		MultiplexColumn mc;
		Rectangle mcBound; // bound of multi-column
		int mcHeight; // height of all multi-columns
		TableColumn tableColumn;
		for (int columnIndex = 0; columnIndex < tcm.getColumnCount(); columnIndex++) {
			tableColumn = tcm.getColumn(columnIndex);
			cellBound.height = size.height;
			cellBound.y = 0;
			mcs = ((MultiplexTableHeader) header).getColumns(tableColumn);
			if (mcs != null) {
				mcHeight = 0;
				while (mcs.hasMoreElements()) {
					mc = mcs.nextElement();
					mcBound = (Rectangle) mcBoundMap.get(mc);
					if (mcBound == null) {
						mcBound = new Rectangle(cellBound);
						Dimension d = mc.getSize(header.getTable());
						mcBound.width = d.width;
						mcBound.height = d.height;
						mcBoundMap.put(mc, mcBound);

						mcHeight += mcBound.height;
						cellBound.height = size.height - mcHeight;
						cellBound.y = mcHeight;
						if (isEmpty(tableColumn) && !mcs.hasMoreElements()) {
							mcBound.height += cellBound.height;
						}
						if (mcBound.intersects(clipBounds)) {
							paintCell(g, mcBound, mc);
						}
					} else {
						mcHeight += mcBound.height;
						cellBound.height = size.height - mcHeight;
						cellBound.y = mcHeight;
						if (isEmpty(tableColumn) && !mcs.hasMoreElements()) {
							mcBound.height += cellBound.height;
						}
					}
				}
			}

			cellBound.width = tableColumn.getWidth();

			if (cellBound.intersects(clipBounds) && !isEmpty(tableColumn)) {
				paintCell(g, cellBound, columnIndex);
			}

			cellBound.x += cellBound.width;
		}

		rendererPane.removeAll();
	}

	private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
		TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
		TableCellRenderer renderer = aColumn.getHeaderRenderer();
		Component component = renderer.getTableCellRendererComponent(
				header.getTable(), aColumn.getHeaderValue(), false, false, -1,
				columnIndex);
		rendererPane.paintComponent(g, component, header, cellRect.x,
				cellRect.y, cellRect.width, cellRect.height, true);
	}

	private void paintCell(Graphics g, Rectangle cellRect, MultiplexColumn mc) {
		TableCellRenderer renderer = mc.getHeaderRenderer();
		Component component = renderer.getTableCellRendererComponent(
				header.getTable(), mc.getHeaderValue(), false, false, -1, -1);
		rendererPane.paintComponent(g, component, header, cellRect.x,
				cellRect.y, cellRect.width, cellRect.height, true);
	}

	private int getHeaderHeight() {
		int height = 0;
		int rendererHeight;
		TableColumn tc;
		TableCellRenderer renderer;
		Component comp;
		Enumeration<MultiplexColumn> em;
		MultiplexColumn mc;
		TableColumnModel columnModel = header.getColumnModel();
		for (int column = 0; column < columnModel.getColumnCount(); column++) {
			tc = columnModel.getColumn(column);
			renderer = tc.getHeaderRenderer();
			comp = renderer.getTableCellRendererComponent(header.getTable(),
					tc.getHeaderValue(), false, false, -1, column);
			rendererHeight = comp.getPreferredSize().height;
			em = ((MultiplexTableHeader) header).getColumns(tc);
			if (em != null) {
				while (em.hasMoreElements()) {
					mc = em.nextElement();
					rendererHeight += mc.getSize(header.getTable()).height;
				}
			}
			height = Math.max(height, rendererHeight);
		}
		return height;
	}

	private Dimension createHeaderSize(long width) {
		TableColumnModel columnModel = header.getColumnModel();
		width += columnModel.getColumnMargin() * columnModel.getColumnCount();
		if (width > Integer.MAX_VALUE) {
			width = Integer.MAX_VALUE;
		}
		return new Dimension((int) width, getHeaderHeight());
	}

	public Dimension getPreferredSize(JComponent c) {
		long width = 0;
		TableColumn tableColumn;
		Enumeration<TableColumn> tableColumns = header.getColumnModel()
				.getColumns();
		while (tableColumns.hasMoreElements()) {
			tableColumn = tableColumns.nextElement();
			width = width + tableColumn.getPreferredWidth();
		}
		return createHeaderSize(width);
	}
}
