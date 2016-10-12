package com.example.dllo.wynews.ui.fragment.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.SelectBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.ui.activity.SelectActivity;
import com.example.dllo.wynews.ui.activity.SelectMorePicActivity;
import com.example.dllo.wynews.ui.adapter.select.SelectAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 新闻 精选
 */
public class SelectFragment extends AbsBaseFragment {
    private int nextNum = 20;
    private RefreshListView refreshListView;
    private SelectAdapter adapter;
    private List<SelectBean.T1467284926140Bean> other = new ArrayList<>();
    private List<SelectBean.T1467284926140Bean> datas = new ArrayList<>();
    private List<SelectBean.T1467284926140Bean> newDatas=new ArrayList<>();
    private View headView;
    private ImageView imageView;

    public static SelectFragment newInstance() {
        Bundle args = new Bundle();
        SelectFragment fragment = new SelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_select;
    }

    @Override
    protected void initViews() {
        refreshListView = byView(R.id.rl_select);
    }

    @Override
    protected void initDatas() {
        adapter = new SelectAdapter(context);
        refreshListView.setAdapter(adapter);
        refreshListView.setVerticalScrollBarEnabled(false);
        initNetWork();
        initRefresh();
        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (datas.get(position-2).getUrl_3w()!=null){
                    Intent intent=new Intent(context,SelectActivity.class);
                    intent.putExtra("select_url",datas.get(position-2).getUrl());
                    intent.putExtra("select_replyCount",datas.get(position-2).getReplyCount());
                    startActivity(intent);
                }else if (
//                        datas.get(position-2).getImgextra().size()==2||datas.get(position-2).getPhotosetID()!=null
                        !datas.get(position-2).getPhotosetID().isEmpty()||null!=datas.get(position-2).getPhotosetID()
                        ){
                    Intent intent=new Intent(context, SelectMorePicActivity.class);
                    intent.putExtra("select_photo_set_id",datas.get(position-2).getPhotosetID());
                    intent.putExtra("select_more_pic_replyCount",datas.get(position-2).getReplyCount()+"");
                    intent.putExtra("select_more_pic_title",datas.get(position-2).getTitle());
                    startActivity(intent);

                }
            }
        });


    }

    private void initRefresh() {
        //刷新加载
        refreshListView.setOnRefreshListener(new OnRefreshListener() {
            //下拉刷新
            @Override
            public void onDownPullRefresh() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.SELECT + 0 + UrlValues.SELECT_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        SelectBean selectBean = gson.fromJson(result, SelectBean.class);
                        datas = selectBean.getT1467284926140();
                        datas.remove(0);
                        adapter.setDatas(datas);
                        nextNum = 20;
                    }

                    @Override
                    public void failure() {

                    }
                });
                Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                refreshListView.hideHeaderView();
            }

            //上拉加载
            @Override
            public void onLoadingMore() {
                VolleyInstance.getInstance().startJsonObjRequest(UrlValues.SELECT + nextNum + UrlValues.SELECT_HTML, new VolleyResult() {
                    @Override
                    public void success(String result) {
                        Gson gson = new Gson();
                        SelectBean selectBean = gson.fromJson(result, SelectBean.class);
                        other = selectBean.getT1467284926140();
                        datas.addAll(other);
                        adapter.setDatas(datas);
                        refreshListView.hideFooterView();
                    }

                    @Override
                    public void failure() {

                    }
                });
                nextNum = nextNum + 20;
                Log.d("rrr", "nextNum:" + nextNum);
            }
        });
    }

    private void initNetWork() {
        //解析数据装入适配器
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.SELECT + 0 + UrlValues.SELECT_HTML, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                SelectBean bean = gson.fromJson(result, SelectBean.class);
                datas = bean.getT1467284926140();
                // 加入头布局
                addheadview(datas);
                newDatas.addAll(datas);
                //移除list内第0条数据
                //防止数据重复
                datas.remove(0);
                adapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void addheadview(final List<SelectBean.T1467284926140Bean> datasNew) {
        headView = LayoutInflater.from(context).inflate(R.layout.item_select_head_view, null);
        imageView = (ImageView) headView.findViewById(R.id.iv_item_select_head);
        TextView textView = (TextView) headView.findViewById(R.id.tv_item_select_head);
        Picasso.with(context).load(datasNew.get(0).getImgsrc()).into(imageView);
        Log.d("SelectFragment", datas.get(0).getImgsrc()+"hahaha");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SelectMorePicActivity.class);
                Log.d("SelectFragment", newDatas.get(0).getReplyCount()+"111");
                Log.d("SelectFragment", newDatas.get(0).getSkipID()+"333");
                Log.d("SelectFragment", newDatas.get(0).getImgsrc()+"222");
                intent.putExtra("select_photo_set_id",newDatas.get(0).getSkipID());
                intent.putExtra("select_more_pic_replyCount",newDatas.get(0).getReplyCount()+"");
                intent.putExtra("select_more_pic_title",newDatas.get(0).getTitle());
                startActivity(intent);
            }
        });
        textView.setText(datas.get(0).getTitle());
        refreshListView.addHeaderView(headView);
    }

}
