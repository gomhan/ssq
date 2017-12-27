package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.FunctionResult;
import lottery.function.shortterm.Function4;
import lottery.function.shortterm.Function4.LowHighNumber;
import lottery.itf.Function;
import lottery.view.funcview.Func4InTableFrame;

public class Func4TableMenu extends ShortTermMenu {

	public Func4TableMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func4TableMenu");
		setText("´óÐ¡Æ«²î");

		function = new Function4();
	}

	@Override
	public void showChart(FunctionResult fr) {
		// TODO Auto-generated method stub
		Func4InTableFrame frame = new Func4InTableFrame(
				(List<LowHighNumber>) fr.getValue(), end - issue);
		frame.display();
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		return function;
	}

}
