package com.example.dllo.wynews.ui.adapter.classification;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.ClassificationBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 */
public class ClassificationListViewAdapter extends BaseAdapter {
    private List<ClassificationBean.LiveReviewBean> datas;
    private Context context;

    public void setDatas(List<ClassificationBean.LiveReviewBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public ClassificationListViewAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_classification, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            convertView.setMinimumHeight(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/4);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(datas.get(position).getImage())
                .resize(ScreenSizeUtil
                        .getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH),ScreenSizeUtil
                .getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/4).
                into(viewHolder.iv_item_classification_image);
        Log.d("ClassificationListViewA", datas.get(position).getRoomName());
        viewHolder.tv_item_classification_room_name.setText(datas.get(position).getRoomName());
        viewHolder.tv_item_classification_user_count.setText(datas.get(position).getUserCount()+"参与");



        return convertView;
    }

    class ViewHolder {
        private TextView tv_item_classification_room_name, tv_item_classification_user_count;
        private ImageView iv_item_classification_image;

        public ViewHolder(View view) {
            tv_item_classification_room_name = (TextView) view.findViewById(R.id.tv_item_classification_room_name);
            tv_item_classification_user_count = (TextView) view.findViewById(R.id.tv_item_classification_user_count);
            iv_item_classification_image = (ImageView) view.findViewById(R.id.iv_item_classification_image);
        }
    }
}
