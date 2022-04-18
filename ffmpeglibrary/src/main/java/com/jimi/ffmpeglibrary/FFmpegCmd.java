package com.jimi.ffmpeglibrary;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author:Fang.Le
 * e-mail:fangsunlight@163.com
 * time:2022/04/08
 * desc:
 */
public class FFmpegCmd {
    static {
        System.loadLibrary("ffmpegcmd");
    }

    public static final String TAG = FFmpegCmd.class.getSimpleName();
    private static OnCmdExecListener sOnCmdExecListener;
    private static long sDuration;

    public native int executeCmd(String[] cmd);

    public native int exitCmd();

    public static native int exec(int argc, String[] argv);

    public static native int execute(String[] commands);

    public static void exec(String[] cmds, long duration, OnCmdExecListener listener) {
        sOnCmdExecListener = listener;
        sDuration = duration;

        exec(cmds.length, cmds);
    }

    public static void execute2(String[] cmds) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                execute(cmds);
            }
        }).start();

//        ThreadPoolUtildd.INSTANCE.executeSingleThreadPool(new Runnable() {
//            @Override
//            public void run() {
//                execute(cmds);
//            }
//        });
//        //使用线程池工具处理耗时操作
//        ThreadPoolUtil.getInstance().execute(new Runnable() {
//            @Override
//            public void run() {
//                //在此执行耗时操作
//                //例如：文件下载、数据库存取、音频格式转换等
//                Log.e("leleTest","execute");
//                execute(cmds);
//
//            }
//        });
//        Log.e("leleTest","execute---------------------");
//        ThreadPool2.getInstance().execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("leleTest","execute++++++++++++++++++++");
//                execute(cmds);
//            }
//        });

//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e("leleTest","execute---------------------");
////                    Thread.currentThread().setName("Thread i = " + index);
////                    System.out.println(Thread.currentThread().getName() + " index = " + index);
////                    try {
////                        Thread.sleep(500);
////                    } catch (InterruptedException e) {
////                        System.out.println("ssss");
////                    }
//                    execute(cmds);
//                }
//            });
//        }
    }

    /**
     * FFmpeg执行结束回调，由C代码中调用
     */
    public static void onExecuted(int ret) {
        Log.e("leleTest","onExecuted ret="+ret);
        if (sOnCmdExecListener != null) {
            if (ret == 0) {
                sOnCmdExecListener.onProgress(sDuration);
                sOnCmdExecListener.onSuccess();
            } else {
                sOnCmdExecListener.onFailure();
            }
        }
    }

    /**
     * FFmpeg执行进度回调，由C代码调用
     */
    public static void onProgress(float progress) {
        Log.e("leleTest","onProgress="+progress);
        if (sOnCmdExecListener != null) {
            if (sDuration != 0) {
                sOnCmdExecListener.onProgress(progress / (sDuration / 1000) * 0.95f);
            }
        }
    }

    public interface OnCmdExecListener {
        void onSuccess();

        void onFailure();

        void onProgress(float progress);
    }
}
