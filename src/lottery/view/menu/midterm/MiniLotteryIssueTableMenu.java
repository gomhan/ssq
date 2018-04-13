package lottery.view.menu.midterm;

import java.util.List;

import lottery.function.midterm.MiniLotteryIssueStatistic;
import lottery.function.midterm.MiniLotteryIssueStatistic.MiniLotteryIssue;
import lottery.itf.Result;
import lottery.view.funcview.MiniLotteryIssueTableFrame;
import lottery.view.menu.AbstractTermMenu;

public class MiniLotteryIssueTableMenu extends AbstractTermMenu {
	private static final long serialVersionUID = 7896957243275108980L;

	public MiniLotteryIssueTableMenu() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("MiniLotteryIssueTableMenu");
		setText("小型彩票期数表");
		function = new MiniLotteryIssueStatistic();
		field.setText("50");
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		MiniLotteryIssueTableFrame frame = new MiniLotteryIssueTableFrame(
				(List<MiniLotteryIssue>) result.getValue(), length);
		frame.display();
	}

}
