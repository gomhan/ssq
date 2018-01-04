package lottery.itf;

/**
 * Result set is a container which contains several results.
 */
public interface ResultSet {

	ResultSet NULL = new ResultSet() {
		@Override
		public Result getResult(int identifier) {
			return null;
		}

		@Override
		public void setResult(int identifier, Result result) {
		}

		@Override
		public Result removeResult(int identifier) {
			return null;
		}

		@Override
		public void clearResult() {
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

	/**
	 * put a result to set.
	 * 
	 * @param identifier
	 * @param result
	 */
	void setResult(int identifier, Result result);

	/**
	 * remove specified result in set.
	 * 
	 * @param identifier
	 * @return
	 */
	Result removeResult(int identifier);

	/**
	 * user should very be careful to call this method, because it will remove
	 * all {@link Result}.
	 */
	void clearResult();
}
