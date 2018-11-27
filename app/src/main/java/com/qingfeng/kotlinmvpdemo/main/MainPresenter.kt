package com.qingfeng.kotlinmvpdemo.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.qingfeng.kotlinmvp_lib.mvp.BasePresenterKt
import com.qingfeng.kotlinmvpdemo.bean.GankIoWelfareItemBean
import com.qingfeng.kotlinmvpdemo.detail.ImageDatilActivity

class MainPresenter : BasePresenterKt<MainContract.View>(), MainContract.Presenter {

    override var mModel: MainContract.Model? = MainModel()

    private val pre_page = 10
    private var mCurPage = 5

    @SuppressLint("CheckResult")
    override fun loadLastestList() {
        mModel?.getGankIoWelfareList(pre_page, mCurPage)
            ?.subscribe({
                mView?.loadDataSuccess()
                when {
                    it.isError ->
                        mView?.networkError()
                    else -> {
                        mCurPage++
                        mView?.updateContentList(it.results)
                    }
                }
            }, {
                mView?.loadDataError()
            })
    }

    @SuppressLint("CheckResult")
    override fun loadMoreList() {
        mModel?.getGankIoWelfareList(pre_page, mCurPage)
            ?.subscribe({
                mView?.loadMoreSuccess()
                when {
                    it.isError ->
                        mView?.networkError()
                    else ->
                        when {
                            it.results.size > 0 -> {
                                mCurPage++
                                mView?.updateContentList(it.results)
                            }
                            else -> {
                                mView?.noMoreData()
                            }

                        }
                }
            }, {
                mView?.loadMoreError()
            })
    }

    override fun onItemClick(position: Int, item: GankIoWelfareItemBean) {
        val bundle = Bundle()
        bundle.putString(ImageDatilActivity.IMAGE_DETAIL_URL,item.url)
        val intent = Intent(mView?.getCtx(),ImageDatilActivity::class.java)
        intent.putExtras(bundle)
        mView?.getCtx()?.startActivity(intent)
    }

}