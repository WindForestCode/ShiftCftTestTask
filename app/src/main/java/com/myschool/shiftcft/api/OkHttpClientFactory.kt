package com.myschool.shiftcft.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    val INSTANCE by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .build()
                )
            }
            .build()

    }
}