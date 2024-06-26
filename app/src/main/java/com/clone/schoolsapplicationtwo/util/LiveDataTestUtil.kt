package com.clone.schoolsapplicationtwo.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object LiveDataTestUtil {
    fun <T> getValue(liveData: LiveData<T>, time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = Observer<T> { o ->
            data = o
            latch.countDown()
        }
        liveData.observeForever(observer)

        try {
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            liveData.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    return LiveDataTestUtil.getValue(this, time, timeUnit)
}
