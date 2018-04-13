package lottery.function;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import lottery.itf.Checker;
import lottery.itf.Result;
import lottery.model.ObservableBean;

/**
 * One result associate with one key. there are at most 255 results of one
 * function. A negative key represent a invalid result.
 */
public class ExecutionResult extends ObservableBean implements Result {

	protected int resultKey;
	protected Checker checker;
	protected Object value;

	public ExecutionResult() {
		this(-1);
	}

	public ExecutionResult(int resultKey) {
		this(resultKey, null);
	}

	public ExecutionResult(int resultKey, Object value) {
		this(resultKey, value, null);
	}

	public ExecutionResult(int resultKey, Object value, Checker checker) {
		super();
		this.resultKey = resultKey;
		this.checker = checker;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		Object old = this.value;
		this.value = value;
		firePropertyChange("value", old, value);
	}

	public int getResultKey() {
		return resultKey;
	}

	public void setResultKey(int resultKey) {
		int old = this.resultKey;
		this.resultKey = resultKey;
		firePropertyChange("resultKey", old, resultKey);
	}

	@Override
	public synchronized boolean invalid() {
		// TODO Auto-generated method stub
		if (getResultKey() < 0) {
			return true;
		}
		return getChecker().invalid(getValue());
	}

	@Override
	public synchronized void setChecker(Checker checker) {
		// TODO Auto-generated method stub
		Checker old = this.checker;
		this.checker = checker;
		firePropertyChange("checker", old, checker);
	}

	@Override
	public synchronized Checker getChecker() {
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

	@Override
	public void clearValue() {
		Object obj = getValue();
		if (obj == null) {
			return;
		}
		if (obj instanceof Collection<?>) {
			try {
				((Collection<?>) obj).clear();
			} catch (UnsupportedOperationException e) {
				// TODO: handle exception
			}
		} else if (obj instanceof Map<?, ?>) {
			try {
				((Map<?, ?>) obj).clear();
			} catch (UnsupportedOperationException e) {
				// TODO: handle exception
			}
		} else if (obj.getClass().isArray()) {
			try {
				Object[] arr = (Object[]) obj;
				Arrays.fill(arr, null);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		setValue(null);
	}
}
