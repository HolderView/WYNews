package com.example.dllo.wynews.model.net;

/**
 * Created by dllo on 16/9/12.
 * 网路请求的接口 (成功,失败)
 */
public interface VolleyResult {
    void success(String result);
    void failure();
}
