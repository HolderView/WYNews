package com.example.dllo.wynews.ui.fragment.news;

import android.os.Bundle;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/20.
 */
public class PictureFragment extends AbsBaseFragment {
    public static PictureFragment newInstance() {
        Bundle args = new Bundle();
        PictureFragment fragment = new PictureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
