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
 * 中奖数字情况统计
 */
public class Function2 implements Function {

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

	public final static String NAME = "Function2";
	private final int id;
	private List<Ball> balls;
	private FunctionResult fr;

	public Function2() {
		// TODO Auto-generated constructor stub
		id = ID_GENERATOR.getAndIncrement();
		balls = new ArrayList<Ball>(LotteryConst.RED_BALL_COUNT);
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
	public Result getResult(int identifier) {
		// TODO Auto-generated method stub
		if (identifier == LotteryConst.DEFAULT_IDENTIFIER) {
			return fr;
		}
		return Result.NULL;
	}

	@Override
	public State invoke(List<DoubleChromosphere> objs) {
		// TODO Auto-generated method stub
		clearValue();
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

		fr.setValue(balls);
		return State.SUCCESS;
	}

	@Override
	public void clearValue() {
		// TODO Auto-generated method stub
		if (balls != null) {
			balls.clear();
		}
	}

}
