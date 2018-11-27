package com.qingfeng.kotlinmvp_lib.mvp

import android.support.annotation.LayoutRes
import android.view.View
import com.exmple.corelib.state.StateView
import com.qingfeng.kotlinmvp_lib.R
import com.sihaiwanlian.baselib.mvp.BaseMvpTitleActivity
import kotlinx.android.synthetic.main.layout_base_load.*

abstract class BaseMvpTitleLoadActivity<V : ITopView, P : ITopPresenter> : BaseMvpTitleActivity<V, P>(), ILoadView<P> {
    override val mStateView: StateView by lazy { sv }

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
    abstract fun childLayout():Int

    abstract fun onRetry()
}