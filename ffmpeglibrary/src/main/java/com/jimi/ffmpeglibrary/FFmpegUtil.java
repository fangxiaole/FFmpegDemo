package com.jimi.ffmpeglibrary;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * author:Fang.Le
 * e-mail:fangsunlight@163.com
 * time:2022/04/11
 * desc:
 */
public class FFmpegUtil {
    private static final String TAG = "FFmpegUtil";

    public static void execCmd(String[] cmds, FFmpegCmd.OnCmdExecListener listener) {
        FFmpegCmd.exec(cmds, 2000, listener);
    }

    public static String[] getCutCmd(String inputPath, String outPath, int startTime, int duration) {
        String commd = "ffmpeg -i " + inputPath + " -vcodec copy -acodec copy -ss " + startTime + " -t " + duration + " " + outPath + " -y";
        String[] cmds = commd.split(" ");
        return cmds;
    }

    public static String[] getCutCmd(String inputPath, String outPath, int startTime) {
        String commd = "ffmpeg -i " + inputPath + " -vcodec copy -acodec copy -ss " + startTime + " " + outPath + " -y";
        String[] cmds = commd.split(" ");
        return cmds;
    }


    public static String[] getMergeCmd(ArrayList<String> inputLists, String outPath) {
//        String path1 = PATH + File.separator + "2022_03_24_09_47_00_03.ts";
//        String path2 = PATH + File.separator + "2022_03_23_13_41_27_03.ts";
//        String outPath2 = PATH + File.separator + "22.ts";
//        String commd2 = "ffmpeg -i concat:" + path1 + "|" + path2 + "| -c copy " + outPath2;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ffmpeg -i concat:");
        for (int i = 0; i < inputLists.size(); i++) {
            String path = inputLists.get(i);
            if (!TextUtils.isEmpty(path)) {
                stringBuffer.append(path);
                stringBuffer.append("|");
            }
        }
        stringBuffer.append(" -c copy ");
        stringBuffer.append(outPath);
        String[] cmds2 = stringBuffer.toString().split(" ");
        return cmds2;
    }


}
