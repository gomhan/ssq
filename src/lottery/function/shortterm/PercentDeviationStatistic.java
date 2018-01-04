package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.LotteryConst;

public class PercentDeviationStatistic extends AbstractFunction {

	public static class PercentNumber implements Comparable<PercentNumber> {
		int ball;
		boolean initial5;
		boolean last5;
		List<String> initial5Issue;
		List<String> last5Issue;

		public PercentNumber(int ball) {
			// TODO Auto-generated constructor stub
			this.ball = ball;
			initial5Issue = new ArrayList<String>();
			last5Issue = new ArrayList<String>();
		}

		public int getBall() {
			return ball;
		}

		public void setBall(int ball) {
			this.ball = ball;
		}

		public boolean isInitial5() {
			return initial5;
		}

		public void setInitial5(boolean initial5) {
			this.initial5 = initial5;
		}

		public boolean isLast5() {
			return last5;
		}

		public void setLast5(boolean last5) {
			this.last5 = last5;
		}

		public List<String> getInitial5Issue() {
			return initial5Issue;
		}

		public void setInitial5Issue(List<String> initial5Issue) {
			this.initial5Issue = initial5Issue;
		}

		public List<String> getLast5Issue() {
			return last5Issue;
		}

		public void setLast5Issue(List<String> last5Issue) {
			this.last5Issue = last5Issue;
		}

		@Override
		public int compareTo(PercentNumber o) {
			// TODO Auto-generated method stub
			return getBall() - o.getBall();
		}

	}

	PercentNumber[] percentNumbers;

	public PercentDeviationStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.SHORT_TERM_OFFSET | 8);
		name = "PercentDeviation";
		describe = "°Ù·Ö±ÈÆ«²î";
		percentNumbers = new PercentNumber[LotteryConst.RED_BALL_COUNT];
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (percentNumbers != null) {
			Arrays.fill(percentNumbers, null);
		}
		getDefaultResult().clearValue();
	}

	@Override
	protected State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		initialize();

		int mark = parameter.size() / 2;
		DoubleChromosphere dc;
		String issue;
		for (int i = 0; i < parameter.size(); i++) {
			dc = parameter.get(i);
			issue = dc.getIssue();
			if (i < mark) {
				initial5(dc.getRed1(), issue);
				initial5(dc.getRed2(), issue);
				initial5(dc.getRed3(), issue);
				initial5(dc.getRed4(), issue);
				initial5(dc.getRed5(), issue);
				initial5(dc.getRed6(), issue);
			} else {
				last5(dc.getRed1(), issue);
				last5(dc.getRed2(), issue);
				last5(dc.getRed3(), issue);
				last5(dc.getRed4(), issue);
				last5(dc.getRed5(), issue);
				last5(dc.getRed6(), issue);
			}
		}

		getDefaultResult().setValue(Arrays.asList(percentNumbers));
		return State.SUCCESS;
	}

	private void initialize() {
		for (int i = 0; i < percentNumbers.length; i++) {
			percentNumbers[i] = new PercentNumber(i + 1);
		}
	}

	private void initial5(int ball, String issue) {
		PercentNumber pn = percentNumbers[ball - 1];
		if (!pn.isInitial5()) {
			pn.setInitial5(true);
		}
		pn.getInitial5Issue().add(issue);
	}

	private void last5(int ball, String issue) {
		PercentNumber pn = percentNumbers[ball - 1];
		if (!pn.isLast5()) {
			pn.setLast5(true);
		}
		pn.getLast5Issue().add(issue);
	}

}
