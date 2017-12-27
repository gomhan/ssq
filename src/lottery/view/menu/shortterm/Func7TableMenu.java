package lottery.view.menu.shortterm;

import java.util.List;

import lottery.function.FunctionResult;
import lottery.function.shortterm.Function7;
import lottery.function.shortterm.Function7.Popular;
import lottery.itf.Function;
import lottery.view.funcview.Func7InTableFrame;

public class Func7TableMenu extends ShortTermMenu {

	public Func7TableMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func7TableMenu");
		setText("¿‰»»√≈∆´≤Ó");

		function = new Function7();
	}

	@Override
	public void showChart(FunctionResult fr) {
		// TODO Auto-generated method stub
		Func7InTableFrame frame = new Func7InTableFrame(
				(List<Popular>) fr.getValue(), end - issue);
		frame.display();
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		((Function7) function).setIssue(issue);
		return function;
	}

}
