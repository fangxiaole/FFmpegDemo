package com.jimi.ffmpeglibrary

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object ThreadPoolUtildd {

    private val executor = Executors.newSingleThreadExecutor()

    fun executeSingleThreadPool(runnable: Runnable): ExecutorService {
        executor.submit(runnable)
        return executor
    }

}
