package com.qingfeng.kotlinmvp_lib.mvp

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.support.v7.widget.RecyclerView
import com.exmple.corelib.state.IStateView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface ITopView : LifecycleOwner {
    fun getCtx(): Context?
    fun bindToPresenter()
    fun finish(resultcode: Int = Activity.RESULT_OK)
    fun networkError()
}

interface ITopPresenter : LifecycleObserver {
    fun attach(view: ITopView)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachView()
}

interface ITopModel {
    fun onDetach()
}

interface IView<P : ITopPresenter> : ITopView {
    var mPresenter: P

    /**
     * 将View和Presenter绑定
     */
    override fun bindToPresenter() {
        mPresenter.attach(this)
    }
}

interface IPresenter<V : ITopView, M : ITopModel> : ITopPresenter {
    var mView: V?
    var mModel: M?
    val isViewAttached: Boolean
        get() = mView != null

    override fun attach(view: ITopView) {
        mView = view as V
    }

    override fun detachView() {
        mModel?.onDetach()
        mView = null
        mModel = null
    }

    fun checkViewAttached() {
        if (!isViewAttached)
            throw MvpViewNotAttachedException()
    }

    private class MvpViewNotAttachedException : RuntimeException("the view is not attach to presenter")
}

interface IModel : ITopModel{
    val mDisposablePool: CompositeDisposable

    fun addDisposable(disposable: Disposable) {
        mDisposablePool.add(disposable)
    }

    override fun onDetach() {
        if (!mDisposablePool.isDisposed) {
            mDisposablePool.clear()
        }
    }
}

interface ILoadView<P : ITopPresenter> : IView<P>{
    val mStateView: IStateView?

    fun showLoading()

    fun showSuccess()

    fun showEmpty(msg: String? = null)

    fun showError(msg: String? = null)
}

interface IListView<P : ITopPresenter> :IView<P>{
    val mRecyclerView: RecyclerView?
    val mStateView: IStateView?
    val mRefreshLayout: SmartRefreshLayout
    fun loadDataSuccess()
    fun loadDataError()
    fun noData()
    fun loadMoreSuccess()
    fun loadMoreError()
    fun noMoreData()
}



