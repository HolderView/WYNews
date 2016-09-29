package com.example.dllo.wynews.ui.fragment.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.SelectBean;
import com.example.dllo.wynews.model.bean.SportsBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.adapter.sports.SportsAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.example.dllo.wynews.view.loopview.LoopView;
import com.example.dllo.wynews.view.loopview.LoopViewEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class SportsFragment extends AbsBaseFragment {
    private RefreshListView refreshListView;
    private LoopView loopView;
    private List<LoopViewEntity> es = new ArrayList<>();
    private SportsAdapter adapter;
    private int nextPage = 10;
    private List<SportsBean.T1348649079062Bean> datas = new ArrayList<>();

    public static SportsFragment newInstance() {
        Bundle args = new Bundle();
        SportsFragment fragment = new SportsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected void initViews() {
        refreshListView = byView(R.id.rl_sports);
    }

    @Override
    protected void initDatas() {
        adapter = new SportsAdapter(context);
        initNet();
        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                refreshListView.setAdapter(adapter);
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.SPORTS + 0 + UrlValues.SPORTS_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        SportsBean sportsBean = gson.fromJson(result, SportsBean.class);
                        datas = sportsBean.getT1348649079062();
                        adapter.setDatas(datas);
                        refreshListView.hideHeaderView();
                        nextPage = 10;
                        Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure() {

                    }
                });
            }

            @Override
            public void onLoadingMore() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.SPORTS + nextPage + UrlValues.SPORTS_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        SportsBean sportsBean = gson.fromJson(result, SportsBean.class);
                        List<SportsBean.T1348649079062Bean> other = sportsBean.getT1348649079062();
                        datas.addAll(other);
                        adapter.setDatas(datas);
                        refreshListView.hideFooterView();
                    }

                    @Override
                    public void failure() {

                    }
                });
                nextPage = nextPage + 10;
            }
        });
    }

    private void initNet() {
        refreshListView.setAdapter(adapter);
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.SPORTS + 0 + UrlValues.SPORTS_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                SportsBean sportsBean = gson.fromJson(result, SportsBean.class);
                List<SportsBean.T1348649079062Bean> datas = sportsBean.getT1348649079062();
                initHeadView(datas);
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void initHeadView(List<SportsBean.T1348649079062Bean> datas) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sports_head_view, null);
        loopView = (LoopView) view.findViewById(R.id.loopview_sports);
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
                Toast.makeText(context, "点击了" + position + "行", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
