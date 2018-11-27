package com.qingfeng.kotlinmvp_lib.mvp

import android.support.annotation.LayoutRes
import android.view.View
import com.exmple.corelib.state.StateView
import com.qingfeng.kotlinmvp_lib.R
import kotlinx.android.synthetic.main.layout_base_load.*

abstract class BaseMvpLoadActivity<V : ITopView, P : ITopPresenter> : BaseMvpActivity<V, P>(), ILoadView<P> {
    override val mStateView: StateView by lazy { sv }

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

    override fun showSuccess() {
        sv.showSuccess()
    }

    override fun showLoading() {
        sv.showLoading()
    }

    override fun showError(msg: String?) {
        sv.showError(msg)
    }

    override fun showEmpty(msg: String?) {
        sv.showEmpty(msg)
    }

    @LayoutRes
    protected abstract fun childView(): Int

    abstract fun onRetry()
}