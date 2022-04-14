package com.jimi.ffmpeglibrary;

import java.util.Locale;

/**
 * author:Fang.Le
 * e-mail:fangsunlight@163.com
 * time:2022/04/08
 * desc:
 */
public class FFmpegUtils {
    /**
     * cut video, you could assign the startTime and duration which you want to
     *
     * @param inputPath  input file
     * @param startTime  startTime in the video(unit is second)
     * @param duration   duration
     * @param outputPath output file
     * @return cut video success or not
     */
    public static String[] cutVideo(String inputPath, float startTime, float duration, String outputPath) {
        // -map 0 -codec copy (copy all tracks)
        // -map 0:v -vcodec copy (copy track of video)
        // -map 0:a -acodec copy (copy all tracks of audio)
        // -map 0:s -scodec copy (copy all tracks of subtitle)
        // ffmpeg -ss %f -accurate_seek -t %f -i %s -map 0 -codec copy -avoid_negative_ts 1 %s
        String cutVideoCmd = "ffmpeg -ss %f -accurate_seek -t %f -i -map 0 -codec copy -avoid_negative_ts 1";
        cutVideoCmd = String.format(Locale.getDefault(), cutVideoCmd, startTime, duration);
        return insert(cutVideoCmd.split(" "), 7, inputPath, outputPath);
    }

    /**
     * insert inputPath and outputPath into target array
     */
    private static String[] insert(String[] cmd, int position, String inputPath, String outputPath) {
        if (cmd == null || inputPath == null || position < 2) {
            return cmd;
        }
        int len = (outputPath != null ? (cmd.length + 2) : (cmd.length + 1));
        String[] result = new String[len];
        System.arraycopy(cmd, 0, result, 0, position);
        result[position] = inputPath;
        System.arraycopy(cmd, position, result, position + 1, cmd.length - position);
        if (outputPath != null) {
            result[result.length - 1] = outputPath;
        }
        return result;
    }

}
