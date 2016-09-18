package com.example.dllo.wynews.ui.fragment.live;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.HotLiveBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.adapter.hotlive.HotLiveAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.example.dllo.wynews.view.loopview.LoopView;
import com.example.dllo.wynews.view.loopview.LoopViewEntity;
import com.google.gson.Gson;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 */
public class HotLiveFragment extends AbsBaseFragment {
    private HotLiveAdapter adapter;
    private List<String> datas=new ArrayList<>();
    private LoopView loopView;
    private List<HotLiveBean.TopBean> topList=new ArrayList<>();
    private List<LoopViewEntity> loopViewEntities=new ArrayList<>();
    private RefreshListView refreshListView;
    public static HotLiveFragment newInstance() {
        HotLiveFragment fragment = new HotLiveFragment();
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_hot_live;
    }

    @Override
    protected void initViews() {
        refreshListView=byView(R.id.rl_hot_live);
        adapter=new HotLiveAdapter(context);

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.HOTLIVE, new VolleyResult() {
            @Override
            public void success(String result) {
                Log.d("HotLiveFragment", result);
                Gson gson=new Gson();
                HotLiveBean topBean=gson.fromJson(result,HotLiveBean.class);

                List<HotLiveBean.TopBean> topBeanList = topBean.getTop();
                Log.d("HotLiveFragment", "topBeanList.size():" + topBeanList.size());

                View view= LayoutInflater.from(context).inflate(R.layout.loop_view_hot_live,null);
                loopView = (LoopView) view.findViewById(R.id.loopview_hot_live);

                for (int i = 0; i <topBeanList.size() ; i++) {
                    Log.d("HotLiveFragment", topBeanList.get(i).getImage());
                    LoopViewEntity e=new LoopViewEntity();
                    e.setImageUrl(topBeanList.get(i).getImage());
                    loopViewEntities.add(e);
                }
                Log.d("HotLiveFragment", "loopViewEntities.size():" + loopViewEntities.size());
                for (int i = 0; i <10 ; i++) {
                    datas.add("这是"+i);
                }
                adapter.setDatas(datas);
                loopView.setLoopData(loopViewEntities);
                refreshListView.addHeaderView(view);
                refreshListView.setAdapter(adapter);
                loopView.setOnItemClickListener(new LoopView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(context, "点击了第" + position + "行", Toast.LENGTH_SHORT).show();
                    }
                });



            }

            @Override
            public void failure() {

            }
        });


    }
}
