package com.jimi.ffmpeglibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:Fang.Le
 * e-mail:fangsunlight@163.com
 * time:2022/04/14
 * desc:
 */
public class ThreadPool2 {
    ExecutorService executorService;
    public ThreadPool2() {
        executorService = Executors.newSingleThreadExecutor();
    }

    private static volatile ThreadPool2 mInstance;

    public static ThreadPool2 getInstance() {
        if (mInstance == null) {
            synchronized (ThreadPoolUtil.class) {
                if (mInstance == null) {
                    mInstance = new ThreadPool2();
                }
            }
        }
        return mInstance;
    }

    /**
     * 执行任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        if (executorService == null) {
            executorService = Executors.newSingleThreadExecutor();
        }
        if (runnable != null) {
            executorService.submit(runnable);
        }
    }
}
