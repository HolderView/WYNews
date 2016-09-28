package com.example.dllo.wynews.ui.fragment.topic;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 话题
 */
public class TopicFragment extends AbsBaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments=new ArrayList<>();
    private TopicViewPagerAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initViews() {
        tabLayout=byView(R.id.tl_topic);
        viewPager=byView(R.id.vp_topic);

    }

    @Override
    protected void initDatas() {
        adapter=new TopicViewPagerAdapter(getChildFragmentManager());
        fragments.add(TopicQuestionFragment.newInstance());
        fragments.add(TopicTopicFragment.newInstance());
        fragments.add(TopicAttentionFragment.newInstance());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setTabTextColors(Color.parseColor("#ffb8cb"),Color.parseColor("#ffffff"));



    }
    private class TopicViewPagerAdapter extends FragmentPagerAdapter{
        private String[] titles={"问吧","话题","关注"};

        public TopicViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments==null?null:fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments==null?0:fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
