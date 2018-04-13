package lottery.view.menu.midterm;

import java.util.List;

import lottery.function.midterm.WinIntervalIssueStatistic;
import lottery.function.midterm.WinIntervalIssueStatistic.IntervalIssue;
import lottery.itf.Function;
import lottery.itf.Result;
import lottery.view.funcview.WinIntervalIssueTableFrame;
import lottery.view.menu.AbstractTermMenu;

public class IntervalIssueTableMenu extends AbstractTermMenu {

	private static final long serialVersionUID = -1875633183162851383L;

	public IntervalIssueTableMenu() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("IntervalIssueTableMenu");
		setText("中奖间隔期数表");
		function = new WinIntervalIssueStatistic();
		field.setText("50");
	}

	@Override
	protected void prepareFunctionWithUserInput(Function f) {
		// TODO Auto-generated method stub
		f.setProperty(WinIntervalIssueStatistic.PROPERTY_SIZE, length);
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		WinIntervalIssueTableFrame frame = new WinIntervalIssueTableFrame(
				(List<IntervalIssue>) result.getValue(), length);
		frame.display();
	}

}
