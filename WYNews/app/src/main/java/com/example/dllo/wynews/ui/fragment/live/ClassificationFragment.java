package com.example.dllo.wynews.ui.fragment.live;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.ui.adapter.classification.ClassificationListViewAdapter;
import com.example.dllo.wynews.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 直播  分类
 * 利用重写监听事件 进行多行多列的RadioButton的单点击事件
 * http://www.tuicool.com/articles/iYZvaeR
 */
public class ClassificationFragment extends AbsBaseFragment {
    //    private String strBtnSelected="1";
    private ListView listView;
    private ClassificationListViewAdapter adapter;
    private List<String> datas;
    private RadioGroup rg_1, rg_2, rg_3;
    private RadioButton rb_top100, rb_dazhibo, rb_zaixianchang, rb_xingzaixian, rb_zonghengtan,
            rb_zixun, rb_yule, rb_bendi, rb_tiyu, rb_shishang, rb_qiche, rb_keji, rb_caijing, rb_shenghuo;


    @Override
    protected int setLayout() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.lv_classification);

    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("第" + i + "行");
        }
        adapter = new ClassificationListViewAdapter(datas, context);


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
        rg_1.check(R.id.rb_classification_top100);
        listView.addHeaderView(view);
        listView.setAdapter(adapter);

    }

    public static ClassificationFragment newInstance() {
        ClassificationFragment fragment = new ClassificationFragment();
        return fragment;
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
                    break;
                case R.id.rb_classification_dazhibo:
                    break;
                case R.id.rb_classification_zaixianchang:
                    break;
                case R.id.rb_classification_xingzaixian:
                    break;
                case R.id.rb_classification_zonghengtan:
                    break;
                case R.id.rb_classification_zixun:
                    break;
                case R.id.rb_classification_yule:
                    break;
                case R.id.rb_classification_bendi:
                    break;
                case R.id.rb_classification_tiyu:
                    break;
                case R.id.rb_classification_shishang:
                    break;
                case R.id.rb_classification_qiche:
                    break;
                case R.id.rb_classification_keji:
                    break;
                case R.id.rb_classification_caijing:
                    break;
                case R.id.rb_classification_shenghuo:
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
