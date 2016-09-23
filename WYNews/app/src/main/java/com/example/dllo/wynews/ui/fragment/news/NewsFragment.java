package com.example.dllo.wynews.ui.fragment.news;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 新闻页
 */
public class NewsFragment extends AbsBaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NewsViewPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"头条", "精选", "娱乐", "体育", "视频", "图片"};
    private ImageView iv_title_sel;
    private boolean isTure = false;

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        tabLayout = byView(R.id.tl_news);
        viewPager = byView(R.id.vp_news);
        iv_title_sel = byView(R.id.iv_popupwindow_sel);

    }

    @Override
    protected void initDatas() {
        adapter = new NewsViewPagerAdapter(getChildFragmentManager());
        fragments.add(HeadlineFragment.newInstance());
        fragments.add(SelectFragment.newInstance());
        fragments.add(EntertainmentFragment.newInstance());
        fragments.add(SportsFragment.newInstance());
        fragments.add(VideoFragment.newInstance());
        fragments.add(PictureFragment.newInstance());
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.parseColor("#868686"), Color.RED);
        tabLayout.setSelectedTabIndicatorColor(Color.RED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        iv_title_sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTure) {
                    iv_title_sel.setImageResource(R.mipmap.a0n);
                    isTure = false;
                } else  {
                    iv_title_sel.setImageResource(R.mipmap.a0o);
                    isTure = true;
                }
            }
        });


    }

    private class NewsViewPagerAdapter extends FragmentPagerAdapter {

        public NewsViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
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
