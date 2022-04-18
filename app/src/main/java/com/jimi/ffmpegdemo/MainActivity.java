package com.jimi.ffmpegdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jimi.ffmpeglibrary.FFmpegCmd;
import com.jimi.ffmpeglibrary.FFmpegUtil;
import com.jimi.ffmpeglibrary.FFmpegUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String PATH = Environment.getExternalStorageDirectory().getPath();
        String inputPath = PATH + File.separator + "2022_03_23_13_40_25_03.ts";
        String outPath = PATH + File.separator + "dd.ts";
        String commd = "ffmpeg -i " + inputPath + " -vcodec copy -acodec copy -ss 10 -t 15 " + outPath + " -y";//可以实现
//        String []cmds = commd.split(" ");
        String[] cmds = FFmpegUtil.getCutCmd(inputPath, outPath, 10, 15);
        String[] cmdsdd = FFmpegUtils.cutVideo(inputPath, 10, 15,outPath);
        Log.e("leleTest", "PATH=" + PATH + "cmds=" + Arrays.toString(cmds));


        String path1 = PATH + File.separator + "2022_03_24_09_47_00_03.ts";
        String path2 = PATH + File.separator + "2022_03_23_13_41_27_03.ts";
        String outPath2 = PATH + File.separator + "ff.ts";
        String commd2 = "ffmpeg -i concat:" + path1 + "|" + path2 + "| -c copy " + outPath2;
//        String []cmds2 = commd2.split(" ");
        ArrayList<String> list = new ArrayList<>();
        list.add(path1);
        list.add(path2);
        String[] cmds2 = FFmpegUtil.getMergeCmd(list, outPath2);


        TextView tx_cut = findViewById(R.id.tx_cut);
        TextView tx_merge = findViewById(R.id.tx_merge);
        tx_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("leleTest", "PATH=" + PATH + "cmds=" + Arrays.toString(cmdsdd));
                String test = "ffmpeg, -i, concat:/storage/sdcard0/DVRMEDIA/CarRecorder/EVENT/2022_04_15/2022_04_15_18_41_02_F_05.ts|/storage/sdcard0/DVRMEDIA/CarRecorder/GENERAL/ForwardCam/2022_04_15/2022_04_15_18_41_01_03.ts|, -c, copy, /storage/sdcard0/DVRMEDIA/CarRecorder/EVENT/2022_04_15/2022_04_15_18_41_02_F_05temp.ts";
                String [] cmdTest = test.split(test);
                FFmpegUtil.execCmd(cmdsdd, new FFmpegCmd.OnCmdExecListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }

                    @Override
                    public void onProgress(float progress) {

                    }
                });
            }
        });
        tx_merge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        fFmpegCmd.executeCmd(cmds2);
//                        fFmpegCmd.exitCmd();
                ArrayList<String> list1 = new ArrayList<>();
                list1.add("/storage/emulated/0/2022_04_15_18_41_02_F_05.ts");
                list1.add("/storage/emulated/0/2022_04_15_18_41_01_03.ts");
                String[] cmdtest = FFmpegUtil.getMergeCmd(list1,"/storage/emulated/0/bb.ts");
                Log.e("leleTest", "PATH=" + PATH + "cmds=" + Arrays.toString(cmdtest));
                FFmpegUtil.execCmd(cmdtest, new FFmpegCmd.OnCmdExecListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure() {

                    }

                    @Override
                    public void onProgress(float progress) {

                    }
                });
            }
        });

//        String cc = "/storage/sdcard0/DVRMEDIA/CarRecorder/EVENT/2022_04_15/2022_04_15_18_21_19_F_05.ts";
//        cc = cc.replace(".ts","temp.ts");
//        Log.e("leleTest","cc="+cc);

    }
}