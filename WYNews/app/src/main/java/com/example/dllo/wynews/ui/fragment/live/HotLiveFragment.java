package com.example.dllo.wynews.ui.fragment.live;

import android.os.Bundle;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/12.
 */
public class HotLiveFragment extends AbsBaseFragment {
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

    }

    @Override
    protected void initDatas() {

    }
}
