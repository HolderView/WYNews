package com.example.dllo.wynews.ui.fragment.topic;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.QuestionBean;
import com.example.dllo.wynews.model.bean.TopicBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 话题 关注
 */
public class TopicAttentionFragment extends AbsBaseFragment {
    private TextView tv_attention_see_name_one, tv_attention_see_concern_count_one, tv_attention_see_talk_count_one,
            tv_attention_see_name_two, tv_attention_see_concern_count_two, tv_attention_see_talk_count_two,
            tv_attention_see_name_three, tv_attention_see_concern_count_three, tv_attention_see_talk_count_three,
            tv_attention_see_name_four, tv_attention_see_concern_count_four, tv_attention_see_talk_count_four,
            tv_attention_question_name_one, tv_attention_question_concern_count_one, tv_attention_question_question_count_one,
            tv_attention_question_name_two, tv_attention_question_concern_count_two, tv_attention_question_question_count_two,
            tv_attention_question_name_three, tv_attention_question_concern_count_three, tv_attention_question_question_count_three,
            tv_attention_question_name_four, tv_attention_question_concern_count_four, tv_attention_question_question_count_four;

    private ImageView iv_attention_see_one, iv_attention_see_two, iv_attention_see_three, iv_attention_see_four,
            iv_attention_question_one, iv_attention_question_two, iv_attention_question_three, iv_attention_question_four;

    public static TopicAttentionFragment newInstance() {
        TopicAttentionFragment fragment = new TopicAttentionFragment();
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_topic_attention;
    }

    @Override
    protected void initViews() {
        tv_attention_see_name_one = byView(R.id.tv_attention_see_name_one);
        tv_attention_see_concern_count_one = byView(R.id.tv_attention_see_concern_count_one);
        tv_attention_see_talk_count_one = byView(R.id.tv_attention_see_talk_count_one);
        tv_attention_see_name_two = byView(R.id.tv_attention_see_name_two);
        tv_attention_see_concern_count_two = byView(R.id.tv_attention_see_concern_count_two);
        tv_attention_see_talk_count_two = byView(R.id.tv_attention_see_talk_count_two);
        tv_attention_see_name_three = byView(R.id.tv_attention_see_name_three);
        tv_attention_see_concern_count_three = byView(R.id.tv_attention_see_concern_count_three);
        tv_attention_see_talk_count_three = byView(R.id.tv_attention_see_talk_count_three);
        tv_attention_see_name_four = byView(R.id.tv_attention_see_name_four);
        tv_attention_see_concern_count_four = byView(R.id.tv_attention_see_concern_count_four);
        tv_attention_see_talk_count_four = byView(R.id.tv_attention_see_talk_count_four);
        tv_attention_question_name_one = byView(R.id.tv_attention_question_name_one);
        tv_attention_question_concern_count_one = byView(R.id.tv_attention_question_concern_count_one);
        tv_attention_question_question_count_one = byView(R.id.tv_attention_question_question_count_one);
        tv_attention_question_name_two = byView(R.id.tv_attention_question_name_two);
        tv_attention_question_concern_count_two = byView(R.id.tv_attention_question_concern_count_two);
        tv_attention_question_question_count_two = byView(R.id.tv_attention_question_question_count_two);
        tv_attention_question_name_three = byView(R.id.tv_attention_question_name_three);
        tv_attention_question_concern_count_three = byView(R.id.tv_attention_question_concern_count_three);
        tv_attention_question_question_count_three = byView(R.id.tv_attention_question_question_count_three);
        tv_attention_question_name_four = byView(R.id.tv_attention_question_name_four);
        tv_attention_question_concern_count_four = byView(R.id.tv_attention_question_concern_count_four);
        tv_attention_question_question_count_four = byView(R.id.tv_attention_question_question_count_four);
        iv_attention_see_one = byView(R.id.iv_attention_see_one);
        iv_attention_see_two = byView(R.id.iv_attention_see_two);
        iv_attention_see_three = byView(R.id.iv_attention_see_three);
        iv_attention_see_four = byView(R.id.iv_attention_see_four);
        iv_attention_question_one = byView(R.id.iv_attention_question_one);
        iv_attention_question_two = byView(R.id.iv_attention_question_two);
        iv_attention_question_three = byView(R.id.iv_attention_question_three);
        iv_attention_question_four = byView(R.id.iv_attention_question_four);

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.TOPIC + 0 + UrlValues.TOPIC_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson=new Gson();
                TopicBean topicBean=gson.fromJson(result,TopicBean.class);
                List<TopicBean.DataBean.SubjectListBean> been=topicBean.getData().getSubjectList();
                Picasso.with(context).load(been.get(0).getPicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14)
                        .into(iv_attention_see_one);
                Picasso.with(context).load(been.get(1).getPicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14)
                        .into(iv_attention_see_two);
                Picasso.with(context).load(been.get(2).getPicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14).into(iv_attention_see_three);
                Picasso.with(context).load(been.get(3).getPicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14).into(iv_attention_see_four);
                tv_attention_see_name_one.setText(been.get(0).getName());
                tv_attention_see_name_two.setText(been.get(1).getName());
                tv_attention_see_name_three.setText(been.get(2).getName());
                tv_attention_see_name_four.setText(been.get(3).getName());
                tv_attention_see_concern_count_one.setText(been.get(0).getConcernCount()+"关注");
                tv_attention_see_concern_count_two.setText(been.get(1).getConcernCount()+"关注");
                tv_attention_see_concern_count_three.setText(been.get(2).getConcernCount()+"关注");
                tv_attention_see_concern_count_four.setText(been.get(3).getConcernCount()+"关注");
                tv_attention_see_talk_count_one.setText(been.get(0).getTalkCount()+"讨论");
                tv_attention_see_talk_count_two.setText(been.get(1).getTalkCount()+"讨论");
                tv_attention_see_talk_count_three.setText(been.get(2).getTalkCount()+"讨论");
                tv_attention_see_talk_count_four.setText(been.get(3).getTalkCount()+"讨论");
            }

            @Override
            public void failure() {

            }
        });
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.QUESTION + 0 + UrlValues.QUESTION_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                QuestionBean questionBean = gson.fromJson(result, QuestionBean.class);
                List<QuestionBean.DataBean.ExpertListBean> been = questionBean.getData().getExpertList();
                Picasso.with(context).load(been.get(0).getHeadpicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14).into(iv_attention_question_one);
                Picasso.with(context).load(been.get(1).getHeadpicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14).into(iv_attention_question_two);
                Picasso.with(context).load(been.get(2).getHeadpicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14).into(iv_attention_question_three);
                Picasso.with(context).load(been.get(3).getHeadpicurl())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/8,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/14).into(iv_attention_question_four);
                tv_attention_question_name_one.setText(been.get(0).getName());
                tv_attention_question_name_two.setText(been.get(1).getName());
                tv_attention_question_name_three.setText(been.get(2).getName());
                tv_attention_question_name_four.setText(been.get(3).getName());
                tv_attention_question_concern_count_one.setText(been.get(0).getConcernCount() + "关注");
                tv_attention_question_concern_count_two.setText(been.get(1).getConcernCount() + "关注");
                tv_attention_question_concern_count_three.setText(been.get(2).getConcernCount() + "关注");
                tv_attention_question_concern_count_four.setText(been.get(3).getConcernCount() + "关注");
                tv_attention_question_question_count_one.setText(been.get(0).getQuestionCount() + "提问");
                tv_attention_question_question_count_two.setText(been.get(1).getQuestionCount() + "提问");
                tv_attention_question_question_count_three.setText(been.get(2).getQuestionCount() + "提问");
                tv_attention_question_question_count_four.setText(been.get(3).getQuestionCount() + "提问");
            }

            @Override
            public void failure() {

            }
        });

    }
}
