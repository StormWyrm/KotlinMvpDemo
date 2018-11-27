package com.qingfeng.kotlinmvpdemo.main

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qingfeng.kotlinmvpdemo.R
import com.qingfeng.kotlinmvpdemo.bean.GankIoWelfareItemBean
import com.qingfeng.kotlinmvpdemo.main.adapter.GankioWelfareAdapter
import com.sihaiwanlian.baselib.mvp.BaseMvpTitleListActivity

class MainActivity : BaseMvpTitleListActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    override var mPresenter: MainContract.Presenter = MainPresenter()
    override val setRefreshEnable: Boolean
        get() = false
    override val setLoadmoreEnable: Boolean
        get() = true

    private lateinit var mAdapter: GankioWelfareAdapter

    override fun hasBackIcon(): Boolean = false

    override fun initView() {
        super.initView()
        setActivityTitle("KotlinMvpDemo")
        setActivityTitleColor(R.color.white)
        initRecycleView()
        mStateView.showLoading()
        mPresenter.loadLastestList()
    }

    override fun initData() {
        super.initData()
        mPresenter.loadLastestList()
    }

    override fun onRefresh() {
    }

    override fun onLoadmore() {
        super.onLoadmore()
        mPresenter.loadMoreList()
    }

    override fun onRetry() {
        mStateView.showLoading()
        mPresenter.loadLastestList()
    }

    override fun networkError() {
        when {
            mAdapter.data.size > 0 -> {
                super.networkError()
            }
            else -> {
                mStateView.showError()
            }
        }
    }

    override fun updateContentList(list: List<GankIoWelfareItemBean>) {
        mStateView.showSuccess()
        if (mAdapter.data.isNullOrEmpty()) {
            initRecycleView(list)
        } else {
            mAdapter.addData( list)
        }
    }

    private fun initRecycleView(list: List<GankIoWelfareItemBean>? = null) {
        if (list.isNullOrEmpty()) {
            //设置一个空的adapter
            mAdapter = GankioWelfareAdapter()
            mRecyclerView.layoutManager = LinearLayoutManager(mContext)
            mRecyclerView.adapter = mAdapter
        } else {
            //第一次加载网络是重新初始化数据并绑定recyclerview
            mAdapter = GankioWelfareAdapter(list)
            //设置加载更多
            mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                mPresenter.onItemClick(position, adapter?.getItem(position) as GankIoWelfareItemBean)
            }
            mRecyclerView.layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            mRecyclerView.adapter = mAdapter
        }
    }

}
