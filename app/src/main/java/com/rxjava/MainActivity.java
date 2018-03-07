package com.rxjava;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.Base.BaseActivity;
import com.common.model.TutorialBean;
import com.common.ui.TutorialAdapter;
import com.rxjava.one.OneActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initData();
    }

    private void initData(){
        final List<TutorialBean> tutorials = new ArrayList<>();
        String[] tutorialArray = getResources().getStringArray(R.array.tutorial);
        Class[] activityClassArray = {OneActivity.class};
        for(int i=0;i<tutorialArray.length;i++){
            TutorialBean bean = new TutorialBean();
            bean.des = tutorialArray[i];
            bean.activity = activityClassArray[i];
            tutorials.add(bean);
        }
        TutorialAdapter adapter = new TutorialAdapter(tutorials) {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this,tutorials.get(position).activity);
                MainActivity.this.startActivity(intent);
            }
        };
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mRecyclerView.setAdapter(adapter);
    }
}
