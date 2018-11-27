package com.qingfeng.kotlinmvp_lib.mvp

import android.content.Context
import android.view.View
import com.qingfeng.kotlinmvp_lib.base.BaseFragment

abstract class BaseMvpFragment<V : ITopView, P : ITopPresenter> : BaseFragment(), IView<P> {

    override fun initView(view: View) {
        bindToPresenter()
        super.initView(view)
    }

    override fun getCtx(): Context? = mContext

    override fun finish(resultcode: Int) {
        finish()
    }
}