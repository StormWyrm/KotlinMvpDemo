package com.qingfeng.kotlinmvp_lib.base

import android.app.Application
import android.content.Context
import android.os.Handler
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogcatLogStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.qingfeng.kotlinmvp_lib.BuildConfig
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates


open class BaseApplication : Application() {
    private var refWatcher: RefWatcher? = null

    companion object {
        lateinit var mContext: Application
            private set
        lateinit var mHandler: Handler
            private set

        fun getRefWatcher(context: Context?): RefWatcher? {
            val myApplication = context?.applicationContext as BaseApplication
            return myApplication.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        mHandler = Handler()
        refWatcher = setupLeakCanary()
        initLogger()//配置Logger
    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .logStrategy(LogcatLogStrategy())
            .showThreadInfo(false)   // (Optional) Whether to show thread info or not. Default true
            .methodCount(0)         // (Optional) How many method line to show. Default 2
            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

}