package com.common.ui;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.common.model.TutorialBean;
import com.rxjava.R;

import java.util.List;

/**
 * Description: <对此类的描述> 
 * Author: lizhen
 * Create Date: 07/03/2018
 */
public abstract class TutorialAdapter extends BaseQuickAdapter<TutorialBean,BaseViewHolder> {

    public TutorialAdapter(@Nullable List<TutorialBean> data) {
        super(R.layout.layout_item_operator,data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, TutorialBean item) {
        if (item != null){
            holder.setText(R.id.item_title,item.title)
                    .setText(R.id.item_des,item.des)
                    .getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(holder.getAdapterPosition());
                }
            });
        }
    }

    public abstract void onItemClick(int position);
}