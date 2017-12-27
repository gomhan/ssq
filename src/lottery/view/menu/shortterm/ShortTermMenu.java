package lottery.view.menu.shortterm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import lottery.itf.Function;
import lottery.model.DoubleChromosphere;
import lottery.util.Context;
import lottery.util.LotteryConst;
import lottery.view.menu.FunctionMenu;

public abstract class ShortTermMenu extends FunctionMenu {

	protected JTextField field;
	protected JLabel label;
	protected JLabel label2;
	protected JTextField field1;
	protected JLabel label3;
	protected JLabel label4;
	protected JPanel inputPanel;

	protected int issue;
	protected int end;
	protected Function function;

	public ShortTermMenu() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(380, 30));
		setMinimumSize(new Dimension(380, 30));
		setMaximumSize(new Dimension(380, 30));

		initialize();
		build();
		addActionListener(this);
	}

	protected void initialize() {
		field = new JTextField(5);
		field.setText("10");
		label = new JLabel("ÆÚÊý£º");
		label2 = new JLabel("]");

		field1 = new JTextField(5);
		field1.setText("0");
		label3 = new JLabel("[ÆÚºÅ£º");

		inputPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		inputPanel.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);
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
	
	protected void prepare() {
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		issue = 0;
		String s = field1.getText();
		if (StringUtils.isBlank(s)) {
		} else {
			try {
				issue = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		if (issue < 0 || issue >= objs.size()) {
			issue = 0;
		}

		end = 10;
		s = field.getText();
		if (StringUtils.isBlank(s)) {
		} else {
			try {
				end = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		end = issue + end;
		if (end < 0) {
			end = 0;
		} else if (end >= objs.size()) {
			end = objs.size();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		prepare();
		super.actionPerformed(e);
	}

	@Override
	protected List<DoubleChromosphere> getParameter() {
		// TODO Auto-generated method stub
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		List<DoubleChromosphere> filterObjs = new ArrayList<>();
		for (int i = issue; i < end; i++) {
			filterObjs.add(objs.get(i));
		}
		return filterObjs;
	}

}
