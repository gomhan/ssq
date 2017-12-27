package lottery.model;

import lottery.itf.NumericLottery;
import lottery.util.LotteryConst;

/**
 * 6 on 33 with one special number
 */
public class DoubleChromosphere implements NumericLottery,
		Comparable<DoubleChromosphere> {
	private String issue;
	private String time;
	private byte red1;
	private byte red2;
	private byte red3;
	private byte red4;
	private byte red5;
	private byte red6;
	private byte blue;
	private int summation;

	public DoubleChromosphere() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().append("[").append(issue).append("|")
				.append(time).append("|").append(red1).append("|").append(red2)
				.append("|").append(red3).append("|").append(red4).append("|")
				.append(red5).append("|").append(red6).append("|").append(blue)
				.append("|").append(summation).append("]").toString();
	}

	public String getRedString() {
		return new StringBuilder().append(LotteryConst.getAlignString(red1))
				.append(",").append(LotteryConst.getAlignString(red2))
				.append(",").append(LotteryConst.getAlignString(red3))
				.append(",").append(LotteryConst.getAlignString(red4))
				.append(",").append(LotteryConst.getAlignString(red5))
				.append(",").append(LotteryConst.getAlignString(red6))
				.toString();
	}

	@Override
	public void summation() {
		// TODO Auto-generated method stub
		summation = red1 + red2 + red3 + red4 + red5 + red6;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public byte getRed1() {
		return red1;
	}

	public void setRed1(byte red1) {
		this.red1 = red1;
	}

	public byte getRed2() {
		return red2;
	}

	public void setRed2(byte red2) {
		this.red2 = red2;
	}

	public byte getRed3() {
		return red3;
	}

	public void setRed3(byte red3) {
		this.red3 = red3;
	}

	public byte getRed4() {
		return red4;
	}

	public void setRed4(byte red4) {
		this.red4 = red4;
	}

	public byte getRed5() {
		return red5;
	}

	public void setRed5(byte red5) {
		this.red5 = red5;
	}

	public byte getRed6() {
		return red6;
	}

	public void setRed6(byte red6) {
		this.red6 = red6;
	}

	public byte getBlue() {
		return blue;
	}

	public void setBlue(byte blue) {
		this.blue = blue;
	}

	public int getSummation() {
		return summation;
	}

	public void setSummation(int summation) {
	}

	@Override
	public int compareTo(DoubleChromosphere o) {
		// TODO Auto-generated method stub
		return summation - o.getSummation();
	}

}
