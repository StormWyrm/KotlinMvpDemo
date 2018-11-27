package com.qingfeng.kotlinmvpdemo.main.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingfeng.kotlinmvp_lib.util.AppUtils;
import com.qingfeng.kotlinmvp_lib.util.GlideUtils;
import com.qingfeng.kotlinmvpdemo.R;
import com.qingfeng.kotlinmvpdemo.bean.GankIoWelfareItemBean;

import java.util.List;


/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioWelfareAdapter extends BaseQuickAdapter<GankIoWelfareItemBean, BaseViewHolder> {

    public GankioWelfareAdapter(@Nullable List<GankIoWelfareItemBean> data) {
        super(R.layout.item_gankio_welfare, data);
        init();

    }

    public GankioWelfareAdapter() {
        super(R.layout.item_gankio_welfare);
        init();
    }


    private void init() {
        setEnableLoadMore(true);
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoWelfareItemBean item) {
        GlideUtils.loadImage(AppUtils.getContext(),
                (ImageView) helper.getView(R.id.iv_item_image),
                item.getUrl(),
                R.mipmap.ic_launcher);
    }
}
