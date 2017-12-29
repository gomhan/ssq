package lottery.function;

import java.util.List;

import lottery.itf.Function;
import lottery.itf.FunctionExecutor;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.model.State;
import lottery.util.LotteryConst;

/**
 * default implementation of {@link Function}.
 */
public class DefaultFunctionExecutor implements FunctionExecutor {
	/* latest installed function */
	private Function f;
	/* cancelled mark */
	private volatile boolean cancelled;
	/* execution result */
	private State state;

	public DefaultFunctionExecutor() {
		// TODO Auto-generated constructor stub
		state = State.UNKNOWN;
	}

	@Override
	public boolean install(Function function) {
		// TODO Auto-generated method stub
		uninstall();
		this.f = function;
		return true;
	}

	@Override
	public void uninstall() {
		// TODO Auto-generated method stub
		if (f != null) {
			f.reset();
			f = null;
		}
		cancelled = false;
		state = State.UNKNOWN;
	}

	@Override
	public Function getFunction() {
		// TODO Auto-generated method stub
		return f;
	}

	@Override
	public void cancel(boolean b) {
		// TODO Auto-generated method stub
		cancelled = b;
	}

	@Override
	public boolean cancelled() {
		// TODO Auto-generated method stub
		return cancelled;
	}

	public Result getDefaultResult() {
		return getResult(LotteryConst.DEFAULT_IDENTIFIER);
	}

	@Override
	public Result getResult(int identifier) {
		// TODO Auto-generated method stub
		Result r = Result.NULL;
		if (state != State.SUCCESS) {
			return r;
		}

		Function func = getFunction();
		if (func == null) {
			return r;
		}

		r = func.getResult(identifier);
		if (r != Result.NULL) {
			if (r.invalid()) {
				r = Result.NULL;
			}
		}
		return r;
	}

	@Override
	public State execute(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		state = State.UNKNOWN;
		Function f = getFunction();
		if (f == null || parameter == null || parameter.size() == 0) {
			state = State.FAILED;
		} else {
			if (cancelled()) {
				state = State.CANCELLED;
			} else {
				state = f.invoke(parameter);
			}
		}
		return state;
	}

}
