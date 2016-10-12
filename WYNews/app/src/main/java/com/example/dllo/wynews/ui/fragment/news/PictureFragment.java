package com.example.dllo.wynews.ui.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.PictureBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.ui.adapter.picture.PictureAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class PictureFragment extends AbsBaseFragment {
    private ListView listView;
    private PictureAdapter adapter;
    private List<PictureBean> datas = new ArrayList<>();

    public static PictureFragment newInstance() {
        Bundle args = new Bundle();
        PictureFragment fragment = new PictureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.lv_picture);
    }

    @Override
    protected void initDatas() {
        adapter = new PictureAdapter(context);
        VolleyInstance.getInstance().startStringRequest(UrlValues.PICTURE, new VolleyResult() {
            @Override
            public void success(String result) {
                Log.d("oooooo", result);
                Gson gson = new Gson();
                Type type=new TypeToken<ArrayList<PictureBean>>(){
                }.getType();
                datas = gson.fromJson(result,type);
                Log.d("oooooo", "datas.size():" + datas.size());
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
        listView.setAdapter(adapter);


    }
}
