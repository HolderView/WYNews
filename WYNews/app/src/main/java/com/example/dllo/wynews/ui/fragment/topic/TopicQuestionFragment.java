package com.example.dllo.wynews.ui.fragment.topic;

import android.os.Bundle;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/12.
 */
public class TopicQuestionFragment extends AbsBaseFragment {
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

    }

    @Override
    protected void initDatas() {

    }
}
