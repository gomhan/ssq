package lottery.itf;

/**
 * Result set is a container which contains several results.
 */
public interface ResultSet {

	ResultSet NULL = new ResultSet() {
		public Result getResult(int identifier) {
			throw new UnsupportedOperationException();
		}
	};

	/**
	 * get result from result set, it can be several results, marked by
	 * identifier.
	 * 
	 * @param identifier
	 * @return Result
	 */
	Result getResult(int identifier);
}
