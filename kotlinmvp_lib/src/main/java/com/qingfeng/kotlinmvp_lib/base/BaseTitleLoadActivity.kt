package com.qingfeng.kotlinmvp_lib.base

import android.support.annotation.LayoutRes
import android.view.View
import com.exmple.corelib.state.StateView
import com.qingfeng.kotlinmvp_lib.R
import kotlinx.android.synthetic.main.layout_base_load.*

abstract class BaseTitleLoadActivity : BaseTitleActivity(){
    protected val mStateView: StateView by lazy { sv }

    override fun childView(): Int = R.layout.layout_base_load

    override fun initView() {
        super.initView()
        var childView = View.inflate(this, childLayout(), null)
        fl_container.addView(childView)

        //点击重试
        sv.onRetry = {
            onRetry()
        }
    }

    fun showSuccess() {
        sv.showSuccess()
    }

    fun showLoading() {
        sv.showLoading()
    }

    fun showError(msg: String? = null) {
        sv.showError(msg)
    }

    fun showEmpty(msg: String? = null) {
        sv.showEmpty(msg)
    }

    @LayoutRes
    abstract fun childLayout():Int

    abstract fun onRetry()
}