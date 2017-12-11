package lottery.view.menu.common;

import java.util.List;

import lottery.function.FunctionResult;
import lottery.function.common.Function1;
import lottery.function.common.Function1.SummationCount;
import lottery.itf.Function;
import lottery.view.funcview.Func1InTableFrame;
import lottery.view.menu.FunctionMenu;

public class Func1TableMenu extends FunctionMenu {
	private static final long serialVersionUID = -2543363810930093121L;
	Function function;

	public Func1TableMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func1TableMenu");
		setText("和数值表");
		addActionListener(this);
		function = new Function1();
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		return function;
	}

	public void showChart(FunctionResult fr) {
		Func1InTableFrame f = new Func1InTableFrame(
				(List<SummationCount>) fr.getValue());
		f.display();
	}
}
