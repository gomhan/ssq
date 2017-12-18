package lottery.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import lottery.function.DefaultFunctionExecutor;
import lottery.function.FunctionResult;
import lottery.itf.Function;
import lottery.itf.FunctionExecutor;
import lottery.itf.FunctionView;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.model.State;
import lottery.util.Context;

public abstract class FunctionMenu extends JMenuItem implements ActionListener,
		FunctionView {

	private static final long serialVersionUID = 6974135665884433724L;

	public FunctionMenu() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FunctionExecutor fe = (DefaultFunctionExecutor) Context.getInstance()
				.getExecutor();
		fe.installFunction(getFunction());
		State s = fe.execute(getParameter());
		if (s != State.SUCCESS) {
			return;
		}

		Result r = fe.getResult();
		if (r instanceof FunctionResult) {
			showChart((FunctionResult) r);
		}
	}

	protected List<DoubleChromosphere> getParameter() {
		// TODO Auto-generated method stub
		return Context.getInstance().getLottery().getLottery();
	}

	protected abstract Function getFunction();

}
