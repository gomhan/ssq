package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.FunctionResult;
import lottery.itf.Function;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.model.State;
import lottery.util.LotteryConst;

/**
 * ÆæÅ¼Æ«²î
 */
public class Function3 implements Function {

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

	public final static String NAME = "Function3";
	private final int id;
	private List<OddEvenNumber> number;
	private FunctionResult fr;

	public Function3() {
		// TODO Auto-generated constructor stub
		id = ID_GENERATOR.getAndIncrement();
		number = new ArrayList<Function3.OddEvenNumber>();
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
	public State invoke(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		clearValue();
		OddEvenNumber oe;
		for (DoubleChromosphere dc : parameter) {
			oe = new OddEvenNumber();
			oe.issue = dc.getIssue();
			oe.numberString = dc.getRedString();
			int even = 0;
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
