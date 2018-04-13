package lottery.view.menu.shortterm;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import lottery.function.shortterm.MissDeviationStatistic;
import lottery.function.shortterm.MissDeviationStatistic.MissNumber;
import lottery.itf.Function;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.util.Context;
import lottery.view.funcview.MissInTableFrame;
import lottery.view.menu.AbstractTermMenu;

import org.apache.commons.lang3.StringUtils;

public class MissTableMenu extends AbstractTermMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7092029843982234128L;
	JLabel missLabel;
	JTextField missField;
	int missCount;

	public MissTableMenu() {
		super();
	}

	protected void initialize() {
		super.initialize();
		setName("MissTableMenu");
		setText("ÒÅÂ©Æ«²î");
		function = new MissDeviationStatistic();
		missLabel = new JLabel("ÒÅÂ©£º");
		missField = new JTextField(5);
		missField.setText("5");
		missLabel.setOpaque(false);
	}

	protected void build() {
		inputPanel.add(label3);
		inputPanel.add(field1);
		inputPanel.add(label);
		inputPanel.add(field);
		inputPanel.add(missLabel);
		inputPanel.add(missField);
		inputPanel.add(label2);

		setLayout(new BorderLayout());
		add(inputPanel, BorderLayout.EAST);
	}

	@Override
	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		MissInTableFrame frame = new MissInTableFrame(
				(List<MissNumber>) result.getValue(), length);
		frame.display();
	}

	@Override
	protected void handleUserInput() {
		// TODO Auto-generated method stub
		super.handleUserInput();
		missCount = 5;
		String s = missField.getText();
		if (StringUtils.isBlank(s)) {
		} else {
			try {
				missCount = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		if (missCount < 0 || missCount + 1 > objs.size()) {
			missCount = 5;
		}
	}

	@Override
	protected void prepareFunctionWithUserInput(Function f) {
		// TODO Auto-generated method stub
		f.setProperty(MissDeviationStatistic.PROPERTY_MISS_COUNT, missCount);
		f.setProperty(MissDeviationStatistic.PROPERTY_OFFSET, offset);
		f.setProperty(MissDeviationStatistic.PROPERTY_LENGTH, length);
	}
}
