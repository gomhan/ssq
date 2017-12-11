package lottery.itf;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lottery.model.DoubleChromosphere;
import lottery.model.State;

/**
 * Function interface extends {@link ResultSet}
 */
public interface Function extends ResultSet {

	/** used to generate function id */
	AtomicInteger ID_GENERATOR = new AtomicInteger(0);

	/**
	 * get function id.
	 * 
	 * @return
	 */
	int getId();

	/**
	 * get function name.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * invoke this function with a set of double-chromosphere.
	 * 
	 * @param parameter
	 * @return {@link State}
	 */
	State invoke(List<DoubleChromosphere> parameter);

	/**
	 * clear result value
	 */
	void clearValue();

}
