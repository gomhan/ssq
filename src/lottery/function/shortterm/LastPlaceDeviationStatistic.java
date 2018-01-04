package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.LotteryConst;

public class LastPlaceDeviationStatistic extends AbstractFunction {

	public static class LastNumber implements Comparable<LastNumber> {
		int lastNumber;
		int count;
		List<Integer> numbers;

		public LastNumber() {
			numbers = new ArrayList<Integer>();
		}

		@Override
		public int compareTo(LastNumber o) {
			// TODO Auto-generated method stub
			return count - o.getCount();
		}

		public int getLastNumber() {
			return lastNumber;
		}

		public void setLastNumber(int lastNumber) {
			this.lastNumber = lastNumber;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public List<Integer> getNumbers() {
			return numbers;
		}

		public void setNumbers(List<Integer> numbers) {
			this.numbers = numbers;
		}
	}

	LastNumber[] lastNumbers;

	public LastPlaceDeviationStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.SHORT_TERM_OFFSET | 7);
		name = "LastPlaceDeviationStatistic";
		describe = "Ä©Î»Êý×ÖÆ«²î";
		lastNumbers = new LastNumber[LotteryConst.LAST_COUNT];
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (lastNumbers != null) {
			Arrays.fill(lastNumbers, null);
		}
		getDefaultResult().clearValue();
	}

	@Override
	protected State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		initialize();

		DoubleChromosphere dc;
		for (int i = 0; i < parameter.size(); i++) {
			dc = parameter.get(i);
			calculateLastNumber(dc.getRed1());
			calculateLastNumber(dc.getRed2());
			calculateLastNumber(dc.getRed3());
			calculateLastNumber(dc.getRed4());
			calculateLastNumber(dc.getRed5());
			calculateLastNumber(dc.getRed6());
		}

		getDefaultResult().setValue(Arrays.asList(lastNumbers));
		return State.SUCCESS;
	}
	
	private void initialize() {
		for (int i = 0; i < lastNumbers.length; i++) {
			lastNumbers[i] = new LastNumber();
			lastNumbers[i].setLastNumber(i);
		}
	}

	private void calculateLastNumber(int ball) {
		int lpNumber = lastPlaceNumber(ball);
		LastNumber ln = lastNumbers[lpNumber];
		ln.setCount(ln.getCount() + 1);
		ln.getNumbers().add(ball);
	}

	private int lastPlaceNumber(int ball) {
		if (ball < 10) {
			return ball;
		} else if (ball < 20) {
			return ball - 10;
		} else if (ball < 30) {
			return ball - 20;
		} else if (ball < 40) {
			return ball - 30;
		}
		return -1;
	}
}
