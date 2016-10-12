package com.example.dllo.wynews.ui.fragment.news;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.EntertainmentBean;
import com.example.dllo.wynews.model.bean.QuestionBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.activity.EntertainmentMorePicActivity;
import com.example.dllo.wynews.ui.activity.SelectActivity;
import com.example.dllo.wynews.ui.activity.SelectMorePicActivity;
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
        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (datas.get(position-2).getImgextra()!=null){
                    Intent intent=new Intent(context,SelectMorePicActivity.class);
                    intent.putExtra("select_photo_set_id",datas.get(position-2).getPhotosetID());
                    intent.putExtra("select_more_pic_replyCount",datas.get(position-2).getReplyCount()+"");
                    intent.putExtra("select_more_pic_title",datas.get(position-2).getTitle());
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(context,SelectActivity.class);
                    intent.putExtra("select_url",datas.get(position-2).getUrl());
                    intent.putExtra("select_replyCount",datas.get(position-2).getReplyCount());
                    startActivity(intent);
                }
            }
        });
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

    private void initHeadView(final List<EntertainmentBean.T1348648517839Bean> datas) {
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
                if (datas.get(0).getAds().get(position).getTag().equals("photoset")){
                    Intent intent=new Intent(context,EntertainmentMorePicActivity.class);
                    intent.putExtra("entertainment_more_pic_id",datas.get(0).getAds().get(position).getUrl());
                    intent.putExtra("entertainment_more_pic_title",datas.get(0).getAds().get(position).getTitle());
                    startActivity(intent);
                }
            }
        });
    }
}
