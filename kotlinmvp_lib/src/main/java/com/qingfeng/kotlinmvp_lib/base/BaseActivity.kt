package com.qingfeng.kotlinmvp_lib.base

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.qingfeng.kotlinmvp_lib.R
import com.qingfeng.kotlinmvp_lib.base.BaseApplication.Companion.getRefWatcher
import com.qingfeng.kotlinmvp_lib.registerEventBus
import com.qingfeng.kotlinmvp_lib.unregisterEventBus
import com.qingfeng.kotlinmvp_lib.util.ActivityUtils.pushActivity
import com.qingfeng.kotlinmvp_lib.util.ActivityUtils.removeActivity
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

abstract class BaseActivity : AppCompatActivity() {
    private var refWatcher: RefWatcher? = null

    open var mContext: Context by Delegates.notNull()
    open var useEventBus = false
    open var hasFinishTransitionAnim = true
    open var hasEnterTransitionAnim = true

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)//设置软键盘弹出模式
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//强制竖屏
        setContentView(getLayoutId())
        mContext = this
        pushActivity(this)
        initStatusBar()
        if (useEventBus) registerEventBus(this)
        initView()
        initData()
        refWatcher = getRefWatcher(this)
    }

    override fun onDestroy() {
        removeActivity(this)
        super.onDestroy()
        if (useEventBus)  unregisterEventBus(this)
        refWatcher?.watch(this)
    }


    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        if (hasEnterTransitionAnim) overridePendingTransitionEnter()
    }

    override fun finish() {
        super.finish()
        if (hasFinishTransitionAnim) overridePendingTransitionExit()
    }

    /**
     * 返回布局
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun initView() {}

    open fun initData() {}

    open fun initStatusBar() {}

    private fun overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right)
    }


}