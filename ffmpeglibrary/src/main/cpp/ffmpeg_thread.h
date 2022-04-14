//
// Created by Fang.Le on 2022/4/11.
//

#ifndef FFMPEGDEMO_FFMPEG_THREAD_H
#define FFMPEGDEMO_FFMPEG_THREAD_H
#endif //FFMPEGDEMO_FFMPEG_THREAD_H
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#include "libswscale/swscale.h"
#include "ffmpeg.h"
#include <pthread.h>
#include <string.h>

int ffmpeg_thread_run_cmd(int cmdnum,char **argv);

void ffmpeg_thread_exit(int ret);

void ffmpeg_thread_callback(void (*cb)(int ret));

void ffmpeg_thread_cancel();
