package com.example.dllo.wynews.ui.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.SelectMorePicBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.ui.adapter.select.SelectMorePictureAdapter;
import com.example.dllo.wynews.ui.app.MyApp;
import com.google.gson.Gson;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/8.
 */
public class SelectMorePicActivity extends AbsBaseActivity {
    private ViewPager viewPager;
    private ImageView iv_finish;
    private List<View> views = new ArrayList<>();
    private SelectMorePictureAdapter adapter;
    private TextView tv_title, tv_number, tv_content, tv_count;
    private SelectMorePicBean selectMorePicBean;
    private String first;
    private String count;
    private String title;


    @Override
    protected int setLayout() {
        return R.layout.activity_select_more_pic;
    }

    @Override
    protected void initViews() {
        viewPager = byView(R.id.vp_select_more_pic);
        iv_finish = byView(R.id.iv_select_more_pic_finish);
        tv_title = byView(R.id.tv_select_more_pic_title);
        tv_number = byView(R.id.tv_select_more_pic_number);
        tv_content = byView(R.id.tv_select_more_pic_content);
        tv_count = byView(R.id.tv_select_more_pic_reply_count);

    }

    @Override
    protected void initData() {
        adapter = new SelectMorePictureAdapter();
        adapter.setContext(this);
        Intent intent = getIntent();
        if (intent!=null){
            String url = intent.getStringExtra("select_photo_set_id");
            first = url.substring(4).replace("|", "/");
            count = intent.getStringExtra("select_more_pic_replyCount");
            title = intent.getStringExtra("select_more_pic_title");
            String[] titles=title.split(" ");
            tv_title.setText(titles[0]);
            tv_count.setText(count);
            VolleyInstance.getInstance().startStringRequest(UrlValues.SELECT_MORE_PIC + first + UrlValues.SELECT_MORE_PIC_JSON, new VolleyResult() {
                @Override
                public void success(String result) {
                    Log.d("LLLL", result);
                    Gson gson = new Gson();
                    selectMorePicBean = gson.fromJson(result, SelectMorePicBean.class);
                    Log.d("PPPP", "selectMorePicBean:" + selectMorePicBean);
                    tv_number.setText(1 + " / " + selectMorePicBean.getPhotos().size());
                    tv_content.setText(selectMorePicBean.getPhotos().get(0).getNote());
                    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        }

                        @Override
                        public void onPageSelected(int position) {
                            tv_number.setText((position + 1) + " / " + selectMorePicBean.getPhotos().size() + "");
                            tv_content.setText(selectMorePicBean.getPhotos().get(position).getNote());
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });


                    adapter.setDatas(selectMorePicBean);
                    viewPager.setAdapter(adapter);
                }

                @Override
                public void failure() {

                }

            });
        }




        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
