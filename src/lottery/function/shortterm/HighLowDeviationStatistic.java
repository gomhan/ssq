package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.LotteryConst;

public class HighLowDeviationStatistic extends AbstractFunction {

	public static class LowHighNumber {
		String issue;
		String numberString;
		int lowNumber;
		int highNumber;

		public int getLowNumber() {
			return lowNumber;
		}

		public int getHighNumber() {
			return highNumber;
		}

		public String getIssue() {
			return issue;
		}

		public String getNumberString() {
			return numberString;
		}

	}

	private List<LowHighNumber> number;

	public HighLowDeviationStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.SHORT_TERM_OFFSET | 2);
		name = "HighLowDeviationStatistic";
		describe = "´óÐ¡Æ«²î";
		number = new ArrayList<LowHighNumber>();
		getDefaultResult().setValue(number);
	}

	@Override
	public State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		LowHighNumber oe;
		for (DoubleChromosphere dc : parameter) {
			oe = new LowHighNumber();
			oe.issue = dc.getIssue();
			oe.numberString = dc.getRedString();
			if (dc.getRed1() < LotteryConst.LOW_HIGH_SEPARATOR) {
				oe.lowNumber++;
			} else {
				oe.highNumber++;
			}
			if (dc.getRed2() < LotteryConst.LOW_HIGH_SEPARATOR) {
				oe.lowNumber++;
			} else {
				oe.highNumber++;
			}
			if (dc.getRed3() < LotteryConst.LOW_HIGH_SEPARATOR) {
				oe.lowNumber++;
			} else {
				oe.highNumber++;
			}
			if (dc.getRed4() < LotteryConst.LOW_HIGH_SEPARATOR) {
				oe.lowNumber++;
			} else {
				oe.highNumber++;
			}
			if (dc.getRed5() < LotteryConst.LOW_HIGH_SEPARATOR) {
				oe.lowNumber++;
			} else {
				oe.highNumber++;
			}
			if (dc.getRed6() < LotteryConst.LOW_HIGH_SEPARATOR) {
				oe.lowNumber++;
			} else {
				oe.highNumber++;
			}
			number.add(oe);
		}
		
		return State.SUCCESS;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (number != null) {
			number.clear();
		}
	}

}
