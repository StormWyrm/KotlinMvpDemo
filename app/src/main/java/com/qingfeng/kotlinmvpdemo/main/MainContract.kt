package com.qingfeng.kotlinmvpdemo.main

import com.qingfeng.kotlinmvp_lib.mvp.IListView
import com.qingfeng.kotlinmvp_lib.mvp.IModel
import com.qingfeng.kotlinmvp_lib.mvp.IPresenter
import com.qingfeng.kotlinmvpdemo.bean.GankIoWelfareItemBean
import com.qingfeng.kotlinmvpdemo.bean.GankIoWelfareListBean
import io.reactivex.Observable

interface MainContract {
    interface Model : IModel {
        fun getGankIoWelfareList(pre_page: Int, page: Int): Observable<GankIoWelfareListBean>
    }

    interface Presenter : IPresenter<View, Model> {
        fun loadLastestList()

        fun loadMoreList()

        fun onItemClick(position: Int, item: GankIoWelfareItemBean)
    }

    interface View : IListView<Presenter> {
        fun updateContentList(list: List<GankIoWelfareItemBean>)
    }
}