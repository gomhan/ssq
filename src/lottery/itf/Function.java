package lottery.itf;

import java.util.List;

import lottery.model.DoubleChromosphere;
import lottery.model.State;

/**
 * This interface represent a execution process. After {@code invoke} method
 * called, user can get function {@link Result}.
 */
public interface Function extends ResultSet {

	/**
	 * get function id.
	 * 
	 * @return
	 */
	Object getId();

	/**
	 * set function id
	 * 
	 * @param id
	 */
	void setId(Object id);

	/**
	 * get function name.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * set function name.
	 * 
	 * @return
	 */
	void setName(String name);

	/**
	 * get function describe.
	 * 
	 * @return
	 */
	String getDescribe();

	/**
	 * set function describe.
	 * 
	 * @return
	 */
	void setDescribe(String describe);

	/**
	 * {@link Function} support to customize properties. User can put or get
	 * properties with method {@code getProperty} and {@code putProperty}.
	 * 
	 * @see
	 * @return
	 */
	Object getProperty(String key);

	/**
	 * {@link Function} support to customize properties. User can put or get
	 * properties with method {@code getProperty} and {@code putProperty}.
	 * 
	 * @param key
	 * @param value
	 */
	void setProperty(String key, Object value);

	/**
	 * Only check customized properties.
	 * 
	 * @return
	 */
	boolean propertyInvalid();

	/**
	 * invoke this function with a set of double-chromosphere.
	 * 
	 * @param parameter
	 * @return {@link State}
	 */
	State invoke(List<DoubleChromosphere> parameter);

	/**
	 * reset function to initiate state, add clear execution result.
	 */
	void reset();

}
