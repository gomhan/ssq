package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.State;
import lottery.util.LotteryConst;

public class Function5 extends AbstractFunction {

	public static class SummationDeviation implements
			Comparable<SummationDeviation> {
		Integer summation;
		Integer sd;// summation deviation
		String issue;

		public Integer getSummation() {
			return summation;
		}

		public Integer getSd() {
			return sd;
		}

		public String getIssue() {
			return issue;
		}

		@Override
		public int compareTo(SummationDeviation o) {
			// TODO Auto-generated method stub
			return getSummation() - o.getSummation();
		}
	}

	private List<SummationDeviation> sds;

	public Function5() {
		// TODO Auto-generated constructor stub
		super();
		name = "Function5";
		describe = "ºÍÊýÖµÆ«²î";
		sds = new ArrayList<SummationDeviation>();
	}

	@Override
	public State invoke(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		clearValue();
		SummationDeviation sd;
		for (DoubleChromosphere dc : parameter) {
			sd = new SummationDeviation();
			sd.issue = dc.getIssue();
			sd.summation = dc.getSummation();
			sd.sd = sd.summation - LotteryConst.DEVIATION_BEST;
			sds.add(sd);
		}
		fr.setValue(sds);
		return State.SUCCESS;
	}

	@Override
	public void clearValue() {
		// TODO Auto-generated method stub
		if (sds != null) {
			sds.clear();
		}
	}

}
