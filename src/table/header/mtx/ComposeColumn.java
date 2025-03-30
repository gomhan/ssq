package table.header.mtx;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * A {@code MultiplexColumn} is part of table header, it spans multi-columns and
 * may contains sub {@link MultiplexColumn}.<br>
 * </br> A illustrate below present a complex table header which contains 3 rows
 * and 8 columns. the most underly is concrete column header supported by
 * {@link JTable}, above it, that is {@code MultiplexColumn}.
 * 
 * <pre>
 *                                     ----------------------------------
 *   first level MultiplexColumn  ->   |             header             |
 *                                     ----------------------------------
 *   second level MultiplexColumn  ->  | sub header a  |  sub header b  |                  -
 *                                     ----------------------------------
 *   the actual table column header->  | A | B | C | D | E | F | G |  H | 
 *                                     ----------------------------------
 * </pre>
 * 
 * 
 * 
 */
public class MultiplexColumn {

	protected TableCellRenderer headerRenderer;

	/** a group of sub column header contained by the class */
	protected Vector<Object> gsch;

	/** name of this multiplex column */
	protected String headerValue;

	protected int margin = 0;

	public MultiplexColumn(String headerName) {
		this(null, headerName);
	}

	public MultiplexColumn(TableCellRenderer headerRenderer, String headerName) {
		if (headerRenderer == null) {
			this.headerRenderer = new MultiplexHeaderRenderer();
		} else {
			this.headerRenderer = headerRenderer;
		}
		this.headerValue = headerName;
		gsch = new Vector<Object>();
	}

	/**
	 * one of below operations :<br>
	 * 1) add a single {@link TableColumn} into this {@link MultiplexColumn}<br>
	 * 2) add a sub {@link MultiplexColumn} into this {@link MultiplexColumn}<br>
	 * 
	 * @param obj
	 *            : this parameter may be {@link TableColumn} or
	 *            {@link MultiplexColumn}
	 */
	public MultiplexColumn add(Object obj) {
		if (obj != null) {
			gsch.addElement(obj);
		}
		return this;
	}

	/**
	 * get {@link MultiplexColumn} of specified {@link TableColumn}
	 * 
	 * @param c
	 *            {@link TableColumn}
	 * @param gs
	 *            vector contains multi-columns
	 * @return
	 */
	public Vector<MultiplexColumn> getColumns(TableColumn c,
			Vector<MultiplexColumn> gs) {
		// first add this to vector
		gs.addElement(this);

		// check if this multiplex column contains the specified TableColumn.
		// if not search sub multiplex column.
		if (gsch.contains(c)) {
			return gs;
		}

		Vector<MultiplexColumn> rgs; // result multi-columns
		Vector<MultiplexColumn> tmp;
		MultiplexColumn gc;
		Enumeration<Object> em = gsch.elements();
		while (em.hasMoreElements()) {
			Object obj = em.nextElement();
			if (obj instanceof MultiplexColumn) {
				gc = (MultiplexColumn) obj;
				tmp = (Vector<MultiplexColumn>) gs.clone();
				rgs = gc.getColumns(c, tmp);
				if (rgs != null) {
					return rgs;
				}
			}
		}

		return null;
	}

	public Dimension getSize(JTable table) {
		Component comp = headerRenderer.getTableCellRendererComponent(table,
				getHeaderValue(), false, false, -1, -1);
		int height = comp.getPreferredSize().height;
		int width = 0;
		Enumeration<Object> em = gsch.elements();
		while (em.hasMoreElements()) {
			Object obj = em.nextElement();
			if (obj instanceof TableColumn) {
				TableColumn aColumn = (TableColumn) obj;
				width += aColumn.getWidth();
				width += margin;
			} else {
				width += ((MultiplexColumn) obj).getSize(table).width;
			}
		}
		return new Dimension(width, height);
	}

	public void setColumnMargin(int margin) {
		this.margin = margin;
		Enumeration<Object> em = gsch.elements();
		while (em.hasMoreElements()) {
			Object obj = em.nextElement();
			if (obj instanceof MultiplexColumn) {
				((MultiplexColumn) obj).setColumnMargin(margin);
			}
		}
	}

	public TableCellRenderer getHeaderRenderer() {
		return headerRenderer;
	}

	public void setHeaderRenderer(TableCellRenderer headerRenderer) {
		if (headerRenderer != null) {
			this.headerRenderer = headerRenderer;
		}
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}
}
