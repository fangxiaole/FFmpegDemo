cmake_minimum_required(VERSION 3.10.2)

set(FFmpeg_Root ${CMAKE_SOURCE_DIR}/src/main/cpp)
#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11 -Wno-deprecated-declarations -fblocks -fPIC")
#设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/libs_export/${ANDROID_ABI})
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/libs_export/${ANDROID_ABI})

add_library(
        ffmpegcmd
        SHARED
        src/main/cpp/ffmpeg_cmd.c
        src/main/cpp/cmdutils.c
        src/main/cpp/ffmpeg.c
        src/main/cpp/ffmpeg_filter.c
        src/main/cpp/ffmpeg_opt.c
        src/main/cpp/ffmpeg_hw.c
        src/main/cpp/ffmpeg_thread.c)

find_library(
        log-lib
        log)

add_library(libavcodec
        SHARED
        IMPORTED)
set_target_properties(libavcodec
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libavcodec.so)

add_library(libavfilter
        SHARED
        IMPORTED)
set_target_properties(libavfilter
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libavfilter.so)
add_library(libavformat
        SHARED
        IMPORTED)
set_target_properties(libavformat
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libavformat.so)
add_library(libavutil
        SHARED
        IMPORTED)
set_target_properties(libavutil
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libavutil.so)
add_library(libswresample
        SHARED
        IMPORTED)
set_target_properties(libswresample
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libswresample.so)

add_library(libswscale
        SHARED
        IMPORTED)
set_target_properties(libswscale
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libswscale.so)


include_directories(${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/include)

target_link_libraries(ffmpegcmd
        libavcodec libavfilter libavformat libavutil libswresample libswscale
        -landroid     # native_window
        -ljnigraphics # bitmap
        -lOpenSLES    # openSLES
        ${log-lib})