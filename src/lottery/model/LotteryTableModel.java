package lottery.model;

import javax.swing.table.AbstractTableModel;

public class LotteryTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 2670879056019991058L;

	public static final String[] Columns = { "索引", "期号", "开奖日期", "红1", "红2",
			"红3", "红4", "红5", "红6", "篮球", "和数值" };

	private LotteryModel model;

	public LotteryTableModel() {
		// TODO Auto-generated constructor stub
	}

	public void setModel(LotteryModel model) {
		this.model = model;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (model == null || model.getLottery() == null) {
			return 0;
		}
		return model.getLottery().size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return Columns[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return Columns.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		DoubleChromosphere dc = model.getLottery().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return dc.getIssue();
		case 2:
			return dc.getTime();
		case 3:
			return dc.getRed1();
		case 4:
			return dc.getRed2();
		case 5:
			return dc.getRed3();
		case 6:
			return dc.getRed4();
		case 7:
			return dc.getRed5();
		case 8:
			return dc.getRed6();
		case 9:
			return dc.getBlue();
		case 10:
			return dc.getSummation();
		}
		return null;
	}

}
