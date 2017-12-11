package lottery.util;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Utilities {
	
	private static final Utilities INSTANCE = new Utilities();

	int index = 0;
	ThreadPoolExecutor tpe;

	private Utilities() {
		// TODO Auto-generated constructor stub
		tpe = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS,
		new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				// TODO Auto-generated method stub
				Thread t = new Thread(r, "lottery_thread_" + index++);
				return t;
			}
		});
	}
	
	public Utilities getInstance() {
		return INSTANCE;
	}
	
	public ThreadPoolExecutor getTpe() {
		return tpe;
	}
}
