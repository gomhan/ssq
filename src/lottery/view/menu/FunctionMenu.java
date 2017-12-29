package lottery.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import lottery.function.DefaultFunctionExecutor;
import lottery.itf.Function;
import lottery.itf.GraphicsSupplier;
import lottery.itf.Result;
import lottery.model.DoubleChromosphere;
import lottery.model.State;
import lottery.util.Context;

public abstract class FunctionMenu extends JMenuItem implements ActionListener,
		GraphicsSupplier {

	private static final long serialVersionUID = 6974135665884433724L;
	protected Function function;

	public FunctionMenu() {
		// TODO Auto-generated constructor stub
		initialize();
		build();
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		handleUserInput();
		Function f = getFunction();
		prepareFunctionWithUserInput(f);
		DefaultFunctionExecutor fe = Context.getInstance().getDefaultExecutor();
		fe.install(f);
		State s = fe.execute(getParameter());
		if (s != State.SUCCESS) {
			return;
		}

		Result r = fe.getDefaultResult();
		if (r != null && r != Result.NULL) {
			showGraphics(r);
		}
	}

	protected List<DoubleChromosphere> getParameter() {
		// TODO Auto-generated method stub
		return Context.getInstance().getLotteryList();
	}

	protected Function getFunction() {
		return function;
	}

	protected void initialize() {
	}

	protected void build() {
	}

	/**
	 * get user input and store in variant.
	 */
	protected void handleUserInput() {
	}

	/**
	 * prepare needed parameter for specified function.
	 * 
	 * @param f
	 */
	protected void prepareFunctionWithUserInput(Function f) {
	}

}
