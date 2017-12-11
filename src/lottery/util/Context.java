package lottery.util;

import java.util.concurrent.ConcurrentHashMap;

import lottery.itf.FunctionExecutor;
import lottery.model.LotteryModel;

public final class Context {

	// public static final String MAIN_FRAME = "mainFrame";
	// public static final String ACTIVE_WINDOW = "activeWindow";
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

	public FunctionExecutor getExecutor() {
		return (FunctionExecutor) _cache.get(EXECUTOR);
	}

	private Context() {
		// TODO Auto-generated constructor stub
	}

}
