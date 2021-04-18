package com.volarpadcloud.api.utils;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;

import java.util.concurrent.*;

/**
 * 线程池工具类
 */
public class ThreadPollUtils {


    /**
     * 固定大小的线程池
     */
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);



    /**
     * 获取CPU的核心数
     */
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     *
     创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。
     创建线程池的时候请使用带ThreadFactory的构造函数，
     并且提供自定义ThreadFactory实现或者使用第三方实现。
     */
    public static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    /**
     * 手动通过 ThreadPoolExecutor 创建线程池
     *
     * corePoolSize用于指定核心线程数量
     * maximumPoolSize指定最大线程数
     * keepAliveTime指定线程空闲后的最大存活时间
     *
     * 可以根据项目的需要自定义线程池的参数
     */
    public static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000),namedThreadFactory);

}
