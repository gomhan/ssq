package lottery.itf;

import java.util.List;

import lottery.model.DoubleChromosphere;
import lottery.model.State;

/**
 * executor for functions.
 */
public interface FunctionExecutor {

	/**
	 * install function to executor.
	 * 
	 * @param function
	 * @return if function has already installed return false,<br>
	 *         otherwise return true.
	 */
	boolean install(Function function);

	/**
	 * uninstall
	 */
	void uninstall();

	/**
	 * get current function, latest installed.
	 * 
	 * @return
	 */
	Function getFunction();

	/**
	 * set whether cancel execution.
	 * 
	 * @param b
	 */
	void cancel(boolean b);

	/**
	 * return whether executor is cancelled.
	 * 
	 * @return user cancelled this execution return true, <br>
	 *         otherwise return false.
	 */
	boolean cancelled();

	/**
	 * get result of execution specified by identifier.
	 * 
	 * @param identifier
	 * @return
	 */
	Result getResult(int identifier);

	/**
	 * execute function that installed to this executor.
	 * 
	 * @param parameter
	 * @return
	 */
	State execute(List<DoubleChromosphere> parameter);

}