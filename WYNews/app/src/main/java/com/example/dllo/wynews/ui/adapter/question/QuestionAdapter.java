package com.example.dllo.wynews.ui.adapter.question;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.QuestionBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/23.
 * 话题问吧Adapter
 */
public class QuestionAdapter extends BaseAdapter {
    private List<QuestionBean.DataBean.ExpertListBean> datas;
    private Context context;

    public void setDatas(List<QuestionBean.DataBean.ExpertListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public QuestionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_question, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(datas.get(position).getHeadpicurl()).into(viewHolder.iv_item_question_headpic);
        Log.d("qqq", datas.get(position).getPicurl());
        Picasso.with(context).load(datas.get(position).getPicurl()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH), ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 4).into(viewHolder.iv_item_question);
        viewHolder.tv_item_question_name.setText(datas.get(position).getName());
        viewHolder.tv_item_question_title.setText(" / " + datas.get(position).getTitle());
        viewHolder.tv_item_question_alias.setText(datas.get(position).getAlias());
        viewHolder.tv_item_question_classification.setText(datas.get(position).getClassification());
        viewHolder.tv_item_question_concerncount.setText(datas.get(position).getConcernCount() + "关注");
        viewHolder.tv_item_question_questioncount.setText(datas.get(position).getQuestionCount() + "提问");

        return convertView;
    }

    class ViewHolder {
        private TextView tv_item_question_name, tv_item_question_title, tv_item_question_alias, tv_item_question_classification,
                tv_item_question_concerncount, tv_item_question_questioncount;
        private ImageView iv_item_question;
        private CircleImageView iv_item_question_headpic;

        public ViewHolder(View view) {
            tv_item_question_alias = (TextView) view.findViewById(R.id.tv_item_question_alias);
            tv_item_question_name = (TextView) view.findViewById(R.id.tv_item_question_name);
            tv_item_question_title = (TextView) view.findViewById(R.id.tv_item_question_title);
            tv_item_question_classification = (TextView) view.findViewById(R.id.tv_item_question_classification);
            tv_item_question_concerncount = (TextView) view.findViewById(R.id.tv_item_question_concerncount);
            tv_item_question_questioncount = (TextView) view.findViewById(R.id.tv_item_question_questioncount);
            iv_item_question = (ImageView) view.findViewById(R.id.iv_item_question);
            iv_item_question_headpic = (CircleImageView) view.findViewById(R.id.iv_item_question_headpic);
        }
    }
}
