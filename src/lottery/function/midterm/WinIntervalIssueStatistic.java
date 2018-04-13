package lottery.function.midterm;

import java.util.Arrays;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.Context;
import lottery.util.LotteryConst;

public class WinIntervalIssueStatistic extends AbstractFunction {

	public static class IntervalIssue {
		private int ball;
		private int[] missCount;
		private int winCount;
		private int lastMiss = -1;
		private int issueCount;

		public int getBall() {
			return ball;
		}

		public void setBall(int ball) {
			this.ball = ball;
		}

		public int[] getMissCount() {
			return missCount;
		}

		public void setMissCount(int[] missCount) {
			this.missCount = missCount;
		}

		public int getWinCount() {
			return winCount;
		}

		public void setWinCount(int winCount) {
			this.winCount = winCount;
		}

		public int getLastMiss() {
			return lastMiss;
		}

		public void setLastMiss(int lastMiss) {
			this.lastMiss = lastMiss;
		}

		public int getIssueCount() {
			return issueCount;
		}

		public void setIssueCount(int issueCount) {
			this.issueCount = issueCount;
		}

	}

	public static final String PROPERTY_SIZE = "size";
	IntervalIssue[] iis;
	int size;

	public WinIntervalIssueStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.MIDDLE_TERM_OFFSET | 3);
		name = "WinIntervalIssueStatistic";
		describe = "中奖间隔期数表";
	}

	@Override
	protected State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		initializeIntervalIssue();
		calculateWinCount();
		for (int i = 0; i < iis.length; i++) {
			handleIntervalIssue(iis[i]);
		}

		getDefaultResult().setValue(Arrays.asList(iis));
		return State.SUCCESS;
	}

	private void calculateWinCount() {
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		int[] ball = new int[LotteryConst.RED_BALL_COUNT + 1];
		for (DoubleChromosphere dc : objs) {
			ball[dc.getRed1()]++;
			ball[dc.getRed2()]++;
			ball[dc.getRed3()]++;
			ball[dc.getRed4()]++;
			ball[dc.getRed5()]++;
			ball[dc.getRed6()]++;
		}

		IntervalIssue ii;
		for (int i = 0; i < iis.length; i++) {
			ii = iis[i];
			ii.setWinCount(ball[ii.getBall()]);
		}
	}

	private void handleIntervalIssue(IntervalIssue ii) {
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		DoubleChromosphere dc;
		int xc = 0;
		int index;
		for (index = 0; index < objs.size(); index++) {
			dc = objs.get(index);
			if (hit(ii.getBall(), dc)) {
				xc++;
			}
			if (xc == size) {
				ii.setIssueCount(index + 1);
			} else if (xc == size + 1) {
				break;
			}
		}

		int miss = 0;
		index -= 1;
		xc -= 1;
		for (; index >= 0; index--) {
			dc = objs.get(index);
			if (hit(ii.getBall(), dc)) {
				ii.getMissCount()[size - xc] = miss;
				xc--;
				miss = 0;
			} else {
				miss++;
			}
		}

		if (miss != 0) {
			ii.setLastMiss(miss);
		}
	}

	private boolean hit(int ball, DoubleChromosphere dc) {
		boolean b = false;
		if (ball == dc.getRed1()) {
			b = true;
		} else if (ball == dc.getRed2()) {
			b = true;
		} else if (ball == dc.getRed3()) {
			b = true;
		} else if (ball == dc.getRed4()) {
			b = true;
		} else if (ball == dc.getRed5()) {
			b = true;
		} else if (ball == dc.getRed6()) {
			b = true;
		}
		return b;
	}

	@Override
	public boolean propertyInvalid() {
		// TODO Auto-generated method stub
		Object obj = getProperty(PROPERTY_SIZE);
		if (obj == null || !(obj instanceof Integer)) {
			size = 0;
		} else {
			size = ((Integer) obj).intValue();
		}
		return super.propertyInvalid();
	}

	private void initializeIntervalIssue() {
		iis = new IntervalIssue[LotteryConst.RED_BALL_COUNT];
		for (int i = 0; i < LotteryConst.RED_BALL_COUNT; i++) {
			iis[i] = new IntervalIssue();
			iis[i].setBall(i + 1);
			iis[i].setMissCount(new int[size]);
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (iis != null) {
			Arrays.fill(iis, null);
			iis = null;
		}
		getDefaultResult().clearValue();
	}

}
