package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.FunctionResult;
import lottery.itf.Function;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.model.State;

/**
 * ÆæÅ¼Æ«²î
 */
public class Function3 implements Function {

	public class OddEvenNumber {
		int issue;
		String numberString;
		int oddNumber;
		int evenNumber;

		public int getOddNumber() {
			return oddNumber;
		}

		public int getEvenNumber() {
			return evenNumber;
		}

		public int getIssue() {
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
		return Result.NULL;
	}

	@Override
	public State invoke(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		clearValue();
		return null;
	}

	@Override
	public void clearValue() {
		// TODO Auto-generated method stub

	}

}
