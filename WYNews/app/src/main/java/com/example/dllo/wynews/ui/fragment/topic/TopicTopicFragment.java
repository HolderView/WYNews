package com.example.dllo.wynews.ui.fragment.topic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.TopicBean;
import com.example.dllo.wynews.model.bean.TopicHeadBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.example.dllo.wynews.ui.adapter.topic.TopicAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 */
public class TopicTopicFragment extends AbsBaseFragment {
    private RefreshListView refreshListView;
    private TopicAdapter adapter;
    private List<TopicBean.DataBean.SubjectListBean> datas = new ArrayList<>();
    private List<TopicBean.DataBean.SubjectListBean> been = new ArrayList<>();
    private View view;
    private List<View> views;
    private int nextPage = 10;

    public static TopicTopicFragment newInstance() {
        TopicTopicFragment fragment = new TopicTopicFragment();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_topic_topic;
    }

    @Override
    protected void initViews() {
        refreshListView = byView(R.id.rl_topic);
    }

    @Override
    protected void initDatas() {
        adapter = new TopicAdapter(context);
        initHeadView();
        initNet();

        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                initNet();
                nextPage=10;
                refreshListView.hideHeaderView();
            }

            @Override
            public void onLoadingMore() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.TOPIC + nextPage + UrlValues.TOPIC_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        TopicBean topicBean = gson.fromJson(result, TopicBean.class);
                        been=topicBean.getData().getSubjectList();
                        datas.addAll(been);
                        adapter.setDatas(datas);
                        Log.d("eee", "datas.size():" + datas.size());
                        refreshListView.hideFooterView();
                        Log.d("qqq", "nextPage:" + nextPage);
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
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.TOPIC + 0 + UrlValues.TOPIC_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Log.d("fff", result);
                Gson gson = new Gson();
                TopicBean topicBean = gson.fromJson(result, TopicBean.class);
                datas=topicBean.getData().getSubjectList();
                adapter.setDatas(datas);
                refreshListView.setAdapter(adapter);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void initHeadView() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.TOPIC_HEAD, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TopicHeadBean topicHeadBean = gson.fromJson(result, TopicHeadBean.class);
                view = LayoutInflater.from(context).inflate(R.layout.item_topic_head_view, null);
                ImageView iv_item_topic_head_one, iv_item_topic_head_two, iv_item_topic_head_three, iv_item_topic_head_four, iv_item_topic_head_five;
                TextView tv_item_topic_head_one, tv_item_topic_head_two, tv_item_topic_head_three, tv_item_topic_head_four, tv_item_topic_head_five;
                iv_item_topic_head_one = (ImageView) view.findViewById(R.id.iv_item_topic_head_one);
                iv_item_topic_head_two = (ImageView) view.findViewById(R.id.iv_item_topic_head_two);
                iv_item_topic_head_three = (ImageView) view.findViewById(R.id.iv_item_topic_head_three);
                iv_item_topic_head_four = (ImageView) view.findViewById(R.id.iv_item_topic_head_four);
                iv_item_topic_head_five = (ImageView) view.findViewById(R.id.iv_item_topic_head_five);
                tv_item_topic_head_one = (TextView) view.findViewById(R.id.tv_item_topic_head_one);
                tv_item_topic_head_two = (TextView) view.findViewById(R.id.tv_item_topic_head_two);
                tv_item_topic_head_three = (TextView) view.findViewById(R.id.tv_item_topic_head_three);
                tv_item_topic_head_four = (TextView) view.findViewById(R.id.tv_item_topic_head_four);
                tv_item_topic_head_five = (TextView) view.findViewById(R.id.tv_item_topic_head_five);
                Log.d("TopicTopicFragment", topicHeadBean.get话题().get(0).getPicUrl());
                Log.d("TopicTopicFragment", topicHeadBean.get话题().get(0).getTopicName());
                Picasso.with(context).load(topicHeadBean.get话题().get(0).getPicUrl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 6)
                        .into(iv_item_topic_head_one);
                Picasso.with(context).load(topicHeadBean.get话题().get(1).getPicUrl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 6)
                        .into(iv_item_topic_head_two);
                Picasso.with(context).load(topicHeadBean.get话题().get(2).getPicUrl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 6)
                        .into(iv_item_topic_head_three);
                Picasso.with(context).load(topicHeadBean.get话题().get(3).getPicUrl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 6)
                        .into(iv_item_topic_head_four);
                Picasso.with(context).load(topicHeadBean.get话题().get(4).getPicUrl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 6)
                        .into(iv_item_topic_head_five);
                tv_item_topic_head_one.setText("#" + topicHeadBean.get话题().get(0).getTopicName() + "#");
                tv_item_topic_head_two.setText("#" + topicHeadBean.get话题().get(1).getTopicName() + "#");
                tv_item_topic_head_three.setText("#" + topicHeadBean.get话题().get(2).getTopicName() + "#");
                tv_item_topic_head_four.setText("#" + topicHeadBean.get话题().get(3).getTopicName() + "#");
                tv_item_topic_head_five.setText("#" + topicHeadBean.get话题().get(4).getTopicName() + "#");
                refreshListView.addHeaderView(view);
            }

            @Override
            public void failure() {

            }
        });
    }
}
