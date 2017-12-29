package lottery.view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lottery.model.DoubleChromosphere;
import lottery.util.Context;
import lottery.util.LotteryConst;

import org.apache.commons.lang3.StringUtils;

public abstract class ShortTermMenu extends FunctionMenu {

	protected JTextField field;
	protected JLabel label;
	protected JLabel label2;
	protected JTextField field1;
	protected JLabel label3;
	protected JLabel label4;
	protected JPanel inputPanel;

	protected int offset;
	protected int length;

	public ShortTermMenu() {
		super();
	}

	protected void initialize() {
		setPreferredSize(new Dimension(380, 30));
		setMinimumSize(new Dimension(380, 30));
		setMaximumSize(new Dimension(380, 30));

		field = new JTextField(5);
		field.setText("10");
		label = new JLabel("ÆÚÊý£º");
		label2 = new JLabel("]");

		field1 = new JTextField(5);
		field1.setText("0");
		label3 = new JLabel("[ÆÚºÅ£º");

		inputPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		inputPanel.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);

		label.setOpaque(false);
		label2.setOpaque(false);
		label3.setOpaque(false);
		inputPanel.setOpaque(false);
	}

	protected void build() {
		inputPanel.add(label3);
		inputPanel.add(field1);
		inputPanel.add(label);
		inputPanel.add(field);
		inputPanel.add(label2);

		setLayout(new BorderLayout());
		add(inputPanel, BorderLayout.EAST);
	}

	protected void handleUserInput() {
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		offset = 0;
		String s = field1.getText();
		if (StringUtils.isBlank(s)) {
		} else {
			try {
				offset = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		if (offset < 0 || offset >= objs.size()) {
			offset = 0;
		}

		length = 10;
		s = field.getText();
		if (StringUtils.isBlank(s)) {
		} else {
			try {
				length = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		if (length < 0) {
			length = 0;
		}
		if (offset + length > objs.size()) {
			length = 0;
		}
	}

	@Override
	protected List<DoubleChromosphere> getParameter() {
		// TODO Auto-generated method stub
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		List<DoubleChromosphere> filterObjs = new ArrayList<>();
		int endIssue = offset + length;
		for (int i = offset; i < endIssue; i++) {
			filterObjs.add(objs.get(i));
		}
		return filterObjs;
	}

}
