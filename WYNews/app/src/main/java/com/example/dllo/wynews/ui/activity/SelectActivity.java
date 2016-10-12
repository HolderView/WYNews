package com.example.dllo.wynews.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;

/**
 * Created by dllo on 16/9/30.
 *
 */
public class SelectActivity extends AbsBaseActivity {
    private WebView web_select;
    private TextView tv_select_reply_count;
    private ImageView iv_select_finish;
    @Override
    protected int setLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected void initViews() {
        web_select=byView(R.id.web_select);
        tv_select_reply_count=byView(R.id.tv_select_reply_count);
        iv_select_finish=byView(R.id.iv_select_finish);
    }

    @Override
    protected void initData() {
        web_select.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        Intent intent=getIntent();
        String url=intent.getStringExtra("select_url");
        int reply_count=intent.getIntExtra("select_replyCount",0);
        tv_select_reply_count.setText(reply_count+"");
        Log.d("tttttt", url);
        Log.d("ttttt", "reply_count:" + reply_count);
        VolleyInstance.getInstance().startStringRequest(url, new VolleyResult() {
            @Override
            public void success(String result) {
                web_select.loadData(result,"text/html; charset=UTF-8",null);
            }

            @Override
            public void failure() {

            }
        });
        iv_select_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
