package com.qingfeng.kotlinmvpdemo.api


import com.qingfeng.kotlinmvpdemo.bean.GankIoWelfareListBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by lonlife on 2018/1/3.
 */

interface GankioService {

    companion object {
        const val HOST = "http://gank.io"
    }

    /**
     * 分类数据: http://gank.io/api/data/福利/请求个数/第几页
     * 数据类型： 福利
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/福利/10/1
     */
    @GET("/api/data/福利/{pre_page}/{page}")
    fun getGankIoWelfareList(
        @Path("pre_page") pre_page: Int,
        @Path("page") page: Int
    ): Observable<GankIoWelfareListBean>


}
