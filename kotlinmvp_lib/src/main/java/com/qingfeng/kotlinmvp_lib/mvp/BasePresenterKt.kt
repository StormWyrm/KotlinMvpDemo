package com.qingfeng.kotlinmvp_lib.mvp

open class BasePresenterKt<V : ITopView> {
    var mView: V? = null
}