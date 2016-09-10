package com.example.dllo.wynews.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.wynews.R;

/**
 * Created by dllo on 16/9/10.
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    private ImageView finishIv;
    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        finishIv=byView(R.id.iv_login_finish);

    }

    @Override
    protected void initData() {
        finishIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_login_finish:
                finish();
                break;
        }
    }
}
