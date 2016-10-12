package com.example.dllo.wynews.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.QuestionDetailsBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.ui.adapter.question.TopicQuestionActivityAdapter;
import com.example.dllo.wynews.view.observablescrollview.ObservableScrollView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/10.
 * 问吧详情
 */
public class TopicQuestionActivity extends AbsBaseActivity implements ObservableScrollView.ScrollViewListener {
//    private int newOrHot =0;
    private LinearLayout ll_activity_topic_question;
    private RadioGroup rg_topic_question;
    private RadioButton rb_topic_question_new, rb_topic_question_hot;
    private boolean isTure = false;
    private String title;
    private int imgHeight;
    private ListView listView;
    private ObservableScrollView observableScrollView;
    private TopicQuestionActivityAdapter activityAdapter;
    private RelativeLayout rl_topic_question;
    private TextView tv_activity_topic_question_content, tv_activity_topic_question_head_title, tv_activity_topic_question_title, tv_activity_topic_question_author_guanzhu, tv_activity_topic_question_author_name, tv_activity_topic_question_author_title, tv_activity_topic_question_other_news, tv_activity_topic_question_question_count, tv_activity_topic_question_answer_count;
    private ImageView iv_activity_topic_question_title, iv_activity_topic_question_finish, iv_activity_topic_question_author, iv_activity_topic_question_arrow;

    @Override
    protected int setLayout() {
        return R.layout.activity_topic_question;
    }

    @Override
    protected void initViews() {
        rg_topic_question = byView(R.id.rg_topic_question);
        rb_topic_question_new = byView(R.id.rb_topic_question_new);
        rb_topic_question_hot = byView(R.id.rb_topic_question_hot);
        listView = byView(R.id.lv_topic_question);
        observableScrollView = byView(R.id.obs_question);
        rl_topic_question = byView(R.id.rl_topic_question);
        ll_activity_topic_question=byView(R.id.ll_activity_topic_question);
        tv_activity_topic_question_content = byView(R.id.tv_activity_topic_question_content);
        tv_activity_topic_question_head_title = byView(R.id.tv_activity_topic_question_head_title);
        tv_activity_topic_question_title = byView(R.id.tv_activity_topic_question_title);
        tv_activity_topic_question_author_guanzhu = byView(R.id.tv_activity_topic_question_author_guanzhu);
        tv_activity_topic_question_author_name = byView(R.id.tv_activity_topic_question_author_name);
        tv_activity_topic_question_author_title = byView(R.id.tv_activity_topic_question_author_title);
        tv_activity_topic_question_other_news = byView(R.id.tv_activity_topic_question_other_news);
        tv_activity_topic_question_question_count = byView(R.id.tv_activity_topic_question_question_count);
        tv_activity_topic_question_answer_count = byView(R.id.tv_activity_topic_question_answer_count);
        iv_activity_topic_question_title = byView(R.id.iv_activity_topic_question_title);
        iv_activity_topic_question_finish = byView(R.id.iv_activity_topic_question_finish);
        iv_activity_topic_question_author = byView(R.id.iv_activity_topic_question_author);
        iv_activity_topic_question_arrow = byView(R.id.iv_activity_topic_question_arrow);
    }

    @Override
    protected void initData() {
        listView.setVerticalScrollBarEnabled(false);
        rg_topic_question.check(R.id.rb_topic_question_new);
        iv_activity_topic_question_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        Log.d("TopicQuestionActivity", intent.getStringExtra("topic_question_expertId"));

        Log.d("TopicQuestionActivity", intent.getStringExtra("topic_question_picurl"));

        Log.d("TopicQuestionActivity", intent.getStringExtra("topic_question_alias"));
        activityAdapter = new TopicQuestionActivityAdapter(this);

        listView.setAdapter(activityAdapter);
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.QUESTION_NEXT + intent.getStringExtra("topic_question_expertId") + UrlValues.QUESTION_NEXT_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Log.d("TopicQuestionActivity", result);
                Gson gson = new Gson();
                final QuestionDetailsBean questionDetailsBean = gson.fromJson(result, QuestionDetailsBean.class);
                title = questionDetailsBean.getData().getExpert().getAlias();
//                datas.add(questionDetailsBean.getData().getLatestList());
                activityAdapter.setDatas(questionDetailsBean.getData().getLatestList());
                rg_topic_question.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_topic_question_new:
                                activityAdapter.setDatas(questionDetailsBean.getData().getLatestList());
//                        newOrHot = 0;
                                rb_topic_question_new.setTextColor(Color.WHITE);
                                rb_topic_question_hot.setTextColor(Color.RED);
                                // activityAdapter.setNewOrHot(newOrHot);
                                break;
                            case R.id.rb_topic_question_hot:
//                        newOrHot = 1;
                                activityAdapter.setDatas(questionDetailsBean.getData().getHotList());
                                rb_topic_question_hot.setTextColor(Color.WHITE);
                                rb_topic_question_new.setTextColor(Color.RED);
                                //  activityAdapter.setNewOrHot(newOrHot);
                                break;
                        }
                    }
                });
//                activityAdapter.setDatas(datas);
                ViewTreeObserver vto = iv_activity_topic_question_title.getViewTreeObserver();
                tv_activity_topic_question_title.setText(questionDetailsBean.getData().getExpert().getAlias());
                if (questionDetailsBean.getData().getExpert().getConcernCount() < 10000) {
                    tv_activity_topic_question_author_guanzhu.setText(questionDetailsBean.getData().getExpert().getConcernCount() + "关注");
                } else {
                    int g = questionDetailsBean.getData().getExpert().getConcernCount() / 10000;
                    int f = questionDetailsBean.getData().getExpert().getConcernCount() % 10000 / 1000;

                    tv_activity_topic_question_author_guanzhu.setText(g + "." + f + "万关注");
                }
                Picasso.with(TopicQuestionActivity.this).load(questionDetailsBean.getData().getExpert().getPicurl()).into(iv_activity_topic_question_title);
                Picasso.with(TopicQuestionActivity.this).load(questionDetailsBean.getData().getExpert().getHeadpicurl()).into(iv_activity_topic_question_author);
                tv_activity_topic_question_author_name.setText(questionDetailsBean.getData().getExpert().getName());
                tv_activity_topic_question_author_title.setText(questionDetailsBean.getData().getExpert().getTitle());
                String allText = questionDetailsBean.getData().getExpert().getDescription();
                tv_activity_topic_question_content.setMaxLines(2);
                iv_activity_topic_question_arrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isTure) {
                            tv_activity_topic_question_content.setMaxLines(2);
                            iv_activity_topic_question_arrow.setImageResource(R.mipmap.a0n);
                            isTure = false;
                        } else {
                            tv_activity_topic_question_content.setMaxLines(300);
                            iv_activity_topic_question_arrow.setImageResource(R.mipmap.a0o);
                            isTure = true;
                        }
                    }
                });
                if (questionDetailsBean.getData().getExpert().getRelatedNews()!=null){
                    String title = questionDetailsBean.getData().getExpert().getRelatedNews().get(0).getTitle();
                    tv_activity_topic_question_other_news.setText(title);
                }else {
                    tv_activity_topic_question_other_news.setVisibility(View.GONE);
                    ll_activity_topic_question.setVisibility(View.GONE);
                }

                tv_activity_topic_question_content.setText(allText);
                tv_activity_topic_question_question_count.setText(questionDetailsBean.getData().getExpert().getQuestionCount() + "提问  ·");
                tv_activity_topic_question_answer_count.setText(questionDetailsBean.getData().getExpert().getAnswerCount() + "回复");
                vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        iv_activity_topic_question_title.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        imgHeight = iv_activity_topic_question_title.getHeight();
                        observableScrollView.setScrollViewListener(TopicQuestionActivity.this);
                    }
                });


            }

            @Override
            public void failure() {

            }
        });
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            rl_topic_question.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
        } else if (y > 0 && y <= imgHeight) {
            float scale = (float) y / imgHeight;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)
            rl_topic_question.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
            tv_activity_topic_question_head_title.setText("");
        } else {
            rl_topic_question.setBackgroundColor(Color.parseColor("#ff3333"));
            tv_activity_topic_question_head_title.setText(title);
        }
    }
}
