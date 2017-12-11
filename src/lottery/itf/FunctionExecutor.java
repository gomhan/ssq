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
	boolean installFunction(Function function);

	/**
	 * uninstall all.
	 */
	void uninstall();

	/**
	 * get current function, latest installed.
	 * 
	 * @return
	 */
	Function getFunction();

	/**
	 * set cancel identifier.
	 * 
	 * @param b
	 */
	void cancel(boolean b);

	/**
	 * return canncel identifier.
	 * 
	 * @return user cancelled this execution return true, <br>
	 *         otherwise return false.
	 */
	boolean cancelled();

	/**
	 * get default result.
	 * 
	 * @return
	 */
	Result getResult();

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