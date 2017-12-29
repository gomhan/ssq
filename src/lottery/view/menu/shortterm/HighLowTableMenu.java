package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.shortterm.HighLowDeviationStatistic;
import lottery.function.shortterm.HighLowDeviationStatistic.LowHighNumber;
import lottery.itf.Result;
import lottery.view.funcview.HighLowInTableFrame;
import lottery.view.menu.ShortTermMenu;

public class HighLowTableMenu extends ShortTermMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6071326861450893056L;

	public HighLowTableMenu() {
		super();
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("HighLowTableMenu");
		setText("´óÐ¡Æ«²î");
		function = new HighLowDeviationStatistic();
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		HighLowInTableFrame frame = new HighLowInTableFrame(
				(List<LowHighNumber>) result.getValue(), length);
		frame.display();
	}

}
