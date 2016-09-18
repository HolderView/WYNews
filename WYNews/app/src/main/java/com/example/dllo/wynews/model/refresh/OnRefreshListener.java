package com.example.dllo.wynews.model.refresh;

/**
 * Created by dllo on 16/9/18.
 * ListView上拉加载下拉刷新的接口
 */
public interface OnRefreshListener {
    /**
     * 下拉刷新
     */
    void onDownPullRefresh();
    /**
     * 上拉加载更多
     */
    void onLoadingMore();
}
