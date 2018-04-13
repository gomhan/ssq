package lottery.function.midterm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.LotteryConst;

public class LotteryIssueStatistic extends AbstractFunction {

	public static class LotteryIssue {
		private String issue;
		private String time;
		private int[] ball;

		public LotteryIssue() {
			// TODO Auto-generated constructor stub
			ball = new int[LotteryConst.BALL_COUNT];
		}

		public String getIssue() {
			return issue;
		}

		public void setIssue(String issue) {
			this.issue = issue;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public int[] getBall() {
			return ball;
		}

		public void setBall(int[] ball) {
			this.ball = ball;
		}

	}

	private List<LotteryIssue> lis;

	public LotteryIssueStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.MIDDLE_TERM_OFFSET | 1);
		name = "LotteryIssueStatistic";
		describe = "²ÊÆ±ÆÚÊý±í";
		lis = new ArrayList<LotteryIssue>();
		getDefaultResult().setValue(lis);
	}

	@Override
	protected State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();

		LotteryIssue li;
		DoubleChromosphere dc;
		int row = 0;
		int[] ball;
		for (int i = parameter.size() - 1; i >= 0; i--) {
			dc = parameter.get(i);

			li = new LotteryIssue();
			li.setTime(dc.getTime());
			li.setIssue(dc.getIssue());
			lis.add(li); // add to list before calculate

			calculate(row++);
			ball = li.getBall();
			hitIndex(1, dc.getRed1() - 1, ball);
			hitIndex(2, dc.getRed2() - 1, ball);
			hitIndex(3, dc.getRed3() - 1, ball);
			hitIndex(4, dc.getRed4() - 1, ball);
			hitIndex(5, dc.getRed5() - 1, ball);
			hitIndex(6, dc.getRed6() - 1, ball);
			hitIndex(7, dc.getBlue() - 1, ball);

		}

		return State.SUCCESS;
	}

	private void calculate(int row) {
		if (row == 0) {
			Arrays.fill(lis.get(row).getBall(), 1);
			return;
		}
		LotteryIssue current = lis.get(row);
		LotteryIssue last = lis.get(row - 1);
		int v1;
		for (int i = 0; i < LotteryConst.BALL_COUNT; i++) {
			v1 = last.getBall()[i];
			if (v1 == -1) {
				current.getBall()[i] = 1;
			} else {
				current.getBall()[i] = v1 + 1;
			}
		}
	}

	/** if hit set value to -1 */
	private void hitIndex(int order, int index, int[] array) {
		if (order < 7) {
			array[index] = -1;
		} else {
			array[index + LotteryConst.RED_BALL_COUNT] = -1;
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (lis != null) {
			lis.clear();
		}
	}
}
