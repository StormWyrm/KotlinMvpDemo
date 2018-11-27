package com.exmple.corelib.mvp

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.exmple.corelib.state.IStateView
import com.qingfeng.kotlinmvp_lib.R
import com.qingfeng.kotlinmvp_lib.mvp.BaseMvpActivity
import com.qingfeng.kotlinmvp_lib.mvp.IListView
import com.qingfeng.kotlinmvp_lib.mvp.ITopPresenter
import com.qingfeng.kotlinmvp_lib.mvp.ITopView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.layout_list.*

/**
 * @FileName: {NAME}.java
 * @author: villa_mou
 * @date: {MONTH}-{HOUR}:01
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
abstract class BaseMvpListActivity<V : ITopView, P : ITopPresenter> : BaseMvpActivity<V, P>(), IListView<P> {
    open val setRecyclerViewBgColor = R.color.white
    open val setRefreshEnable = true
    open val setLoadmoreEnable = false

    override val mStateView: IStateView by lazy { list_sv }
    override val mRecyclerView: RecyclerView by lazy { list_rv }
    override val mRefreshLayout: SmartRefreshLayout by lazy { refreshLayout }

    override fun getLayoutId(): Int = R.layout.layout_list

    override fun initView() {
        //设置列表背景色
        list_rv.setBackgroundColor(ContextCompat.getColor(this, setRecyclerViewBgColor))
        //重试
        list_sv.onRetry = { onRetry() }
        //刷新
        refreshLayout.setOnRefreshListener {
            onRefresh()
        }
        refreshLayout.setOnLoadmoreListener{
            onLoadmore()
        }

        //设置下拉刷新是否可用
        refreshLayout.isEnabled = setRefreshEnable
        refreshLayout.isEnableLoadMore = setLoadmoreEnable
    }

    abstract fun onRefresh()

    abstract fun onRetry()

    protected fun onLoadmore() {

    }
}