package table.header.mtx;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * A concrete class of multi-columns table header.
 */
public class MultiplexTableHeader extends JTableHeader {
	private static final long serialVersionUID = -7605575004017964947L;
	// private static final String uiClassID = "GroupableTableHeaderUI";
	protected Vector<MultiplexColumn> gmc;

	public MultiplexTableHeader(TableColumnModel model) {
		super(model);
		setReorderingAllowed(false);
	}

	public void addMultiplexColumn(MultiplexColumn mc) {
		if (gmc == null) {
			gmc = new Vector<MultiplexColumn>();
		}
		gmc.addElement(mc);
	}

	/**
	 * get all {@link MultiplexColumn} with specified {@link TableColumn}
	 * 
	 * @param column
	 *            {@link TableColumn}
	 * @return
	 */
	public Enumeration<MultiplexColumn> getColumns(TableColumn column) {
		if (gmc == null) {
			return null;
		}

		MultiplexColumn mc;
		Vector<MultiplexColumn> rmc; // result of multiplex columns
		Vector<MultiplexColumn> tmp = new Vector<>();
		Enumeration<MultiplexColumn> em = gmc.elements();
		while (em.hasMoreElements()) {
			mc = em.nextElement();
			tmp.clear();
			rmc = mc.getColumns(column, tmp);
			if (rmc != null) {
				return rmc.elements();
			}
		}
		return null;
	}

	public void setColumnMargin() {
		if (gmc == null) {
			return;
		}

		MultiplexColumn mc;
		int columnMargin = getColumnModel().getColumnMargin();
		Enumeration<MultiplexColumn> em = gmc.elements();
		while (em.hasMoreElements()) {
			mc = em.nextElement();
			mc.setColumnMargin(columnMargin);
		}
	}
	
}
