package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.Context;
import lottery.util.LotteryConst;

public class PopularDeviationStatistic extends AbstractFunction {

	public static class Popular implements Comparable<Popular> {
		DoubleChromosphere dc;
		int[] ballMissCount = new int[6];
		int[] ballMissMap = new int[LotteryConst.RED_BALL_COUNT + 1];
		int missCount;
		int hotNumberCount;
		String averageMiss;

		public DoubleChromosphere getDc() {
			return dc;
		}

		public void setDc(DoubleChromosphere dc) {
			this.dc = dc;
		}

		public int[] getBallMissCount() {
			return ballMissCount;
		}

		public void setBallMissCount(int[] missNumber) {
			this.ballMissCount = missNumber;
		}

		public int[] getBallMissMap() {
			return ballMissMap;
		}

		public void setBallMissMap(int[] ballMissMap) {
			this.ballMissMap = ballMissMap;
		}

		public int getMissCount() {
			return missCount;
		}

		public void setMissCount(int missCount) {
			this.missCount = missCount;
		}

		public int getHotNumberCount() {
			return hotNumberCount;
		}

		public void setHotNumberCount(int hotNumberCount) {
			this.hotNumberCount = hotNumberCount;
		}

		public String getAverageMiss() {
			return averageMiss;
		}

		public void setAverageMiss(String averageMiss) {
			this.averageMiss = averageMiss;
		}

		@Override
		public int compareTo(Popular o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	public static final String PROPERTY_OFFSET = "offset";
	private List<Popular> popular;
	private int offset;

	public PopularDeviationStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.SHORT_TERM_OFFSET | 5);
		name = "PopularDeviationStatistic";
		describe = "¿‰»»√≈∆´≤Ó";
		popular = new ArrayList<Popular>();
		getDefaultResult().setValue(popular);
	}

	@Override
	public boolean propertyInvalid() {
		// TODO Auto-generated method stub
		Object obj = properties.get(PROPERTY_OFFSET);
		if (obj == null || !(obj instanceof Integer)) {
			offset = 0;
		} else {
			offset = ((Integer) obj).intValue();
		}
		return super.propertyInvalid();
	}

	@Override
	public State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		List<DoubleChromosphere> dcs = Context.getInstance().getLotteryList();
		Popular p;
		DoubleChromosphere dc;
		int lastHitIndex;
		int issueNo = offset;
		for (int i = 0; i < parameter.size(); i++, issueNo++) {
			dc = parameter.get(i);
			p = new Popular();
			p.setDc(dc);

			lastHitIndex = lastHitIndex(issueNo + 1, dc.getRed1(), dcs);
			lastHitIndex = lastHitIndex - issueNo - 1;
			p.getBallMissCount()[0] = lastHitIndex;
			p.getBallMissMap()[dc.getRed1()] = lastHitIndex;

			lastHitIndex = lastHitIndex(issueNo + 1, dc.getRed2(), dcs);
			lastHitIndex = lastHitIndex - issueNo - 1;
			p.getBallMissCount()[1] = lastHitIndex;
			p.getBallMissMap()[dc.getRed2()] = lastHitIndex;

			lastHitIndex = lastHitIndex(issueNo + 1, dc.getRed3(), dcs);
			lastHitIndex = lastHitIndex - issueNo - 1;
			p.getBallMissCount()[2] = lastHitIndex;
			p.getBallMissMap()[dc.getRed3()] = lastHitIndex;

			lastHitIndex = lastHitIndex(issueNo + 1, dc.getRed4(), dcs);
			lastHitIndex = lastHitIndex - issueNo - 1;
			p.getBallMissCount()[3] = lastHitIndex;
			p.getBallMissMap()[dc.getRed4()] = lastHitIndex;

			lastHitIndex = lastHitIndex(issueNo + 1, dc.getRed5(), dcs);
			lastHitIndex = lastHitIndex - issueNo - 1;
			p.getBallMissCount()[4] = lastHitIndex;
			p.getBallMissMap()[dc.getRed5()] = lastHitIndex;

			lastHitIndex = lastHitIndex(issueNo + 1, dc.getRed6(), dcs);
			lastHitIndex = lastHitIndex - issueNo - 1;
			p.getBallMissCount()[5] = lastHitIndex;
			p.getBallMissMap()[dc.getRed6()] = lastHitIndex;

			calculate(p);
			popular.add(p);
		}

		return State.SUCCESS;
	}

	public int lastHitIndex(int issueNo, byte ball, List<DoubleChromosphere> dcs) {
		if (issueNo >= dcs.size()) {
			return issueNo;
		}
		if (ball == dcs.get(issueNo).getRed1()
				|| ball == dcs.get(issueNo).getRed2()
				|| ball == dcs.get(issueNo).getRed3()
				|| ball == dcs.get(issueNo).getRed4()
				|| ball == dcs.get(issueNo).getRed5()
				|| ball == dcs.get(issueNo).getRed6()) {
			return issueNo;
		} else {
			return lastHitIndex(issueNo + 1, ball, dcs);
		}
	}

	private void calculate(Popular p) {
		int hotNumberCount = 0;
		int missCount = 0;
		for (int i : p.ballMissCount) {
			if (i < 10) {
				hotNumberCount++;
			}
			missCount += i;
		}
		p.setHotNumberCount(hotNumberCount);
		p.setMissCount(missCount);
		double average = (missCount * 1.0d) / 6.0d;
		p.setAverageMiss(LotteryConst.DF.format(average));
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (popular != null) {
			popular.clear();
		}
	}

}
