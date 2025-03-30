package table.header.mtx;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableColumnModel;

/**
 * An auxiliary class that define a header object which covers multi-columns for
 * create {@link MultiplexColumn}. the class save header's name and header's
 * region and any sub-group headers.
 */
public class MultiplexHeader {

	/* actually is the text shown in table header */
	private String headerValue;
	private int beginColumn;
	private int endColumn;
	private List<MultiplexHeader> subHeaders;

	/**
	 * create a multiplex with specified {@code headerValue}, but do not define
	 * the region.
	 * 
	 * @param headerValue
	 */
	public MultiplexHeader(String headerValue) {
		this(headerValue, -1, -1);
	}

	/**
	 * create a multiplex header with specified value and region.
	 * 
	 * @param headerValue
	 * @param beginColumn
	 * @param endColumn
	 */
	public MultiplexHeader(String headerValue, int beginColumn, int endColumn) {
		this.headerValue = headerValue;
		this.beginColumn = beginColumn;
		this.endColumn = endColumn;
		subHeaders = new ArrayList<MultiplexHeader>();
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public int getBeginColumn() {
		return beginColumn;
	}

	public void setBeginColumn(int beginColumn) {
		this.beginColumn = beginColumn;
	}

	public int getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(int endColumn) {
		this.endColumn = endColumn;
	}

	public MultiplexHeader addSubHeader(MultiplexHeader subHeader) {
		subHeaders.add(subHeader);
		return this;
	}

	/**
	 * create a {@link MultiplexColumn} which described by this
	 * {@link MultiplexHeader}.
	 * 
	 * @param tableColumnModel
	 *            {@link TableColumnModel}
	 * @return
	 */
	public MultiplexColumn createMultiplexColumn(
			TableColumnModel tableColumnModel) {
		MultiplexColumn mc = new MultiplexColumn(headerValue);
		if (beginColumn >= 0 && endColumn >= 0 && beginColumn <= endColumn) {
			for (int i = beginColumn; i <= endColumn; i++) {
				mc.add(tableColumnModel.getColumn(i));
			}
		}

		for (MultiplexHeader groupHeader : subHeaders) {
			mc.add(groupHeader.createMultiplexColumn(tableColumnModel));
		}
		return mc;
	}

}
