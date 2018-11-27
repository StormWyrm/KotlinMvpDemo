package com.qingfeng.kotlinmvpdemo.api

import com.qingfeng.kotlinmvp_lib.util.RetrofitFactory

class GankioRetrofit : RetrofitFactory<GankioService>() {
    override fun getApiService(): Class<GankioService> = GankioService::class.java
}