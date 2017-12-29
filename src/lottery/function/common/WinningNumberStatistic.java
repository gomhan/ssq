package lottery.function.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.LotteryConst;

/**
 * �н��������ͳ��
 */
public class WinningNumberStatistic extends AbstractFunction {

	public static class Ball implements Comparable<Ball> {
		int number;
		int count;
		int position;
		String scale;

		public int getNumber() {
			return number;
		}

		public int getCount() {
			return count;
		}

		public int getPosition() {
			return position;
		}

		public String getScale() {
			return scale;
		}

		@Override
		public int compareTo(Ball o) {
			// TODO Auto-generated method stub
			return o.getCount() - getCount();
		}
	}

	private List<Ball> balls;

	public WinningNumberStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.COMMON_OFFSET | 2);
		name = "WinningNumberStatistic";
		describe = "�н�����ͳ��";
		balls = new ArrayList<Ball>(LotteryConst.RED_BALL_COUNT);
		getDefaultResult().setValue(balls);
	}

	@Override
	public State calculate(List<DoubleChromosphere> objs) {
		// TODO Auto-generated method stub
		reset();
		int[] ball = new int[LotteryConst.RED_BALL_COUNT + 1];
		int total = 0;
		for (DoubleChromosphere dc : objs) {
			ball[dc.getRed1()]++;
			total++;
			ball[dc.getRed2()]++;
			total++;
			ball[dc.getRed3()]++;
			total++;
			ball[dc.getRed4()]++;
			total++;
			ball[dc.getRed5()]++;
			total++;
			ball[dc.getRed6()]++;
			total++;
		}

		Ball b;
		double d;
		for (int i = 1; i < ball.length; i++) {
			b = new Ball();
			b.number = i;
			b.count = ball[i];
			d = (double) b.count / (double) total * 100.0d;
			b.scale = LotteryConst.DF.format(d);
			balls.add(b);
		}

		Collections.sort(balls);
		for (int i = 0; i < balls.size(); i++) {
			b = balls.get(i);
			b.position = i + 1;
		}

		return State.SUCCESS;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (balls != null) {
			balls.clear();
		}
	}

}
