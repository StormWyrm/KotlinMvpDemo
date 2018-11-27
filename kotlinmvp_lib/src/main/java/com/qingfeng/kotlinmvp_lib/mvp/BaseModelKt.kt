package com.qingfeng.kotlinmvp_lib.mvp

import io.reactivex.disposables.CompositeDisposable

open class BaseModelKt {
    val mDisposablePool: CompositeDisposable by lazy { CompositeDisposable() }
}