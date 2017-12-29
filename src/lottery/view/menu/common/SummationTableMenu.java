package lottery.view.menu.common;

import java.util.List;

import lottery.function.common.SummationStatistic;
import lottery.function.common.SummationStatistic.SummationCount;
import lottery.itf.Result;
import lottery.view.funcview.SummationInTableFrame;
import lottery.view.menu.FunctionMenu;

public class SummationTableMenu extends FunctionMenu {
	private static final long serialVersionUID = -2543363810930093121L;

	public SummationTableMenu() {
		super();
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		setName("SummationTableMenu");
		setText("和数值表");
		function = new SummationStatistic();
	}

	public void showGraphics(Result result) {
		SummationInTableFrame f = new SummationInTableFrame(
				(List<SummationCount>) result.getValue());
		f.display();
	}
}
