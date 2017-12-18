package lottery.function.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lottery.function.FunctionResult;
import lottery.itf.Function;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.model.State;
import lottery.util.LotteryConst;

/**
 * 和数值统计
 */
public class Function1 implements Function {

	public static class SummationCount implements Comparable<SummationCount> {
		Integer summation;
		Integer count;
		List<DoubleChromosphere> dc;

		public Integer getSummation() {
			return summation;
		}

		public Integer getCount() {
			return count;
		}

		public List<DoubleChromosphere> getDc() {
			return dc;
		}

		@Override
		public int compareTo(SummationCount o) {
			// TODO Auto-generated method stub
			return getSummation() - o.getSummation();
		}
	}

	public final static String NAME = "Function1";
	private final int id;
	private List<SummationCount> scs;
	private FunctionResult fr;

	public Function1() {
		// TODO Auto-generated constructor stub
		scs = new ArrayList<>();
		id = ID_GENERATOR.getAndIncrement();
		fr = new FunctionResult();
	}

	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	@Override
	public State invoke(List<DoubleChromosphere> objs) {
		// TODO Auto-generated method stub
		clearValue();
		List<DoubleChromosphere> lottery = new ArrayList<>(objs);
		Collections.sort(lottery);

		// locally variable
		int min = lottery.get(0).getSummation();
		int max = lottery.get(lottery.size() - 1).getSummation();
		int count;
		int summation;
		SummationCount sc;
		DoubleChromosphere dc;

		// initialize summation count
		for (int i = min; i <= max; i++) {
			sc = new SummationCount();
			sc.count = 0;
			sc.dc = new ArrayList<>();
			sc.summation = i;
			scs.add(sc);
		}

		// get double chromosphere
		for (int i = 0; i < lottery.size(); i++) {
			dc = lottery.get(i);
			summation = dc.getSummation();
			sc = getSc(summation);
			if (sc == null) {
				continue;
			}
			sc.dc.add(dc);
			count = sc.count;
			sc.count = count + 1;
		}

		fr.setValue(scs);
		return State.SUCCESS;
	}

	private SummationCount getSc(int sum) {
		for (SummationCount sc : scs) {
			if (sc.summation == sum) {
				return sc;
			}
		}
		return null;
	}

	@Override
	public void clearValue() {
		// TODO Auto-generated method stub
		if (scs != null) {
			scs.clear();
		}
	}

	@Override
	public Result getResult(int identifier) {
		// TODO Auto-generated method stub
		if (identifier == LotteryConst.DEFAULT_IDENTIFIER) {
			return fr;
		}
		return Result.NULL;
	}

}
