package com.example.dllo.wynews.ui.fragment.news;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.EntertainmentBean;
import com.example.dllo.wynews.model.bean.QuestionBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.adapter.entertainment.EntertainmentAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.example.dllo.wynews.view.loopview.LoopView;
import com.example.dllo.wynews.view.loopview.LoopViewEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻  娱乐
 */
public class EntertainmentFragment extends AbsBaseFragment {
    private RefreshListView refreshListView;
    private LoopView loopView;
    private List<LoopViewEntity> es = new ArrayList<>();
    private List<EntertainmentBean.T1348648517839Bean> datas;
    private List<EntertainmentBean.T1348648517839Bean> other=new ArrayList<>();
    private EntertainmentAdapter adapter;
    private int nextPage = 10;


    public static EntertainmentFragment newInstance() {
        Bundle args = new Bundle();
        EntertainmentFragment fragment = new EntertainmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_entertainment;
    }

    @Override
    protected void initViews() {
        refreshListView = byView(R.id.rl_entertainment);
    }

    @Override
    protected void initDatas() {
        adapter = new EntertainmentAdapter(context);
        refreshListView.setAdapter(adapter);
        initNet();
        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.ENTERTAINMENT + 0 + UrlValues.ENTERTAINMENT_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Log.d("yyyy", result);
                        Gson gson = new Gson();
                        EntertainmentBean bean = gson.fromJson(result, EntertainmentBean.class);
                        datas = bean.getT1348648517839();
                        adapter.setDatas(datas);
                        nextPage = 10;
                    }

                    @Override
                    public void failure() {

                    }
                });
                Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                refreshListView.hideHeaderView();
            }

            @Override
            public void onLoadingMore() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.ENTERTAINMENT + nextPage + UrlValues.ENTERTAINMENT_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        EntertainmentBean entertainmentBean = gson.fromJson(result, EntertainmentBean.class);
                        other = entertainmentBean.getT1348648517839();
                        datas.addAll(other);
                        adapter.setDatas(datas);
                        refreshListView.hideFooterView();
                    }
                    @Override
                    public void failure() {

                    }
                });
                nextPage = nextPage + 10;
                Log.d("pppppp", "nextPage:" + nextPage);
            }
        });
    }

    private void initNet() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.ENTERTAINMENT + 0 + UrlValues.ENTERTAINMENT_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Log.d("yyyy", result);
                Gson gson = new Gson();
                EntertainmentBean bean = gson.fromJson(result, EntertainmentBean.class);
                datas = bean.getT1348648517839();
                initHeadView(datas);
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });

    }

    private void initHeadView(List<EntertainmentBean.T1348648517839Bean> datas) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_entertainment_head_view, null);
        loopView = (LoopView) view.findViewById(R.id.loopview_entertainment);
        for (int i = 0; i < datas.get(0).getAds().size(); i++) {
            LoopViewEntity e = new LoopViewEntity();
            e.setImageUrl(datas.get(0).getAds().get(i).getImgsrc());
            e.setDescript(datas.get(0).getAds().get(i).getTitle());
            es.add(e);
        }
        loopView.setLoopData(es);
        refreshListView.addHeaderView(view);
        loopView.setOnItemClickListener(new LoopView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(context, "第" + position + "行", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
