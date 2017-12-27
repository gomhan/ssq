package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.FunctionResult;
import lottery.function.shortterm.Function3;
import lottery.function.shortterm.Function3.OddEvenNumber;
import lottery.itf.Function;
import lottery.view.funcview.Func3InTableFrame;

public class Func3TableMenu extends ShortTermMenu {

	public Func3TableMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func3TableMenu");
		setText("ÆæÅ¼Æ«²î");

		function = new Function3();
	}

	@Override
	public void showChart(FunctionResult fr) {
		// TODO Auto-generated method stub
		Func3InTableFrame frame = new Func3InTableFrame(
				(List<OddEvenNumber>) fr.getValue(), end - issue);
		frame.display();
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		return function;
	}

}
