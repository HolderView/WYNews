package com.example.dllo.wynews.ui.fragment.live;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.HotLiveBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.example.dllo.wynews.ui.adapter.hotlive.HotLiveAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.example.dllo.wynews.view.loopview.LoopView;
import com.example.dllo.wynews.view.loopview.LoopViewEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 直播 热门Fragment
 */
public class HotLiveFragment extends AbsBaseFragment {
    private HotLiveAdapter adapter;
    private List<HotLiveBean.LiveReviewBean> datas = new ArrayList<>();
    private LoopView loopView;
    private List<LoopViewEntity> loopViewEntities = new ArrayList<>();
    private RefreshListView refreshListView;
    private int nextPage = 2;
    private List<HotLiveBean.LiveReviewBean> other = new ArrayList<>();

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
        refreshListView = byView(R.id.rl_hot_live);
        adapter = new HotLiveAdapter(context);

    }

    @Override
    protected void initDatas() {
        initNetDatas();
        initRefreshDatas();

    }

    //刷新数据 加载数据
    private void initRefreshDatas() {
        refreshListView.setVerticalScrollBarEnabled(false);
        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.HOTLIVE + 1 + UrlValues.HOTLIVE_JSON, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        HotLiveBean hotLiveBean = gson.fromJson(result, HotLiveBean.class);
                        datas = hotLiveBean.getLive_review();
                        adapter.setDatas(datas);
                        refreshListView.setAdapter(adapter);
                        refreshListView.hideHeaderView();
                        Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                        nextPage = 2;

                    }

                    @Override
                    public void failure() {
                        Toast.makeText(context, "刷新失败", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onLoadingMore() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.HOTLIVE + nextPage + UrlValues.HOTLIVE_JSON, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        HotLiveBean hotLiveBean = gson.fromJson(result, HotLiveBean.class);
                        other = hotLiveBean.getLive_review();
                        datas.addAll(other);
                        adapter.setDatas(datas);
                        adapter.notifyDataSetChanged();
                        refreshListView.hideFooterView();
                    }

                    @Override
                    public void failure() {
                        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
                nextPage++;

            }
        });
    }

    //设置网络数据
    private void initNetDatas() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.HOTLIVE + 1 + UrlValues.HOTLIVE_JSON, new VolleyResult() {
            @Override
            public void success(String result) {
                Log.d("HotLiveFragment", result);
                Gson gson = new Gson();
                HotLiveBean topBean = gson.fromJson(result, HotLiveBean.class);

                List<HotLiveBean.TopBean> topBeanList = topBean.getTop();
                Log.d("HotLiveFragment", "topBeanList.size():" + topBeanList.size());

                View view = LayoutInflater.from(context).inflate(R.layout.loop_view_hot_live, null);
                loopView = (LoopView) view.findViewById(R.id.loopview_hot_live);


                for (int i = 0; i < topBeanList.size(); i++) {
                    Log.d("HotLiveFragment", topBeanList.get(i).getImage());
                    Log.d("xxxxxxx", "1111" + topBeanList.get(i).getRoomName());
                    LoopViewEntity e = new LoopViewEntity();
                    e.setImageUrl(topBeanList.get(i).getImage());
                    e.setDescript(topBeanList.get(i).getRoomName());

                    loopViewEntities.add(e);
                }
                Log.d("HotLiveFragment", "loopViewEntities.size():" + loopViewEntities.size());
                List<HotLiveBean.LiveReviewBean> liveReviewBeen = topBean.getLive_review();
                adapter.setDatas(liveReviewBeen);
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
