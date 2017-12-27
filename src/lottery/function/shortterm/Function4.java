package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.State;
import lottery.util.LotteryConst;

public class Function4 extends AbstractFunction {

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

	public Function4() {
		// TODO Auto-generated constructor stub
		super();
		name = "Function4";
		describe = "´óÐ¡Æ«²î";
		number = new ArrayList<LowHighNumber>();
	}

	@Override
	public State invoke(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		clearValue();
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
		fr.setValue(number);
		return State.SUCCESS;
	}

	@Override
	public void clearValue() {
		// TODO Auto-generated method stub
		if (number != null) {
			number.clear();
		}
	}

}
