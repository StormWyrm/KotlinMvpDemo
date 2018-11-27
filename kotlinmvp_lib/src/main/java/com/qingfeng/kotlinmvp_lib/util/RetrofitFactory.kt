package com.qingfeng.kotlinmvp_lib.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * FileName: com.mou.demo.http.retrofit.RetrofitUtils.java
 * Author: mouxuefei
 * date: 2017/12/22
 * version: V1.0
 * desc:
 */


abstract class RetrofitFactory<T> {
    private val time_out: Long = 15//超时时间
    var apiService: T

    init {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                // 添加请求头header
//                if (getToken().isNotEmpty()) {
//                    builder.header("userToken", getToken())
//                }
                val build = builder.build()
                chain.proceed(build)
            }
            .addInterceptor(
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {})
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )//设置打印得日志内容
            .connectTimeout(time_out, TimeUnit.SECONDS)
            .readTimeout(time_out, TimeUnit.SECONDS)
            .build()

        apiService = Retrofit.Builder()
            .baseUrl("http://gank.io")
            .addConverterFactory(GsonConverterFactory.create(buildGson())) // 添加Gson转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Retrofit到RxJava的转换器
            .client(httpClient)
            .build()
            .create(getApiService())
    }

    abstract fun getApiService(): Class<T>

    private fun buildGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
    }

    fun getService(): T {
        return apiService
    }
}