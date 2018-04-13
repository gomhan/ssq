package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.function.ExecutionResult;
import lottery.function.shortterm.PopularDeviationStatistic.Popular;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.Context;
import lottery.util.LotteryConst;

public class MissDeviationStatistic extends AbstractFunction {

	public static class MissNumber implements Comparable<MissNumber> {
		int missCount;
		int missNumberCount;
		List<Byte> possibleNumber;

		public MissNumber() {
			// TODO Auto-generated constructor stub
			possibleNumber = new ArrayList<Byte>();
		}

		public int getMissCount() {
			return missCount;
		}

		public void setMissCount(int missCount) {
			this.missCount = missCount;
		}

		public int getMissNumberCount() {
			return missNumberCount;
		}

		public void setMissNumberCount(int missNumberCount) {
			this.missNumberCount = missNumberCount;
		}

		public List<Byte> getPossibleNumber() {
			return possibleNumber;
		}

		public void setPossibleNumber(List<Byte> possibleNumber) {
			this.possibleNumber = possibleNumber;
		}

		@Override
		public int compareTo(MissNumber o) {
			// TODO Auto-generated method stub
			if (getMissNumberCount() != o.getMissNumberCount()) {
				return getMissNumberCount() - o.getMissNumberCount();
			} else {
				return getMissCount() - o.getMissCount();
			}
		}
	}

	public static final String PROPERTY_MISS_COUNT = "missCount";
	public static final String PROPERTY_OFFSET = "offset";
	public static final String PROPERTY_LENGTH = "length";
	private PopularDeviationStatistic popularFunction;
	private int missCount;
	private int offset;
	private int length;
	private MissNumber[] missNumbers;

	public MissDeviationStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.SHORT_TERM_OFFSET | 6);
		name = "MissDeviationStatistic";
		describe = "ÒÅÂ©Æ«²î";
		popularFunction = new PopularDeviationStatistic();
	}

	@Override
	public State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		initiateMissNumber();

		popularFunction.setProperty(PopularDeviationStatistic.PROPERTY_OFFSET,
				offset);
		State s = popularFunction.invoke(parameter);
		if (s != State.SUCCESS) {
			return s;
		}
		List<Popular> value = (List<Popular>) ((ExecutionResult) (popularFunction
				.getResult(LotteryConst.DEFAULT_IDENTIFIER))).getValue();
		MissNumber missNumber;
		int[] ballMissCount;
		int missCount;
		int missNumberCount;
		for (int i = 0; i < value.size(); i++) {
			ballMissCount = value.get(i).getBallMissCount();
			for (int j = 0; j < ballMissCount.length; j++) {
				missCount = ballMissCount[j];
				if (missCount + 1 > missNumbers.length) {
					continue;
				}
				missNumber = missNumbers[missCount];
				if (missNumber == null) {
					missNumber = new MissNumber();
					missNumbers[missCount] = missNumber;
				}
				missNumberCount = missNumber.getMissNumberCount();
				missNumber.setMissNumberCount(missNumberCount + 1);
			}
		}

		// handle missCount == 0
		DoubleChromosphere dc = parameter.get(0);
		missNumber = missNumbers[0];
		missNumber.getPossibleNumber().add(dc.getRed1());
		missNumber.getPossibleNumber().add(dc.getRed2());
		missNumber.getPossibleNumber().add(dc.getRed3());
		missNumber.getPossibleNumber().add(dc.getRed4());
		missNumber.getPossibleNumber().add(dc.getRed5());
		missNumber.getPossibleNumber().add(dc.getRed6());
		// handle other missCount
		List<DoubleChromosphere> dcs = Context.getInstance().getLotteryList();
		int start = offset + 1;
		int end = start + length;
		for (int i = start; i < end; i++) {
			dc = dcs.get(i);
			missCount = i - offset;
			if (missCount + 1 > missNumbers.length) {
				continue;
			}
			missNumber = missNumbers[missCount];
			calculatePossibleNumber(i - 1, dc.getRed1(), dcs, missNumber);
			calculatePossibleNumber(i - 1, dc.getRed2(), dcs, missNumber);
			calculatePossibleNumber(i - 1, dc.getRed3(), dcs, missNumber);
			calculatePossibleNumber(i - 1, dc.getRed4(), dcs, missNumber);
			calculatePossibleNumber(i - 1, dc.getRed5(), dcs, missNumber);
			calculatePossibleNumber(i - 1, dc.getRed6(), dcs, missNumber);
		}

		Arrays.sort(missNumbers);
		popularFunction.reset();

		getDefaultResult().setValue(Arrays.asList(missNumbers));
		return s;
	}

	private void calculatePossibleNumber(int issueNo, byte ball,
			List<DoubleChromosphere> dcs, MissNumber missNumber) {
		boolean isLotteryNumber = isLotteryNumber(issueNo, ball, dcs);
		if (!isLotteryNumber) {
			missNumber.getPossibleNumber().add(ball);
		}
	}

	private boolean isLotteryNumber(int issueNo, byte ball,
			List<DoubleChromosphere> dcs) {
		if (issueNo < offset) {
			return false;
		}
		if (ball == dcs.get(issueNo).getRed1()
				|| ball == dcs.get(issueNo).getRed2()
				|| ball == dcs.get(issueNo).getRed3()
				|| ball == dcs.get(issueNo).getRed4()
				|| ball == dcs.get(issueNo).getRed5()
				|| ball == dcs.get(issueNo).getRed6()) {
			return true;
		} else {
			return isLotteryNumber(issueNo - 1, ball, dcs);
		}
	}

	private void initiateMissNumber() {
		missNumbers = new MissNumber[missCount + 1];
		for (int i = 0; i < missNumbers.length; i++) {
			missNumbers[i] = new MissNumber();
			missNumbers[i].setMissCount(i);
		}
	}

	@Override
	public boolean propertyInvalid() {
		// TODO Auto-generated method stub
		Object obj = getProperty(PROPERTY_MISS_COUNT);
		if (obj == null || !(obj instanceof Integer)) {
			missCount = 5;
		} else {
			missCount = ((Integer) obj).intValue();
		}

		obj = getProperty(PROPERTY_OFFSET);
		if (obj == null || !(obj instanceof Integer)) {
			offset = 0;
		} else {
			offset = ((Integer) obj).intValue();
		}

		obj = getProperty(PROPERTY_LENGTH);
		if (obj == null || !(obj instanceof Integer)) {
			length = 10;
		} else {
			length = ((Integer) obj).intValue();
		}

		return super.propertyInvalid();
	}

	@Override
	public void reset() {
		missNumbers = null;
		getDefaultResult().clearValue();
	}

}
