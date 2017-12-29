package lottery.itf;

/**
 * The class implement this interface should knows how to present {@link Result}
 * in user interface.
 */
public interface GraphicsSupplier {

	/**
	 * show graphics with result of specified function.
	 * 
	 * @param result
	 */
	void showGraphics(Result result);
}
