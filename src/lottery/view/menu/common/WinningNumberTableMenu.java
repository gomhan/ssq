package lottery.view.menu.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lottery.function.common.WinningNumberStatistic;
import lottery.function.common.WinningNumberStatistic.Ball;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.util.Context;
import lottery.util.LotteryConst;
import lottery.view.funcview.WinningNumberInTableFrame;
import lottery.view.menu.FunctionMenu;

import org.apache.commons.lang3.StringUtils;

public class WinningNumberTableMenu extends FunctionMenu {
	private static final long serialVersionUID = 6397092403402449636L;

	JPanel p;
	JTextField field;
	JLabel label;
	JLabel label2;
	int issue;

	public WinningNumberTableMenu() {
		super();
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		setName("WinningNumberTableMenu");
		setText("中奖数字情况表");
		setPreferredSize(new Dimension(230, 30));
		setMinimumSize(new Dimension(230, 30));
		setMaximumSize(new Dimension(230, 30));
		function = new WinningNumberStatistic();
		
		field = new JTextField(5);
		label = new JLabel("[期数：");
		label2 = new JLabel("]");
		p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);
		label.setOpaque(false);
		label2.setOpaque(false);
		p.setOpaque(false);
	}
	
	@Override
	protected void build() {
		// TODO Auto-generated method stub
		p.add(label);
		p.add(field);
		p.add(label2);
		
		setLayout(new BorderLayout());
		add(p, BorderLayout.EAST);
	}

	public void showGraphics(Result result) {
		// TODO Auto-generated method stub
		WinningNumberInTableFrame frame = new WinningNumberInTableFrame(
				(List<Ball>) result.getValue(), issue);
		frame.display();
	}
	
	@Override
	protected void handleUserInput() {
		// TODO Auto-generated method stub
		issue = 0;
		String s = field.getText();
		if (StringUtils.isBlank(s)) {
			issue = Context.getInstance().getLotteryList().size();
		} else {
			try {
				issue = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		// field.setText("");
	}

	@Override
	protected List<DoubleChromosphere> getParameter() {
		// TODO Auto-generated method stub
		List<DoubleChromosphere> objs = Context.getInstance().getLotteryList();
		List<DoubleChromosphere> filterObjs = null;
		if (issue > 0) {
			filterObjs = new ArrayList<>();
			for (int i = 0; i < issue; i++) {
				filterObjs.add(objs.get(i));
			}
		} else {
			filterObjs = objs;
		}
		return filterObjs;
	}

}
