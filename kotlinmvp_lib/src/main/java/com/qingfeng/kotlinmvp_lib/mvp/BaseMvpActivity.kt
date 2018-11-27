package com.qingfeng.kotlinmvp_lib.mvp

import android.content.Context
import com.qingfeng.kotlinmvp_lib.base.BaseActivity
import com.qingfeng.kotlinmvp_lib.util.ToastUtils

abstract class BaseMvpActivity<V : ITopView, P : ITopPresenter> : BaseActivity(), IView<P> {

    override fun initView() {
        bindToPresenter()
        super.initView()
    }

    override fun getCtx(): Context?  = this

    override fun finish(resultcode: Int) {
        finish()
    }

    override fun networkError()  = ToastUtils.showToast("Network Error")

}