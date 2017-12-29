package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.shortterm.OddEvenDeviationStatistic;
import lottery.function.shortterm.OddEvenDeviationStatistic.OddEvenNumber;
import lottery.itf.Result;
import lottery.view.funcview.OddEvenInTableFrame;
import lottery.view.menu.ShortTermMenu;

public class OddEvenTableMenu extends ShortTermMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5585629830006765435L;

	public OddEvenTableMenu() {
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("OddEvenTableMenu");
		setText("ÆæÅ¼Æ«²î");
		function = new OddEvenDeviationStatistic();
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		OddEvenInTableFrame frame = new OddEvenInTableFrame(
				(List<OddEvenNumber>) result.getValue(), length);
		frame.display();
	}

}
