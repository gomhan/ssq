package lottery.view.menu.shortterm;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import lottery.function.FunctionResult;
import lottery.function.shortterm.Function6;
import lottery.function.shortterm.Function6.Area;
import lottery.itf.Function;
import lottery.util.LotteryConst;
import lottery.view.funcview.Func6InTableFrame;

import org.apache.commons.lang3.StringUtils;

public class Func6TableMenu extends ShortTermMenu {

	JLabel areaLabel;
	JTextField areaField;
	int area;

	public Func6TableMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func6TableMenu");
		setText("数字区间偏差");

		function = new Function6();
	}

	protected void initialize() {
		super.initialize();
		areaLabel = new JLabel("区间：");
		areaField = new JTextField(5);
		areaField.setText("3");
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
	public void showChart(FunctionResult fr) {
		// TODO Auto-generated method stub
		Func6InTableFrame frame = new Func6InTableFrame(
				(List<Area>) fr.getValue(), end - issue);
		frame.display();
	}
	
	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		super.prepare();
		area = 3;
		String s = areaField.getText();
		if (StringUtils.isBlank(s)) {
		} else {
			try {
				area = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		if (area < 0 || area > LotteryConst.RED_BALL_COUNT) {
			area = 3;
		}
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		((Function6) function).setAreaCount(area);
		return function;
	}
}
