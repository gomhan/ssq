package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.shortterm.PercentDeviationStatistic;
import lottery.function.shortterm.PercentDeviationStatistic.PercentNumber;
import lottery.itf.Result;
import lottery.view.funcview.PercentInTableFrame;
import lottery.view.menu.AbstractTermMenu;

public class PercentTableMenu extends AbstractTermMenu {

	public PercentTableMenu() {
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("PercentInTableMenu");
		setText("°Ù·Ö±ÈÆ«²î");
		function = new PercentDeviationStatistic();
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		PercentInTableFrame frame = new PercentInTableFrame(
				(List<PercentNumber>) result.getValue(), length);
		frame.display();
	}

}
