package com.example.dllo.wynews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dllo.wynews.R;

/**
 * Created by dllo on 16/9/9.
 * Activity的基类
 * 方法的解释:
 * setLayout:绑定布局
 * initView:绑定组件
 * initData:处理数据
 * byView:简化findViewById
 * goTo:跳转及带值跳转
 * 在finish中加入动画效果
 */
public abstract class AbsBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initViews();
        initData();
    }

    protected abstract int setLayout();

    protected abstract void initViews();

    protected abstract void initData();

    protected <T extends View> T byView(int resId) {
        return (T) findViewById(resId);
    }

    protected void goTo(Context from, Class<? extends AbsBaseActivity> to) {
        startActivity(new Intent(from, to));
    }

    protected void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle bundle) {
        Intent intent = new Intent(from, to);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition();
    }
}
