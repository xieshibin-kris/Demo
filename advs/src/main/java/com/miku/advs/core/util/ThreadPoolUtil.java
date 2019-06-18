package com.miku.advs.core.util;

import java.util.concurrent.*;

/**
 * 线程池工具类
 * @author MXL
 *
 */
public class ThreadPoolUtil {
	
	/**
	 * 可缓存线程池 线程数量无限制
	 * @return
	 */
	public static ExecutorService getCachedThreadPool(){
		return Executors.newCachedThreadPool();
	}

	/**
	 * 定长线程池，可控制线程最大并发数
	 * @return
	 */
	public static ExecutorService getFixedThreadPool(){
		return Executors.newFixedThreadPool(10);
	}
	
	/**
	 * 定时及周期性任务执行
	 * @return
	 */
	public static ScheduledExecutorService getScheduledThreadPool(){
		return Executors.newScheduledThreadPool(5);
    }
	
	/**
	 * 线程池
	 * @return
	 */
	public static ThreadPoolExecutor getThreadPoolExecutor(){
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		return new ThreadPoolExecutor(5, 100, 60, TimeUnit.SECONDS, workQueue);
	}
	
}