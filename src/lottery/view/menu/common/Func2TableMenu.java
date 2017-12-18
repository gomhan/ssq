package lottery.view.menu.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lottery.function.FunctionResult;
import lottery.function.common.Function2;
import lottery.function.common.Function2.Ball;
import lottery.itf.Function;
import lottery.model.DoubleChromosphere;
import lottery.util.Context;
import lottery.util.LotteryConst;
import lottery.view.funcview.Func2InTableFrame;
import lottery.view.menu.FunctionMenu;

import org.apache.commons.lang3.StringUtils;

public class Func2TableMenu extends FunctionMenu {
	private static final long serialVersionUID = 6397092403402449636L;

	JTextField field;
	JLabel label;
	JLabel label2;
	int issue;
	Function function;

	public Func2TableMenu() {
		// TODO Auto-generated constructor stub
		super();
		setName("Func2TableMenu");
		setText("�н����������");
		field = new JTextField(5);
		addActionListener(this);
		function = new Function2();

		label = new JLabel("[������");
		label2 = new JLabel("]");
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
		p.add(label);
		p.add(field);
		p.add(label2);
		p.setBackground(LotteryConst.COMPONENT_DEFAULT_BG);

		setPreferredSize(new Dimension(230, 30));
		setMinimumSize(new Dimension(230, 30));
		setMaximumSize(new Dimension(230, 30));
		setLayout(new BorderLayout());
		add(p, BorderLayout.EAST);
	}

	public void showChart(FunctionResult fr) {
		// TODO Auto-generated method stub
		Func2InTableFrame frame = new Func2InTableFrame(
				(List<Ball>) fr.getValue(), issue);
		frame.display();
	}

	@Override
	protected Function getFunction() {
		// TODO Auto-generated method stub
		return function;
	}

	@Override
	protected List<DoubleChromosphere> getParameter() {
		// TODO Auto-generated method stub
		List<DoubleChromosphere> objs = Context.getInstance().getLottery()
				.getLottery();
		issue = 0;
		String s = field.getText();
		if (StringUtils.isBlank(s)) {
			issue = objs.size();
		} else {
			try {
				issue = Integer.parseInt(s);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		field.setText("");
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