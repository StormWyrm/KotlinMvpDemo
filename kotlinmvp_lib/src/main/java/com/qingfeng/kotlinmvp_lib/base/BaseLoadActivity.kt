package com.qingfeng.kotlinmvp_lib.base

import android.support.annotation.LayoutRes
import android.view.View
import com.exmple.corelib.state.StateView
import com.qingfeng.kotlinmvp_lib.R
import kotlinx.android.synthetic.main.layout_base_load.*

abstract class BaseLoadActivity : BaseActivity() {
    protected val mStateView: StateView by lazy { sv }

    override fun getLayoutId(): Int = R.layout.layout_base_load

    override fun initView() {
        super.initView()
        var childView = View.inflate(this, childView(), null)
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
    protected abstract fun childView(): Int

    abstract fun onRetry()
}
