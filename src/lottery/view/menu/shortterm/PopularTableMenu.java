package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.shortterm.PopularDeviationStatistic;
import lottery.function.shortterm.PopularDeviationStatistic.Popular;
import lottery.itf.Function;
import lottery.itf.Result;
import lottery.view.funcview.PopularInTableFrame;
import lottery.view.menu.AbstractTermMenu;

public class PopularTableMenu extends AbstractTermMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1123425236663830785L;

	public PopularTableMenu() {
		super();
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		setName("PopularTableMenu");
		setText("¿‰»»√≈∆´≤Ó");
		function = new PopularDeviationStatistic();
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		PopularInTableFrame frame = new PopularInTableFrame(
				(List<Popular>) result.getValue(), length);
		frame.display();
	}

	@Override
	protected void prepareFunctionWithUserInput(Function f) {
		// TODO Auto-generated method stub
		f.setProperty(PopularDeviationStatistic.PROPERTY_OFFSET, offset);
	}

}
