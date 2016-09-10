package com.example.dllo.wynews.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.LiveFragment;
import com.example.dllo.wynews.ui.fragment.MeFragment;
import com.example.dllo.wynews.ui.fragment.news.NewsFragment;
import com.example.dllo.wynews.ui.fragment.TopicFragment;

/**
 * 首页Activity
 */

public class MainActivity extends AbsBaseActivity {
    private RadioGroup mainRg;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mainRg = byView(R.id.rg_main);

    }

    @Override
    protected void initData() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.rb_main_news:
                        transaction.replace(R.id.fl_main, new NewsFragment());
                        break;
                    case R.id.rb_main_live:
                        transaction.replace(R.id.fl_main,new LiveFragment());
                        break;
                    case R.id.rb_main_topic:
                        transaction.replace(R.id.fl_main,new TopicFragment());
                        break;
                    case R.id.rb_main_me:
                        transaction.replace(R.id.fl_main,new MeFragment());
                        break;
                }
                transaction.commit();
            }

        });
        mainRg.check(R.id.rb_main_news);
    }


}
