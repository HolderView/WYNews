package com.example.dllo.wynews.ui.fragment.live;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class LiveFragment extends AbsBaseFragment {
    private List<Fragment> fragments = new ArrayList<>();
    private TabLayout tabLayout;
    private LiveViewPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected int setLayout() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initViews() {
        viewPager = byView(R.id.vp_live);
        tabLayout = byView(R.id.tl_live);
    }

    @Override
    protected void initDatas() {
        adapter=new LiveViewPagerAdapter(getChildFragmentManager());
        fragments.add(HotLiveFragment.newInstance());
        fragments.add(ClassificationFragment.newInstance());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setTabTextColors(Color.parseColor("#ffb8cb"),Color.parseColor("#ffffff"));

    }

    private class LiveViewPagerAdapter extends FragmentPagerAdapter {

        private String[] titles = {"热门", "分类"};

        public LiveViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return fragments == null ? null : fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }
}
