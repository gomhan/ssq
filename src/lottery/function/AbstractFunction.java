package lottery.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lottery.itf.Function;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.model.ObservableBean;
import lottery.model.State;
import lottery.util.LotteryConst;

public abstract class AbstractFunction extends ObservableBean implements
		Function {

	protected Object id;
	protected String name;
	protected String describe;
	
	private Map<String, Object> properties;
	private Map<Integer, Result> results;

	public AbstractFunction() {
		// TODO Auto-generated constructor stub
		super();
		properties = new HashMap<String, Object>();
		results = new HashMap<Integer, Result>();
		Result r = new ExecutionResult(LotteryConst.DEFAULT_IDENTIFIER);
		setResult(LotteryConst.DEFAULT_IDENTIFIER, r);
	}

	public Object getId() {
		return id;
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		Object old = this.id;
		this.id = id;
		firePropertyChange("id", old, id);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		Object old = this.name;
		this.name = name;
		firePropertyChange("name", old, name);
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		Object old = this.describe;
		this.describe = describe;
		firePropertyChange("describe", old, describe);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return describe;
	}

	@Override
	public Object getProperty(String key) {
		// TODO Auto-generated method stub
		return properties.get(key);
	}

	@Override
	public void setProperty(String key, Object value) {
		// TODO Auto-generated method stub
		Object old = properties.get(key);
		properties.put(key, value);
		firePropertyChange(key, old, value);
	}

	@Override
	public Result getResult(int identifier) {
		// TODO Auto-generated method stub
		Result fr = results.get(identifier);
		if (fr == null) {
			fr = Result.NULL;
		}
		return fr;
	}

	@Override
	public void setResult(int identifier, Result result) {
		// TODO Auto-generated method stub
		Result old = getResult(identifier);
		results.put(identifier, result);
		firePropertyChange("result", old, result);
	}

	@Override
	public Result removeResult(int identifier) {
		// TODO Auto-generated method stub
		Result r = results.remove(identifier);
		return r;
	}

	protected Result getDefaultResult() {
		Result r = getResult(LotteryConst.DEFAULT_IDENTIFIER);
		if (!(r instanceof Result)) {
			return null;
		}
		return (Result) r;
	}

	@Override
	public boolean propertyInvalid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public State invoke(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		if (propertyInvalid()) {
			return State.FAILED;
		}
		return calculate(parameter);
	}

	@Override
	public void clearResult() {
		// TODO Auto-generated method stub
		for (Result r : results.values()) {
			if (r != null && r != Result.NULL) {
				r.clearValue();
			}
		}
		results.clear();
	}

	/**
	 * calculate with parameter.
	 * 
	 * @param parameter
	 * @return
	 */
	protected abstract State calculate(List<DoubleChromosphere> parameter);

}
