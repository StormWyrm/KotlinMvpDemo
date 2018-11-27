package com.qingfeng.kotlinmvp_lib.util;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;



/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/28 20:07
 * @DESC: Glide的封装类
 * @VERSION: V1.0
 */

public class GlideUtils {


    /**
     * 将对应url的图片加载到ImageView之上
     *
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void loadImage(Context context, ImageView imageView, String imagePath, int resourceId) {
        Glide.with(context)
                .load(imagePath)
                .asBitmap()
                .placeholder(resourceId)
                .error(resourceId)
                .into(imageView);

    }

    //加载占位图
    public static void loadPlaceHolder(Context context, ImageView imageView, int imageId) {
        Glide.with(context)
                .load(imageId)
                .asBitmap()
                .centerCrop()
                .into(imageView);
    }

}
