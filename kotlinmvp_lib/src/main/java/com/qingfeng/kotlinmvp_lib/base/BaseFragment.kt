package com.qingfeng.kotlinmvp_lib.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qingfeng.kotlinmvp_lib.registerEventBus
import com.qingfeng.kotlinmvp_lib.unregisterEventBus
import com.squareup.leakcanary.RefWatcher

abstract class BaseFragment : Fragment() {
    private var isViewPrepare = false
    private var isLoadData = false
    private var refWatcher: RefWatcher? = null
    open var useEventBus: Boolean = false
    open var mActivity: BaseActivity? = null
    open var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
        mActivity = mContext as? BaseActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        if (useEventBus) registerEventBus(this)
        refWatcher = BaseApplication.getRefWatcher(mContext)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView(view)
        initData()
        lazyLoadDataIfPrepared()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser) lazyLoadDataIfPrepared()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus) unregisterEventBus(this)
        refWatcher?.watch(this)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun lazyLoad()

    open fun initView(view: View) {}

    open fun initData() {}

    private fun lazyLoadDataIfPrepared(){
        if(userVisibleHint && isViewPrepare && !isLoadData){
            lazyLoad()
            isLoadData = true
        }
    }


}