package com.example.dllo.wynews.ui.fragment.topic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.TopicHeadBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/12.
 */
public class TopicTopicFragment extends AbsBaseFragment {
    private RefreshListView refreshListView;

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
        initHeadView();
    }

    private void initHeadView() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.TOPIC_HEAD, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                TopicHeadBean topicHeadBean = gson.fromJson(result, TopicHeadBean.class);
                View view = LayoutInflater.from(context).inflate(R.layout.item_topic_head_view, null);
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
                Picasso.with(context).load(topicHeadBean.get话题().get(0).getPicUrl()).into(iv_item_topic_head_one);
                Picasso.with(context).load(topicHeadBean.get话题().get(1).getPicUrl()).into(iv_item_topic_head_two);
                Picasso.with(context).load(topicHeadBean.get话题().get(2).getPicUrl()).into(iv_item_topic_head_three);
                Picasso.with(context).load(topicHeadBean.get话题().get(3).getPicUrl()).into(iv_item_topic_head_four);
                Picasso.with(context).load(topicHeadBean.get话题().get(4).getPicUrl()).into(iv_item_topic_head_five);
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
