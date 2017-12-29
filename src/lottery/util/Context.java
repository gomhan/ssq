package lottery.util;

import java.awt.Window;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lottery.function.DefaultFunctionExecutor;
import lottery.model.DoubleChromosphere;
import lottery.model.LotteryModel;
import lottery.view.LotteryWindow;

public final class Context {

	public static final String MAIN_FRAME = "mainFrame";
	public static final String ACTIVE_WINDOW = "activeWindow";
	public static final String LOTTERY_MODEL = "lotteryModel";
	public static final String EXECUTOR = "executor";

	private static class Holder {
		private static final Context INSTANCE = new Context();
	}

	public static Context getInstance() {
		return Holder.INSTANCE;
	}

	private ConcurrentHashMap<String, Object> _cache = new ConcurrentHashMap<String, Object>();

	public void putObject(String key, Object comp) {
		_cache.put(key, comp);
	}

	public Object getObject(String key) {
		return _cache.get(key);
	}

	public LotteryModel getLottery() {
		return (LotteryModel) _cache.get(LOTTERY_MODEL);
	}

	public List<DoubleChromosphere> getLotteryList() {
		LotteryModel lm = getLottery();
		if (lm != null && lm.getLottery() != null) {
			return lm.getLottery();
		}
		return null;
	}

	public DefaultFunctionExecutor getDefaultExecutor() {
		return (DefaultFunctionExecutor) _cache.get(EXECUTOR);
	}

	public LotteryWindow getMainFrame() {
		return (LotteryWindow) _cache.get(MAIN_FRAME);
	}

	public Window getActiveWindow() {
		return (Window) _cache.get(ACTIVE_WINDOW);
	}

	private Context() {
		// TODO Auto-generated constructor stub
	}

}
