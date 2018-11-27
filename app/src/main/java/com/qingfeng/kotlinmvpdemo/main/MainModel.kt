package com.qingfeng.kotlinmvpdemo.main

import com.exmple.corelib.scheduler.IoMainScheduler
import com.qingfeng.kotlinmvp_lib.mvp.BaseModelKt
import com.qingfeng.kotlinmvpdemo.api.GankioRetrofit
import com.qingfeng.kotlinmvpdemo.api.GankioService
import com.qingfeng.kotlinmvpdemo.bean.GankIoWelfareListBean
import io.reactivex.Observable

class MainModel : BaseModelKt(), MainContract.Model {
    private var mGankioService: GankioService = GankioRetrofit().getService()


    override fun getGankIoWelfareList(pre_page: Int, page: Int): Observable<GankIoWelfareListBean> {
        return mGankioService.getGankIoWelfareList(pre_page,page).compose(IoMainScheduler())
    }
}