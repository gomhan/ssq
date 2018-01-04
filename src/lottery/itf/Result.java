package lottery.itf;

/**
 * Each {@link Result} encapsulated a value which is the actual result of
 * execution of a function. user can access value of this {@link Result} via
 * {@code get} and {@code set} method. use {@link Checker} to verify the
 * validity of value.
 */
public interface Result {

	/** Null result */
	Result NULL = new Result() {

		@Override
		public void setValue(Object value) {
		}

		@Override
		public Object getValue() {
			return null;
		}

		@Override
		public boolean invalid() {
			return false;
		}

		@Override
		public void setChecker(Checker checker) {
		}

		@Override
		public Checker getChecker() {
			return null;
		}

		@Override
		public void clearValue() {
		}
	};

	/**
	 * get value of this result.
	 * 
	 * @return
	 */
	Object getValue();

	/**
	 * set value of this result.
	 * 
	 * @param value
	 */
	void setValue(Object value);

	/**
	 * get result checker.
	 * 
	 * @return
	 */
	Checker getChecker();

	/**
	 * set checker
	 * 
	 * @param checker
	 */
	void setChecker(Checker checker);

	/**
	 * whether this result is invalid.
	 * 
	 * @return
	 */
	boolean invalid();

	/**
	 * clear value of this result.
	 */
	void clearValue();

}
