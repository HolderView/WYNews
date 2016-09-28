package com.example.dllo.wynews.ui.fragment.live;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.ClassificationBean;
import com.example.dllo.wynews.model.net.UrlValues;
import com.example.dllo.wynews.model.net.VolleyInstance;
import com.example.dllo.wynews.model.net.VolleyResult;
import com.example.dllo.wynews.model.refresh.OnRefreshListener;
import com.example.dllo.wynews.model.refresh.RefreshListView;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.example.dllo.wynews.ui.adapter.classification.ClassificationListViewAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 直播  分类
 * 利用重写监听事件 进行多行多列的RadioButton的单点击事件
 * http://www.tuicool.com/articles/iYZvaeR
 */
public class ClassificationFragment extends AbsBaseFragment {
    public static ClassificationFragment newInstance() {
        ClassificationFragment fragment = new ClassificationFragment();
        return fragment;
    }
    private RefreshListView refreshListView;
    private ClassificationListViewAdapter adapter;
    private List<String> datas;
    private RadioGroup rg_1, rg_2, rg_3;
    private RadioButton rb_top100, rb_dazhibo, rb_zaixianchang, rb_xingzaixian, rb_zonghengtan,
            rb_zixun, rb_yule, rb_bendi, rb_tiyu, rb_shishang, rb_qiche, rb_keji, rb_caijing, rb_shenghuo;
    private List<ClassificationBean.LiveReviewBean> liveDatas;
    private List<ClassificationBean.LiveReviewBean> otherDatas;
    private String firstUrl=UrlValues.CLASSIFICATION_TOP100;
    private String endUrl=UrlValues.CLASSIFICATION_JSON;
    private int nextPage = 2;


    @Override
    protected int setLayout() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initViews() {
        refreshListView = byView(R.id.lv_classification);

    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        refreshListView.setVerticalScrollBarEnabled(false);
        liveDatas = new ArrayList<>();
        adapter = new ClassificationListViewAdapter(context);
        View view = initView();
        initClick();
        rg_1.check(R.id.rb_classification_top100);
        refreshListView.addHeaderView(view);
        refreshListView.setAdapter(adapter);
        adapter.setDatas(liveDatas);
        initNet();
    }
    //获取网络数据
    private void initNet() {
        //刚进入直播分类时自动获取Top100
        VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_TOP100 + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                final ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                liveDatas = classBean.getLive_review();
                adapter.setDatas(liveDatas);
                refreshListView.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onDownPullRefresh() {
                        Log.d("ClassificationFragment", "直接刷新了");
                        VolleyInstance.getInstance().startJsonObjRequest(firstUrl+1+endUrl, new VolleyResult() {
                            @Override
                            public void success(String result) {
                                Gson gson = new Gson();
                                ClassificationBean classificationBean = gson.fromJson(result, ClassificationBean.class);
                                liveDatas = classificationBean.getLive_review();
                                adapter.setDatas(liveDatas);
                                refreshListView.hideHeaderView();
                                Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
                                nextPage=2;
                            }

                            @Override
                            public void failure() {


                            }
                        });
                    }

                    @Override
                    public void onLoadingMore() {
                        Log.d("ClassificationFragment", "直接加载了");
                        VolleyInstance.getInstance().startJsonObjRequest(firstUrl + nextPage + endUrl, new VolleyResult() {
                            @Override
                            public void success(String result) {
                                Gson gson = new Gson();
                                ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                                otherDatas = classBean.getLive_review();
                                liveDatas.addAll(otherDatas);
                                adapter.setDatas(liveDatas);
                                adapter.notifyDataSetChanged();
                                refreshListView.hideFooterView();
                                Toast.makeText(context, "加载成功", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void failure() {
                                refreshListView.hideFooterView();
                            }
                        });
                        nextPage++;

                    }
                });

            }

            @Override
            public void failure() {

            }
        });
    }
    //点击事件
    private void initClick() {
        rb_top100.setOnClickListener(new BtnSelected("1"));
        rb_dazhibo.setOnClickListener(new BtnSelected("2"));
        rb_zaixianchang.setOnClickListener(new BtnSelected("3"));
        rb_xingzaixian.setOnClickListener(new BtnSelected("4"));
        rb_zonghengtan.setOnClickListener(new BtnSelected("5"));
        rb_zixun.setOnClickListener(new BtnSelected("6"));
        rb_yule.setOnClickListener(new BtnSelected("7"));
        rb_bendi.setOnClickListener(new BtnSelected("8"));
        rb_tiyu.setOnClickListener(new BtnSelected("9"));
        rb_shishang.setOnClickListener(new BtnSelected("10"));
        rb_qiche.setOnClickListener(new BtnSelected("11"));
        rb_keji.setOnClickListener(new BtnSelected("12"));
        rb_caijing.setOnClickListener(new BtnSelected("13"));
        rb_shenghuo.setOnClickListener(new BtnSelected("14"));
    }
    //初始化组件
    @NonNull
    private View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classification_head_view, null);
        rg_1 = (RadioGroup) view.findViewById(R.id.rg_classification_title_first);
        rg_2 = (RadioGroup) view.findViewById(R.id.rg_classification_title_second);
        rg_3 = (RadioGroup) view.findViewById(R.id.rg_classification_title_three);
        rb_top100 = (RadioButton) view.findViewById(R.id.rb_classification_top100);
        rb_dazhibo = (RadioButton) view.findViewById(R.id.rb_classification_dazhibo);
        rb_zaixianchang = (RadioButton) view.findViewById(R.id.rb_classification_zaixianchang);
        rb_xingzaixian = (RadioButton) view.findViewById(R.id.rb_classification_xingzaixian);
        rb_zonghengtan = (RadioButton) view.findViewById(R.id.rb_classification_zonghengtan);
        rb_zixun = (RadioButton) view.findViewById(R.id.rb_classification_zixun);
        rb_yule = (RadioButton) view.findViewById(R.id.rb_classification_yule);
        rb_bendi = (RadioButton) view.findViewById(R.id.rb_classification_bendi);
        rb_tiyu = (RadioButton) view.findViewById(R.id.rb_classification_tiyu);
        rb_shishang = (RadioButton) view.findViewById(R.id.rb_classification_shishang);
        rb_qiche = (RadioButton) view.findViewById(R.id.rb_classification_qiche);
        rb_keji = (RadioButton) view.findViewById(R.id.rb_classification_keji);
        rb_caijing = (RadioButton) view.findViewById(R.id.rb_classification_caijing);
        rb_shenghuo = (RadioButton) view.findViewById(R.id.rb_classification_shenghuo);
        return view;
    }


    public class BtnSelected implements View.OnClickListener {
        final public String btnId;

        public BtnSelected(String str) {
            btnId = str;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rb_classification_top100:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_TOP100 + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            final ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_TOP100;
                            endUrl=UrlValues.CLASSIFICATION_JSON;

                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_dazhibo:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_DAZHIBO + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_DAZHIBO;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_zaixianchang:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_ZAIXIANCHANG + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_ZAIXIANCHANG;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });

                    break;
                case R.id.rb_classification_xingzaixian:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_XINGZAIXIAN + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_XINGZAIXIAN;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_zonghengtan:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_ZONGHENGTAN + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_ZONGHENGTAN;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });

                    break;
                case R.id.rb_classification_zixun:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_ZIXUN + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_ZIXUN;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });

                    break;
                case R.id.rb_classification_yule:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_YULE + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_YULE;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_bendi:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_BENDI + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_BENDI;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_tiyu:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_TIYU + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_TIYU;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_shishang:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_SHISHANG + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_SHISHANG;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_qiche:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_QICHE + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_QICHE;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_keji:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_KEJI + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_KEJI;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_caijing:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_CAIJING + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_CAIJING;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
                case R.id.rb_classification_shenghuo:
                    VolleyInstance.getInstance().startJsonObjRequest(UrlValues.CLASSIFICATION_SHENGHUO + 1 + UrlValues.CLASSIFICATION_JSON, new VolleyResult() {
                        @Override
                        public void success(String result) {
                            Log.d("xxxxxxx", result);
                            Gson gson = new Gson();
                            ClassificationBean classBean = gson.fromJson(result, ClassificationBean.class);
                            List<ClassificationBean.LiveReviewBean> liveDatas = classBean.getLive_review();
                            adapter.setDatas(liveDatas);
                            firstUrl=UrlValues.CLASSIFICATION_SHENGHUO;
                            endUrl=UrlValues.CLASSIFICATION_JSON;
                        }

                        @Override
                        public void failure() {

                        }
                    });
                    break;
            }
            if (btnId.equals("1") || btnId.equals("2") || btnId.equals("3") || btnId.equals("4") || btnId.equals("5")) {
                rg_2.clearCheck();
                rg_3.clearCheck();
            } else if (btnId.equals("6") || btnId.equals("7") || btnId.equals("8") || btnId.equals("9") || btnId.equals("10")) {
                rg_1.clearCheck();
                rg_3.clearCheck();
            } else if (btnId.equals("11") || btnId.equals("12") || btnId.equals("13") || btnId.equals("14")) {
                rg_1.clearCheck();
                rg_2.clearCheck();
            }
        }


    }
}
