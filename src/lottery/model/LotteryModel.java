package lottery.model;

import java.util.List;
import java.util.Map;

import lottery.itf.Function;

public class LotteryModel {

	private Map<Identifier, Function> functions;
	private List<DoubleChromosphere> lottery;

	public LotteryModel() {
		// TODO Auto-generated constructor stub
	}

	public List<DoubleChromosphere> getLottery() {
		return lottery;
	}

	public void setLottery(List<DoubleChromosphere> lottery) {
		this.lottery = lottery;
	}

}
