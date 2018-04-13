package lottery.function.shortterm;

import java.util.ArrayList;
import java.util.List;

import lottery.function.AbstractFunction;
import lottery.model.DoubleChromosphere;
import lottery.model.Identifier;
import lottery.model.State;
import lottery.util.LotteryConst;

public class NumberAreaDeviationStatistic extends AbstractFunction {

	public static class Area implements Comparable<Area> {
		int startNumber;
		int endNumber;
		int[] numberCount;
		String[] issue;
		String areaName;

		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public int getStartNumber() {
			return startNumber;
		}

		public void setStartNumber(int startNumber) {
			this.startNumber = startNumber;
		}

		public int getEndNumber() {
			return endNumber;
		}

		public void setEndNumber(int endNumber) {
			this.endNumber = endNumber;
		}

		public int[] getNumberCount() {
			return numberCount;
		}

		public void setNumberCount(int[] numberCount) {
			this.numberCount = numberCount;
		}

		public String[] getIssue() {
			return issue;
		}

		public void setIssue(String[] issue) {
			this.issue = issue;
		}

		public boolean contain(int ball) {
			return ball >= startNumber && ball <= endNumber;
		}

		@Override
		public int compareTo(Area o) {
			// TODO Auto-generated method stub
			return getAreaName().compareTo(o.getAreaName());
		}
	}

	public static final String PROPERTY_AREA_COUNT = "areaCount";
	private List<Area> areas;
	private int areaCount;
	private int issueCount;

	public NumberAreaDeviationStatistic() {
		// TODO Auto-generated constructor stub
		super();
		id = Identifier.createIdentifier(LotteryConst.SHORT_TERM_OFFSET | 4);
		name = "NumberAreaDeviationStatistic";
		describe = "Êý×ÖÇø¼äÆ«²î";
		areaCount = 11;
		areas = new ArrayList<Area>();
		getDefaultResult().setValue(areas);
	}

	@Override
	public boolean propertyInvalid() {
		// TODO Auto-generated method stub
		Object obj = getProperty(PROPERTY_AREA_COUNT);
		if (obj == null || !(obj instanceof Integer)) {
			areaCount = 0;
		} else {
			areaCount = ((Integer) obj).intValue();
		}
		return super.propertyInvalid();
	}

	@Override
	public State calculate(List<DoubleChromosphere> parameter) {
		// TODO Auto-generated method stub
		issueCount = parameter.size();
		reset();
		calculateArea();
		DoubleChromosphere dc;
		for (int i = 0; i < issueCount; i++) {
			dc = parameter.get(i);
			insertToArea(dc, i);
		}

		return State.SUCCESS;
	}

	private void insertToArea(DoubleChromosphere dc, int index) {
		int count;
		int[] numberCount;
		String[] issue;
		String title = new StringBuilder().append(index + 1).append(" - [")
				.append(dc.getIssue()).append("]").toString();
		for (Area area : areas) {
			issue = area.getIssue();
			numberCount = area.getNumberCount();
			if (area.contain(dc.getRed1())) {
				count = numberCount[index];
				numberCount[index] = count + 1;
			}
			if (area.contain(dc.getRed2())) {
				count = numberCount[index];
				numberCount[index] = count + 1;
			}
			if (area.contain(dc.getRed3())) {
				count = numberCount[index];
				numberCount[index] = count + 1;
			}
			if (area.contain(dc.getRed4())) {
				count = numberCount[index];
				numberCount[index] = count + 1;
			}
			if (area.contain(dc.getRed5())) {
				count = numberCount[index];
				numberCount[index] = count + 1;
			}
			if (area.contain(dc.getRed6())) {
				count = numberCount[index];
				numberCount[index] = count + 1;
			}
			issue[index] = title;
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		if (areas != null) {
			areas.clear();
		}
	}

	private int getAreaCount() {
		return areaCount;
	}

	private void calculateArea() {
		int count;
		if (LotteryConst.RED_BALL_COUNT % getAreaCount() == 0) {
			count = LotteryConst.RED_BALL_COUNT / getAreaCount();
		} else {
			count = LotteryConst.RED_BALL_COUNT / getAreaCount() + 1;
		}
		Area area;
		int start = 0;
		int end = 0;
		for (int i = 0; i < getAreaCount(); i++) {
			area = new Area();
			start = i * count + 1;
			end = (i + 1) * count;
			if (end > LotteryConst.RED_BALL_COUNT) {
				end = LotteryConst.RED_BALL_COUNT;
			}
			area.setStartNumber(start);
			area.setEndNumber(end);
			area.setAreaName(new StringBuilder().append("[").append(start)
					.append(" ").append("-").append(" ").append(end)
					.append("]").toString());
			area.setNumberCount(new int[issueCount]);
			area.setIssue(new String[issueCount]);
			areas.add(area);
		}
	}
}
