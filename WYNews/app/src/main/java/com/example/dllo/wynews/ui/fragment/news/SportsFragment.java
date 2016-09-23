package com.example.dllo.wynews.ui.fragment.news;

import android.os.Bundle;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/20.
 */
public class SportsFragment extends AbsBaseFragment {
    public static SportsFragment newInstance() {
        Bundle args = new Bundle();
        SportsFragment fragment = new SportsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
