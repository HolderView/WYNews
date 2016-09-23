package com.example.dllo.wynews.ui.fragment.news;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

/**
 */
public class EntertainmentFragment extends AbsBaseFragment {

    public static EntertainmentFragment newInstance() {
        Bundle args = new Bundle();
        EntertainmentFragment fragment = new EntertainmentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_entertainment;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
