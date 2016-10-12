package com.example.dllo.wynews.ui.fragment.news;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 新闻页
 * Headline头条 Select精选
 */
public class NewsFragment extends AbsBaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NewsViewPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"头条", "精选", "娱乐", "体育", "视频", "图片"};
    private ImageView iv_title_sel;
    private boolean isTure = false;
    private PopupWindow popupWindow;
    private LinearLayout layout;
    private TextView textView;
    private TextView tv_news_popup_window_headline, tv_news_popup_window_select, tv_news_popup_window_entertainment, tv_news_popup_window_sports, tv_news_popup_window_video, tv_news_popup_window_picture;


    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        tabLayout = byView(R.id.tl_news);
        viewPager = byView(R.id.vp_news);
        iv_title_sel = byView(R.id.iv_popupwindow_sel);
        layout = byView(R.id.ll_news);
        textView = byView(R.id.tv_news_title);
        popupWindow = new PopupWindow();

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
                    tabLayout.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    popupWindow.dismiss();

                } else {
                    iv_title_sel.setImageResource(R.mipmap.a0o);
                    isTure = true;
                    View view = LayoutInflater.from(context).inflate(R.layout.news_popup_window, null);
                    tv_news_popup_window_headline = (TextView) view.findViewById(R.id.tv_news_popup_window_headline);
                    tv_news_popup_window_select = (TextView) view.findViewById(R.id.tv_news_popup_window_select);
                    tv_news_popup_window_entertainment = (TextView) view.findViewById(R.id.tv_news_popup_window_entertainment);
                    tv_news_popup_window_sports = (TextView) view.findViewById(R.id.tv_news_popup_window_sports);
                    tv_news_popup_window_video = (TextView) view.findViewById(R.id.tv_news_popup_window_video);
                    tv_news_popup_window_picture = (TextView) view.findViewById(R.id.tv_news_popup_window_picture);
                    tv_news_popup_window_headline.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tabLayout.getTabAt(0).select();
                            popupWindow.dismiss();
                            tabLayout.setVisibility(View.VISIBLE);
                            isTure = false;
                            textView.setVisibility(View.GONE);
                            iv_title_sel.setImageResource(R.mipmap.a0n);
                        }
                    });
                    tv_news_popup_window_select.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tabLayout.getTabAt(1).select();
                            popupWindow.dismiss();
                            tabLayout.setVisibility(View.VISIBLE);
                            isTure = false;
                            textView.setVisibility(View.GONE);
                            iv_title_sel.setImageResource(R.mipmap.a0n);
                        }
                    });
                    tv_news_popup_window_entertainment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tabLayout.getTabAt(2).select();
                            popupWindow.dismiss();
                            tabLayout.setVisibility(View.VISIBLE);
                            isTure = false;
                            textView.setVisibility(View.GONE);
                            iv_title_sel.setImageResource(R.mipmap.a0n);
                        }
                    });
                    tv_news_popup_window_sports.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tabLayout.getTabAt(3).select();
                            popupWindow.dismiss();
                            tabLayout.setVisibility(View.VISIBLE);
                            isTure = false;
                            textView.setVisibility(View.GONE);
                            iv_title_sel.setImageResource(R.mipmap.a0n);
                        }
                    });
                    tv_news_popup_window_video.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tabLayout.getTabAt(4).select();
                            popupWindow.dismiss();
                            tabLayout.setVisibility(View.VISIBLE);
                            isTure = false;
                            textView.setVisibility(View.GONE);
                            iv_title_sel.setImageResource(R.mipmap.a0n);
                        }
                    });
                    tv_news_popup_window_picture.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tabLayout.getTabAt(5).select();
                            popupWindow.dismiss();
                            tabLayout.setVisibility(View.VISIBLE);
                            isTure = false;
                            textView.setVisibility(View.GONE);
                            iv_title_sel.setImageResource(R.mipmap.a0n);
                        }
                    });
                    switch (viewPager.getCurrentItem()) {
                        case 0:
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tv_news_popup_window_headline.setTextColor(Color.WHITE);
                            tv_news_popup_window_headline.setBackgroundResource(R.drawable.shape_news_popup_window_other);
                            break;
                        case 1:
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tv_news_popup_window_select.setTextColor(Color.WHITE);
                            tv_news_popup_window_select.setBackgroundResource(R.drawable.shape_news_popup_window_other);
                            break;
                        case 2:
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tv_news_popup_window_entertainment.setTextColor(Color.WHITE);
                            tv_news_popup_window_entertainment.setBackgroundResource(R.drawable.shape_news_popup_window_other);
                            break;
                        case 3:
                            tv_news_popup_window_sports.setTextColor(Color.WHITE);
                            tv_news_popup_window_sports.setBackgroundResource(R.drawable.shape_news_popup_window_other);
                            break;
                        case 4:
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tv_news_popup_window_video.setTextColor(Color.WHITE);
                            tv_news_popup_window_video.setBackgroundResource(R.drawable.shape_news_popup_window_other);
                            break;
                        case 5:
                            tv_news_popup_window_headline.setPadding(5, 15, 5, 15);
                            tv_news_popup_window_picture.setTextColor(Color.WHITE);
                            tv_news_popup_window_picture.setBackgroundResource(R.drawable.shape_news_popup_window_other);
                            break;

                    }
                    popupWindow.setContentView(view);
                    popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    textView.setVisibility(View.VISIBLE);
                    tabLayout.setVisibility(View.GONE);
                    popupWindow.showAsDropDown(layout);
                }
            }
        });
        Log.d("pppp", "viewPager.getCurrentItem():" + viewPager.getCurrentItem());


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
