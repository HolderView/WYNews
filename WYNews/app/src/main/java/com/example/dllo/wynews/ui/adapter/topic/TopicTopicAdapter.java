package com.example.dllo.wynews.ui.adapter.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.QuestionDetailsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/10/12.
 */
public class TopicTopicAdapter extends BaseAdapter {
    private List<QuestionDetailsBean.DataBean.LatestListBean> datas;
    private Context context;
    private int newOrHot;

    public void setNewOrHot(int newOrHot) {
        this.newOrHot = newOrHot;
        notifyDataSetChanged();
    }

    public void setDatas(List<QuestionDetailsBean.DataBean.LatestListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public TopicTopicAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activity_topic_question, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        QuestionDetailsBean.DataBean.LatestListBean bean = (QuestionDetailsBean.DataBean.LatestListBean) getItem(position);

        viewHolder.tv_item_activity_topic_user_content.setText(bean.getQuestion().getContent());
        viewHolder.tv_item_activity_topic_user_name.setText(bean.getQuestion().getUserName());
        //viewHolder.tv_item_activity_topic_author_name.setText(bean.getAnswer().getSpecialistName());
        //viewHolder.tv_item_activity_topic_author_content.setText(bean.getAnswer().getContent());
        viewHolder.v.setVisibility(View.GONE);
        viewHolder.ll_item_activity_topic_author.setVisibility(View.GONE);
        if (bean.getQuestion().getUserHeadPicUrl()!=null&&!bean.getQuestion().getUserHeadPicUrl().equals("")){
            Picasso.with(context).load(bean.getQuestion().getUserHeadPicUrl()).into(viewHolder.iv_item_activity_topic_user);
        }else {
            viewHolder.iv_item_activity_topic_user.setImageResource(R.mipmap.a_5);
        }
        //Picasso.with(context).load(bean.getAnswer().getSpecialistHeadPicUrl()).into(viewHolder.iv_item_activity_topic_author);
        return convertView;
    }

    class ViewHolder {
        private TextView tv_item_activity_topic_user_name, tv_item_activity_topic_user_content, tv_item_activity_topic_author_name, tv_item_activity_topic_author_content;
        private CircleImageView iv_item_activity_topic_user, iv_item_activity_topic_author;
        private LinearLayout ll_item_activity_topic_author;
        private View v;

        public ViewHolder(View view) {
            tv_item_activity_topic_user_name = (TextView) view.findViewById(R.id.tv_item_activity_topic_user_name);
            tv_item_activity_topic_user_content = (TextView) view.findViewById(R.id.tv_item_activity_topic_user_content);
            tv_item_activity_topic_author_name = (TextView) view.findViewById(R.id.tv_item_activity_topic_author_name);
            tv_item_activity_topic_author_content = (TextView) view.findViewById(R.id.tv_item_activity_topic_author_content);
            iv_item_activity_topic_user = (CircleImageView) view.findViewById(R.id.iv_item_activity_topic_user);
            iv_item_activity_topic_author = (CircleImageView) view.findViewById(R.id.iv_item_activity_topic_author);
            ll_item_activity_topic_author = (LinearLayout) view.findViewById(R.id.ll_item_activity_topic_author);
            v = (View) view.findViewById(R.id.v_topic);

        }
    }
}
