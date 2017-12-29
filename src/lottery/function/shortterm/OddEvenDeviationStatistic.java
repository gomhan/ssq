package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.LotteryConst;

/**
 * ÆæÅ¼Æ«²î
 */
public class OddEvenDeviationStatistic extends AbstractFunction {

	public static class OddEvenNumber {
		String issue;
		String numberString;
		int oddNumber;
		int evenNumber;

		public int getOddNumber() {
			return oddNumber;
		}

		public int getEvenNumber() {
			return evenNumber;
		}

		public String getIssue() {
			return issue;
		}

		public String getNumberString() {
			return numberString;
		}

	}

	private List<OddEvenNumber> number;

	public OddEvenDeviationStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.SHORT_TERM_OFFSET | 1);
		name = "OddEvenDeviationStatistic";
		describe = "ÆæÅ¼Æ«²î";
		number = new ArrayList<OddEvenNumber>();
		getDefaultResult().setValue(number);
	}

	@Override
	public State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		reset();
		OddEvenNumber oe;
		for (DoubleChromosphere dc : parameter) {
			oe = new OddEvenNumber();
			oe.issue = dc.getIssue();
			oe.numberString = dc.getRedString();
			if (dc.getRed1() % 2 == 0) {
				oe.evenNumber++;
			} else {
				oe.oddNumber++;
			}
			if (dc.getRed2() % 2 == 0) {
				oe.evenNumber++;
			} else {
				oe.oddNumber++;
			}
			if (dc.getRed3() % 2 == 0) {
				oe.evenNumber++;
			} else {
				oe.oddNumber++;
			}
			if (dc.getRed4() % 2 == 0) {
				oe.evenNumber++;
			} else {
				oe.oddNumber++;
			}
			if (dc.getRed5() % 2 == 0) {
				oe.evenNumber++;
			} else {
				oe.oddNumber++;
			}
			if (dc.getRed6() % 2 == 0) {
				oe.evenNumber++;
			} else {
				oe.oddNumber++;
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
