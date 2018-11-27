package com.qingfeng.kotlinmvp_lib

import android.content.Context
import android.support.annotation.StringRes
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.qingfeng.kotlinmvp_lib.base.BaseApplication
import org.greenrobot.eventbus.EventBus
import org.jetbrains.annotations.NotNull

fun showToastBottom(@NotNull msg: String) {
    val sToast = Toast.makeText(BaseApplication.mContext, msg, Toast.LENGTH_SHORT)
    sToast.show()
}

fun showToastBottom(@StringRes msgResId: Int) {
    val sToast = Toast.makeText(
        BaseApplication.mContext,
        BaseApplication.mContext.resources.getString(msgResId),
        Toast.LENGTH_SHORT
    )
    sToast.show()
}

fun showToastCenter(@NotNull msg: String) {
    val sToast = Toast.makeText(BaseApplication.mContext, msg, Toast.LENGTH_SHORT)
    sToast.setGravity(Gravity.CENTER, 0, 0)
    sToast.setText(msg)
    sToast.show()
}

fun showToastCenter(@StringRes msgResId: Int) {
    val sToast = Toast.makeText(
        BaseApplication.mContext,
        BaseApplication.mContext.resources.getString(msgResId),
        Toast.LENGTH_SHORT
    )
    sToast.setGravity(Gravity.CENTER, 0, 0)
    sToast.setText(BaseApplication.mContext.resources.getString(msgResId))
    sToast.show()
    sToast.show()
}

fun registerEventBus(@NotNull obj: Any) {
    if (!EventBus.getDefault().isRegistered(obj)) {
        EventBus.getDefault().register(obj)
    }
}

fun unregisterEventBus(@NotNull obj: Any) {
    if (EventBus.getDefault().isRegistered(obj)) {
        EventBus.getDefault().unregister(obj)
    }
}

fun sendEvent(@NotNull obj: Any) {
    EventBus.getDefault().post(obj)
}

/**
 * dp转px
 */
fun View.dp2px(dipValue: Float): Float {
    return (dipValue * this.resources.displayMetrics.density + 0.5f)
}

/**
 * px转dp
 */
fun View.px2dp(pxValue: Float): Float {
    return (pxValue / this.resources.displayMetrics.density + 0.5f)
}

/**
 * sp转px
 */
fun Context.sp2px(spValue: Float): Float {
    return (spValue * this.resources.displayMetrics.scaledDensity + 0.5f)
}