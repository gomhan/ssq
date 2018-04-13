package lottery.view.menu.midterm;

import java.util.List;

import lottery.function.midterm.LotteryIssueStatistic;
import lottery.function.midterm.LotteryIssueStatistic.LotteryIssue;
import lottery.itf.Result;
import lottery.view.funcview.LotteryIssueTableFrame;
import lottery.view.menu.AbstractTermMenu;

public class LotteryIssueTableMenu extends AbstractTermMenu {

	private static final long serialVersionUID = -9086711858809993359L;

	public LotteryIssueTableMenu() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("LotteryIssueTableMenu");
		setText("²ÊÆ±ÆÚÊý±í");
		function = new LotteryIssueStatistic();
		field.setText("50");
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		LotteryIssueTableFrame frame = new LotteryIssueTableFrame(
				(List<LotteryIssue>) result.getValue(), length);
		frame.display();
	}
}
