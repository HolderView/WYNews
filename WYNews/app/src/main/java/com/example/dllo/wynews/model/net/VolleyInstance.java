package com.example.dllo.wynews.model.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.wynews.ui.app.MyApp;

import org.json.JSONObject;

/**
 * Created by dllo on 16/9/12.
 * 网络请求的单例
 * 双重校验锁
 */
public class VolleyInstance {
    private static VolleyInstance instance;
    private RequestQueue requestQueue;
    //私有化构造方法
    private VolleyInstance(){
        requestQueue= Volley.newRequestQueue(MyApp.getContext());
    }
    //双重校验锁模式的单例
    public static VolleyInstance getInstance(){
        //如果instance为空
        if (instance==null){
            //那么进行全部线程同步扫描
            synchronized (VolleyInstance.class){
                //如果扫描后还为空
                if (instance==null){
                    //那么new一个对象
                    instance=new VolleyInstance();
                }
            }
        }
        return instance;
    }
    //String请求的方法
    public void startStringRequest(String url,final VolleyResult volleyResult){
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyResult.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResult.failure();
            }
        });
        requestQueue.add(stringRequest);
    }
    //JsonObj请求的方法
    public void startJsonObjRequest(String url,final VolleyResult volleyResult){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    volleyResult.success(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    volleyResult.failure();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }



}
