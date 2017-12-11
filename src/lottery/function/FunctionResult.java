package lottery.function;

import lottery.itf.Checker;
import lottery.itf.Result;

public class FunctionResult implements Result {

	protected Object value;
	protected Checker checker;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public boolean invalid() {
		// TODO Auto-generated method stub
		return getChecker().invalid(getValue());
	}

	@Override
	public void setChecker(Checker checker) {
		// TODO Auto-generated method stub
		this.checker = checker;
	}

	@Override
	public Checker getChecker() {
		// TODO Auto-generated method stub
		if (checker == null) {
			checker = new Checker() {
				@Override
				public boolean invalid(Object value) {
					// TODO Auto-generated method stub
					return false;
				}
			};
		}
		return checker;
	}
}
