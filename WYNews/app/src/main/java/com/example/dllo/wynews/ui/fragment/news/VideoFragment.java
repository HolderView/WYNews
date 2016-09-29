package com.example.dllo.wynews.ui.fragment.news;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.VideoBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.ui.adapter.video.VideoAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class VideoFragment extends AbsBaseFragment {
    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    public static VideoFragment newInstance() {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initViews() {
        recyclerView=byView(R.id.rv_video);
    }

    @Override
    protected void initDatas() {
        adapter=new VideoAdapter(context);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.VIDEO, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson=new Gson();
                VideoBean videoBean=gson.fromJson(result,VideoBean.class);
                List<VideoBean.视频Bean> datas=videoBean.get视频();
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }
}
