package lottery.function.midterm;

import java.util.Arrays;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.Context;
import lottery.util.LotteryConst;

public class MiniLotteryIssueStatistic extends AbstractFunction {

	public static class MiniLotteryIssue {
		private int ball;
		private String[] issue;
		private int[] status;
		private int missCount;
		private int winCount;

		public int getBall() {
			return ball;
		}

		public void setBall(int ball) {
			this.ball = ball;
		}

		public String[] getIssue() {
			return issue;
		}

		public void setIssue(String[] issue) {
			this.issue = issue;
		}

		public int[] getStatus() {
			return status;
		}

		public void setStatus(int[] status) {
			this.status = status;
		}

		public int getMissCount() {
			return missCount;
		}

		public void setMissCount(int missCount) {
			this.missCount = missCount;
		}

		public int getWinCount() {
			return winCount;
		}

		public void setWinCount(int winCount) {
			this.winCount = winCount;
		}

	}

	MiniLotteryIssue[] mlis;

	public MiniLotteryIssueStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.MIDDLE_TERM_OFFSET | 2);
		name = "MiniLotteryIssueStatistic";
		describe = "小型彩票期数表";
	}

	@Override
	protected State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		initMiniLotteryIssue(parameter.size());
		calculateWinCount();
		fillIssue(parameter);
		MiniLotteryIssue mli;
		DoubleChromosphere dc;
		int size = parameter.size() - 1;
		for (int i = size; i >= 0; i--) {
			dc = parameter.get(i);
			mli = mlis[dc.getRed1() - 1];
			fillMiniLotteryIssue(size - i, mli, dc);
			mli = mlis[dc.getRed2() - 1];
			fillMiniLotteryIssue(size - i, mli, dc);
			mli = mlis[dc.getRed3() - 1];
			fillMiniLotteryIssue(size - i, mli, dc);
			mli = mlis[dc.getRed4() - 1];
			fillMiniLotteryIssue(size - i, mli, dc);
			mli = mlis[dc.getRed5() - 1];
			fillMiniLotteryIssue(size - i, mli, dc);
			mli = mlis[dc.getRed6() - 1];
			fillMiniLotteryIssue(size - i, mli, dc);
		}
		calculateMissCount();
		getDefaultResult().setValue(Arrays.asList(mlis));
		return State.SUCCESS;
	}

	private void fillMiniLotteryIssue(int index, MiniLotteryIssue mli,
			DoubleChromosphere dc) {
		mli.getIssue()[index] = dc.getIssue();
		mli.getStatus()[index] = 1;
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

		MiniLotteryIssue mli;
		for (int i = 0; i < mlis.length; i++) {
			mli = mlis[i];
			mli.setWinCount(ball[mli.getBall()]);
		}
	}

	private void fillIssue(List<DoubleChromosphere> objs) {
		MiniLotteryIssue mli;
		String[] issue;
		int size = objs.size() - 1;
		for (int i = 0; i < mlis.length; i++) {
			mli = mlis[i];
			issue = mli.getIssue();
			for (int j = 0; j < issue.length; j++) {
				issue[j] = objs.get(size - j).getIssue();
			}
		}

	}

	private void calculateMissCount() {
		MiniLotteryIssue mli;
		int[] status;
		int lastWinIndex;
		for (int i = 0; i < mlis.length; i++) {
			mli = mlis[i];
			status = mli.getStatus();
			lastWinIndex = getLastWinIndex(mli) + 1;
			if (lastWinIndex >= status.length) {
				continue;
			}
			Arrays.fill(status, lastWinIndex, status.length, -2);
			mli.setMissCount(status.length - lastWinIndex);
		}
	}

	private int getLastWinIndex(MiniLotteryIssue mli) {
		for (int i = mli.getStatus().length - 1; i >= 0; i--) {
			if (mli.getStatus()[i] == 1) {
				return i;
			}
		}
		return -1;
	}

	private void initMiniLotteryIssue(int size) {
		mlis = new MiniLotteryIssue[LotteryConst.RED_BALL_COUNT];
		for (int i = 0; i < LotteryConst.RED_BALL_COUNT; i++) {
			mlis[i] = new MiniLotteryIssue();
			mlis[i].setBall(i + 1);
			mlis[i].setIssue(new String[size]);
			mlis[i].setStatus(new int[size]);
			Arrays.fill(mlis[i].getStatus(), -1); // init with -1
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (mlis != null) {
			Arrays.fill(mlis, null);
			mlis = null;
		}
		getDefaultResult().clearValue();
	}
}
