package lottery.view.menu.shortterm;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import lottery.function.shortterm.NumberAreaDeviationStatistic;
import lottery.function.shortterm.NumberAreaDeviationStatistic.Area;
import lottery.itf.Function;
import lottery.itf.Result;
import lottery.util.LotteryConst;
import lottery.view.funcview.NumberAreaInTableFrame;
import lottery.view.menu.ShortTermMenu;

import org.apache.commons.lang3.StringUtils;

public class NumberAreaTableMenu extends ShortTermMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6912497808548053935L;
	JLabel areaLabel;
	JTextField areaField;
	int areaCount;

	public NumberAreaTableMenu() {
		super();
	}

	protected void initialize() {
		super.initialize();
		setName("NumberAreaTableMenu");
		setText("数字区间偏差");
		function = new NumberAreaDeviationStatistic();
		areaLabel = new JLabel("区间：");
		areaField = new JTextField(5);
		areaField.setText("3");
		areaLabel.setOpaque(false);
	}

	protected void build() {
		inputPanel.add(label3);
		inputPanel.add(field1);
		inputPanel.add(label);
		inputPanel.add(field);
		inputPanel.add(areaLabel);
		inputPanel.add(areaField);
		inputPanel.add(label2);

		setLayout(new BorderLayout());
		add(inputPanel, BorderLayout.EAST);
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		NumberAreaInTableFrame frame = new NumberAreaInTableFrame(
				(List<Area>) result.getValue(), length);
		frame.display();
	}

	@Override
	protected void handleUserInput() {
		// TODO Auto-generated method stub
		super.handleUserInput();
		areaCount = 3;
		String s = areaField.getText();
		if (StringUtils.isBlank(s)) {
		} else {
			try {
				areaCount = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		if (areaCount < 0 || areaCount > LotteryConst.RED_BALL_COUNT) {
			areaCount = 3;
		}
	}

	@Override
	protected void prepareFunctionWithUserInput(Function f) {
		// TODO Auto-generated method stub
		f.setProperty(NumberAreaDeviationStatistic.PROPERTY_AREA_COUNT,
				areaCount);
	}
}
