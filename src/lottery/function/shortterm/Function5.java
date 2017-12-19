package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.State;

public class Function5 extends AbstractFunction {

	private List<DoubleChromosphere> dcs;

	public Function5() {
		// TODO Auto-generated constructor stub
		super();
		name = "Function5";
		dcs = new ArrayList<DoubleChromosphere>();
	}

	@Override
	public State invoke(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		dcs.addAll(parameter);
		fr.setValue(dcs);
		return State.SUCCESS;
	}

	@Override
	public void clearValue() {
		// TODO Auto-generated method stub
		if (dcs != null) {
			dcs.clear();
		}
	}

}
