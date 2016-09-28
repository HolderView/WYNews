package com.example.dllo.wynews.ui.adapter.entertainment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.EntertainmentBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/28.
 * 娱乐Adapter
 */
public class EntertainmentAdapter extends BaseAdapter {
    private Context context;
    private List<EntertainmentBean.T1348648517839Bean> datas;

    public void setDatas(List<EntertainmentBean.T1348648517839Bean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public EntertainmentAdapter(Context context) {
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

    private static final int TYPE_MORE_IMG = 0;
    private static final int TYPE_LEFT = 1;

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).getImgextra() != null) {
            return TYPE_MORE_IMG;
        } else {
            return TYPE_LEFT;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        FirstViewHolder firstViewHolder = null;
        SecondViewHolder secondViewHolder = null;
        if (convertView == null) {
            switch (type) {
                case TYPE_MORE_IMG:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_entertainment_second, parent, false);
                    secondViewHolder = new SecondViewHolder(convertView);
                    convertView.setTag(secondViewHolder);
                    break;
                case TYPE_LEFT:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_entertainment_first, parent, false);
                    firstViewHolder = new FirstViewHolder(convertView);
                    convertView.setTag(firstViewHolder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_MORE_IMG:
                    secondViewHolder = (SecondViewHolder) convertView.getTag();
                    break;
                case TYPE_LEFT:
                    firstViewHolder = (FirstViewHolder) convertView.getTag();
                    break;

            }
        }
        switch (type){
            case TYPE_MORE_IMG:
                Picasso.with(context).load(datas.get(position).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .into(secondViewHolder.iv_item_entertainment_second_one);
                Picasso.with(context).load(datas.get(position).getImgextra().get(0).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .into(secondViewHolder.iv_item_entertainment_second_two);
                Picasso.with(context).load(datas.get(position).getImgextra().get(1).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .into(secondViewHolder.iv_item_entertainment_second_three);
                secondViewHolder.tv_item_entertainment_second_title.setText(datas.get(position).getTitle());
                secondViewHolder.tv_item_entertainment_second_from.setText(datas.get(position).getSource());
                secondViewHolder.tv_item_entertainment_second_number.setText(datas.get(position).getReplyCount()+"跟帖");
                break;
            case TYPE_LEFT:
                Picasso.with(context).load(datas.get(position).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 7)
                        .into(firstViewHolder.iv_item_entertainment_first);
                firstViewHolder.tv_item_entertainment_first_title.setText(datas.get(position).getTitle());
                firstViewHolder.tv_item_entertainment_first_from.setText(datas.get(position).getSource());
                firstViewHolder.tv_item_entertainment_first_number.setText(datas.get(position).getReplyCount()+"跟帖");
                break;
        }

        return convertView;
    }

    class FirstViewHolder {
        private ImageView iv_item_entertainment_first;
        private TextView tv_item_entertainment_first_title, tv_item_entertainment_first_from, tv_item_entertainment_first_number;

        public FirstViewHolder(View view) {
            iv_item_entertainment_first = (ImageView) view.findViewById(R.id.iv_item_entertainment_first);
            tv_item_entertainment_first_title = (TextView) view.findViewById(R.id.tv_item_entertainment_first_title);
            tv_item_entertainment_first_from = (TextView) view.findViewById(R.id.tv_item_entertainment_first_from);
            tv_item_entertainment_first_number = (TextView) view.findViewById(R.id.tv_item_entertainment_first_number);
        }
    }

    class SecondViewHolder {
        private ImageView iv_item_entertainment_second_one, iv_item_entertainment_second_two, iv_item_entertainment_second_three;
        private TextView tv_item_entertainment_second_title, tv_item_entertainment_second_from, tv_item_entertainment_second_number;

        public SecondViewHolder(View view) {
            iv_item_entertainment_second_one = (ImageView) view.findViewById(R.id.iv_item_entertainment_second_one);
            iv_item_entertainment_second_two = (ImageView) view.findViewById(R.id.iv_item_entertainment_second_two);
            iv_item_entertainment_second_three = (ImageView) view.findViewById(R.id.iv_item_entertainment_second_three);
            tv_item_entertainment_second_title = (TextView) view.findViewById(R.id.tv_item_entertainment_second_title);
            tv_item_entertainment_second_from = (TextView) view.findViewById(R.id.tv_item_entertainment_second_from);
            tv_item_entertainment_second_number = (TextView) view.findViewById(R.id.tv_item_entertainment_second_number);
        }
    }

}
