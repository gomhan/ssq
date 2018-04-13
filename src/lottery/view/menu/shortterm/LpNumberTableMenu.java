package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.shortterm.LastPlaceDeviationStatistic;
import lottery.function.shortterm.LastPlaceDeviationStatistic.LastNumber;
import lottery.itf.Result;
import lottery.view.funcview.LastPlaceInTableFrame;
import lottery.view.menu.AbstractTermMenu;

public class LpNumberTableMenu extends AbstractTermMenu {

	public LpNumberTableMenu() {
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("LpNumberTableMenu");
		setText("ĩλ��ƫ��");
		function = new LastPlaceDeviationStatistic();
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		LastPlaceInTableFrame frame = new LastPlaceInTableFrame(
				(List<LastNumber>) result.getValue(), length);
		frame.display();
	}

}
