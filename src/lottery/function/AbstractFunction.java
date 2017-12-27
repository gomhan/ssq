package lottery.function;

import lottery.itf.Function;
import lottery.itf.Result;
import lottery.util.LotteryConst;

public abstract class AbstractFunction implements Function {

	protected String name;
	protected String describe;
	protected final int id;
	protected FunctionResult fr;

	public AbstractFunction() {
		// TODO Auto-generated constructor stub
		id = ID_GENERATOR.getAndIncrement();
		fr = new FunctionResult();
	}

	@Override
	public Result getResult(int identifier) {
		// TODO Auto-generated method stub
		if (identifier == LotteryConst.DEFAULT_IDENTIFIER) {
			return fr;
		}
		return Result.NULL;
	}

	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return describe;
	}
}
