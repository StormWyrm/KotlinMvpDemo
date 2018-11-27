package com.sihaiwanlian.baselib.mvp

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.exmple.corelib.state.IStateView
import com.qingfeng.kotlinmvp_lib.R
import com.qingfeng.kotlinmvp_lib.mvp.IListView
import com.qingfeng.kotlinmvp_lib.mvp.ITopPresenter
import com.qingfeng.kotlinmvp_lib.mvp.ITopView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.layout_list.*

/**
 * @FileName: {NAME}.java
 * @author: villa_mou
 * @date: {MONTH}-{HOUR}:03
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
abstract class BaseMvpTitleListActivity<V : ITopView, P : ITopPresenter> : BaseMvpTitleActivity<V, P>(), IListView<P> {

    override val mStateView: IStateView by lazy { list_sv }
    override val mRecyclerView: RecyclerView by lazy { list_rv }
    override val mRefreshLayout: SmartRefreshLayout by lazy { refreshLayout }

    open val setRecyclerViewBgColor = R.color.white
    open val setRefreshEnable = true
    open val setLoadmoreEnable = false

    override fun childView() = R.layout.layout_list

    override fun initView() {
        super.initView()
        //设置下拉刷新是否可用
        refreshLayout.setRefreshHeader(ClassicsHeader(mContext))
        refreshLayout.setRefreshFooter(ClassicsFooter(mContext))
        refreshLayout.isEnableRefresh = setRefreshEnable
        refreshLayout.isEnableLoadMore = setLoadmoreEnable

        //设置背景色
        list_rv.setBackgroundColor(ContextCompat.getColor(this, setRecyclerViewBgColor))
        //重试
        list_sv.onRetry = {
            onRetry()
        }
        //刷新
        refreshLayout.setOnRefreshListener {
            onRefresh()
        }
        //加载
        refreshLayout.setOnLoadmoreListener {
            onLoadmore()
        }

    }

    override fun loadDataSuccess() {
        mRefreshLayout.finishRefresh()
    }

    override fun noData() {
        mStateView.showEmpty()
    }

    override fun loadDataError() {
        mRefreshLayout.finishRefresh(false)
    }

    override fun loadMoreSuccess() {
        mRefreshLayout.finishLoadmore(true)
    }

    override fun loadMoreError() {
        mRefreshLayout.finishLoadmore(false)
    }

    override fun noMoreData() {
        mRefreshLayout.finishLoadmoreWithNoMoreData()
    }

    abstract fun onRefresh()

    abstract fun onRetry()

    open fun onLoadmore() {

    }
}