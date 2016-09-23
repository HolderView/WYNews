package com.example.dllo.wynews.ui.fragment.topic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.QuestionBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.adapter.question.QuestionAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 话题问吧
 */
public class TopicQuestionFragment extends AbsBaseFragment {
    private List<QuestionBean.DataBean.ExpertListBean> other = new ArrayList<>();
    private List<QuestionBean.DataBean.ExpertListBean> datas = new ArrayList<>();
    private int nextPage = 10;
    private RefreshListView refreshListView;
    private QuestionAdapter adapter;
    private boolean isTure = false;

    public static TopicQuestionFragment newInstance() {
        TopicQuestionFragment fragment = new TopicQuestionFragment();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_topic_question;
    }

    @Override
    protected void initViews() {
        refreshListView = byView(R.id.rl_question);
    }

    @Override
    protected void initDatas() {
        adapter = new QuestionAdapter(context);
        refreshListView.setVerticalScrollBarEnabled(false);
        initNet();
        initHeadView();
        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                initNet();
                refreshListView.hideHeaderView();
                Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadingMore() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.QUESTION + nextPage + UrlValues.QUESTION_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        QuestionBean questionBean = gson.fromJson(result, QuestionBean.class);
                        other = questionBean.getData().getExpertList();
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

    private void initHeadView() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic_question_head_view, null);
        final LinearLayout ll_question_third, ll_question_second;
        final ImageView iv_question_title;
        iv_question_title = (ImageView) view.findViewById(R.id.iv_question_title);
        ll_question_second = (LinearLayout) view.findViewById(R.id.ll_question_second);
        ll_question_third = (LinearLayout) view.findViewById(R.id.ll_question_third);
        refreshListView.addHeaderView(view);
        refreshListView.setAdapter(adapter);
        iv_question_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTure) {
                    iv_question_title.setImageResource(R.mipmap.a0n);
                    ll_question_second.setVisibility(View.GONE);
                    ll_question_third.setVisibility(View.GONE);
                    isTure = false;
                } else {
                    iv_question_title.setImageResource(R.mipmap.a0o);
                    ll_question_second.setVisibility(View.VISIBLE);
                    ll_question_third.setVisibility(View.VISIBLE);
                    isTure = true;
                }
            }
        });
    }

    private void initNet() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.QUESTION + 0 + UrlValues.QUESTION_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Log.d("qqq", result);
                Gson gson = new Gson();
                QuestionBean questionBean = gson.fromJson(result, QuestionBean.class);
                datas = questionBean.getData().getExpertList();
                adapter.setDatas(datas);
                refreshListView.setAdapter(adapter);
            }

            @Override
            public void failure() {

            }
        });
    }
}
