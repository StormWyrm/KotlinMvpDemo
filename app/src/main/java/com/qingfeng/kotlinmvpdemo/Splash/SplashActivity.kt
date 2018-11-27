package com.qingfeng.kotlinmvpdemo.Splash

import android.content.Intent
import com.exmple.corelib.scheduler.IoMainScheduler
import com.qingfeng.kotlinmvp_lib.base.BaseActivity
import com.qingfeng.kotlinmvpdemo.R
import com.qingfeng.kotlinmvpdemo.main.MainActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    private val totalTime = 3L

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initData() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
            .take(totalTime + 1)
            .map { totalTime - it }
            .compose(IoMainScheduler())
            .subscribe(
                object : Observer<Long> {
                    override fun onComplete() {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Long) {
                        tv_time.text = "${t}S"
                    }


                    override fun onError(e: Throwable) {
                    }

                }
            )


    }

}
