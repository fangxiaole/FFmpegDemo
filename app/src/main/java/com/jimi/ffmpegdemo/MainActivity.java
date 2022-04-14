package com.jimi.ffmpegdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jimi.ffmpeglibrary.FFmpegCmd;
import com.jimi.ffmpeglibrary.FFmpegUtil;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String ACTION_GSENSOR_RECORD_CRASH_EVENT = "android.intent.action.GSENSOR_RECORD_CRASH_EVENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FFmpegCmd fFmpegCmd = new FFmpegCmd();
        String PATH = Environment.getExternalStorageDirectory().getPath();
        String inputPath = PATH+ File.separator+"2022_03_23_13_40_25_03.ts";
        String outPath = PATH+ File.separator+"aa.ts";

        String commd ="ffmpeg -i "+ inputPath+" -vcodec copy -acodec copy -ss 10 -t 15 "+outPath+" -y";//可以实现
        String []cmds = commd.split(" ");
        Log.e("leleTest","PATH="+PATH+"cmds="+ Arrays.toString(cmds));


        String path1=  PATH+ File.separator+"2022_03_24_09_47_00_03.ts";
        String path2= PATH+File.separator+"2022_03_23_13_41_27_03.ts";
        String outPath2 = PATH+ File.separator+"22.ts";
        String commd2 = "ffmpeg -i concat:"+path1+"|"+path2+"| -c copy "+outPath2;
        String []cmds2 = commd2.split(" ");


        TextView tx_cut = findViewById(R.id.tx_cut);
        TextView tx_merge = findViewById(R.id.tx_merge);
        tx_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ACTION_GSENSOR_RECORD_CRASH_EVENT);
                sendBroadcast(intent);

//                Log.e("leleTest","PATH="+PATH+"cmds="+ Arrays.toString(cmds));
////                        fFmpegCmd.executeCmd(cmds);
////                        fFmpegCmd.exitCmd();
//                FFmpegUtil.execCmd(cmds, new FFmpegCmd.OnCmdExecListener() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onFailure() {
//
//                    }
//
//                    @Override
//                    public void onProgress(float progress) {
//
//                    }
//                });
//                FFmpegCmd.execute2(cmds);
////                FFmpegCmd.execute2(cmds);
////                FFmpegCmd.execute2(cmds);
////                FFmpegCmd.execute2(cmds);
////                FFmpegCmd.execute2(cmds);
////                FFmpegCmd.execute2(cmds);
            }
        });
        tx_merge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("leleTest","PATH="+PATH+"cmds="+ Arrays.toString(cmds));
//                        fFmpegCmd.executeCmd(cmds2);
//                        fFmpegCmd.exitCmd();
                FFmpegUtil.execCmd(cmds2, new FFmpegCmd.OnCmdExecListener() {
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
//                FFmpegCmd.execute2(cmds);
            }
        });

    }
}