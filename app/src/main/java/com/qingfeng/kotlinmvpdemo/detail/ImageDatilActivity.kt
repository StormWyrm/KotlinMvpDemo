package com.qingfeng.kotlinmvpdemo.detail

import com.bumptech.glide.Glide
import com.qingfeng.kotlinmvp_lib.base.BaseTitleActivity
import com.qingfeng.kotlinmvpdemo.R
import kotlinx.android.synthetic.main.activity_image_detail.*

class ImageDatilActivity : BaseTitleActivity() {


    companion object {
        const val IMAGE_DETAIL_URL = "image_detail_url"
    }

    private val imageUrl: String? by lazy {
        intent.extras?.getString(IMAGE_DETAIL_URL)
    }


    override fun childView(): Int = R.layout.activity_image_detail

    override fun initView() {
        super.initView()
        setActivityTitle("Image Detail")
        setActivityTitleColor(R.color.white)
    }

    override fun initData() {
        super.initData()
        if (imageUrl.isNullOrEmpty()) {
            finish()
        }
        loadImage(imageUrl)
    }

    private fun loadImage(imageUrl: String?) {
        Glide.with(this)
            .load(imageUrl ?: "")
            .asBitmap()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(ziv)
    }


}