package com.example.dllo.wynews.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.activity.LoginActivity;
import com.example.dllo.wynews.ui.activity.SettingsActivity;

/**
 * Created by dllo on 16/9/9.
 */
public class MeFragment extends AbsBaseFragment implements View.OnClickListener {
    private RelativeLayout settingRl;
    private Button loginBtn;
    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initViews() {
        settingRl=byView(R.id.rl_me_setting);
        loginBtn=byView(R.id.btn_me_login);
    }

    @Override
    protected void initDatas() {
        settingRl.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_me_setting:
                goToActivity(SettingsActivity.class);
                break;
            case R.id.btn_me_login:
                goToActivity(LoginActivity.class);
                break;

        }
    }
}
