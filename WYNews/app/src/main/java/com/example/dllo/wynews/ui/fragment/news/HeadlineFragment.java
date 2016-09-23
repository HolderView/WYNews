package com.example.dllo.wynews.ui.fragment.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.HeadlineBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.adapter.headline.HeadlineAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.example.dllo.wynews.view.loopview.LoopView;
import com.example.dllo.wynews.view.loopview.LoopViewEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 新闻 头条
 */
public class HeadlineFragment extends AbsBaseFragment {
    private ListView listView;
    private List<LoopViewEntity> loopViewEntities=new ArrayList<>();
    private HeadlineAdapter adapter;

    public static HeadlineFragment newInstance() {
        Bundle args = new Bundle();
        HeadlineFragment fragment = new HeadlineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_headline;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.lv_headline);
    }

    @Override
    protected void initDatas() {
        adapter=new HeadlineAdapter(context);
        initNet();
    }

    private void initNet() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.HEADLINE, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                HeadlineBean headlineBean = gson.fromJson(result, HeadlineBean.class);
                List<HeadlineBean.T1348647909107Bean> datas = headlineBean.getT1348647909107();
                Log.d("HeadlineFragment", "datas:" + datas);
                Log.d("HeadlineFragment", "datas.size():" + datas.size());
                //加入头布局轮播图
                initHeadView(datas);
                datas.remove(0);
                adapter.setDatas(datas);
                listView.setAdapter(adapter);
                //去掉listview滑动条
                listView.setVerticalScrollBarEnabled(false);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void initHeadView(List<HeadlineBean.T1348647909107Bean> datas) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_headline_head_view, null);
        LoopView loopView = (LoopView) view.findViewById(R.id.loopview_head_line);
        for (int i = 0; i < datas.get(0).getAds().size(); i++) {
            if ("photoset".equals(datas.get(0).getAds().get(i).getTag())) {
                Log.d("HeadlineFragment", datas.get(0).getAds().get(i).getImgsrc());
                LoopViewEntity e = new LoopViewEntity();
                e.setImageUrl(datas.get(0).getAds().get(i).getImgsrc());
                e.setDescript(datas.get(0).getAds().get(i).getTitle());
                loopViewEntities.add(e);
            }

        }
        loopView.setLoopData(loopViewEntities);
        listView.addHeaderView(view);
        loopView.setOnItemClickListener(new LoopView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }
}
