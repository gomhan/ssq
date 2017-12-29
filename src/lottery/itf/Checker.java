package lottery.itf;

/**
 * used to check value of {@link Result}
 */
public interface Checker {

	/**
	 * check whether specified value is invalid.
	 * 
	 * @param value
	 * @return
	 */
	boolean invalid(Object value);
}
