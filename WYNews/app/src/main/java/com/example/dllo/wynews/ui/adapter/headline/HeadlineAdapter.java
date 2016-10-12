package com.example.dllo.wynews.ui.adapter.headline;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.HeadlineBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 * 头条Adapter
 */
public class HeadlineAdapter extends BaseAdapter {
    private List<HeadlineBean.T1348647909107Bean> datas;
    private Context context;
    private static final int TYPE_ONE_IMG = 1;
    private static final int TYPE_LEFT = 0;
    private static final int TYPE_THREE_IMG = 2;

    public void setDatas(List<HeadlineBean.T1348647909107Bean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public HeadlineAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
//        Log.d("eeeee", "datas.get(position).getImgnewextra().size():" + datas.get(position).getImgnewextra().size());
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("xxx", "datas.get(position).getImgnewextra():" + datas.get(position).getImgnewextra());
        if (datas.get(position).getInterest() != null && datas.get(position).getImgnewextra() == null) {
            return TYPE_LEFT;
        } else if (datas.get(position).getImgnewextra() != null) {
            return TYPE_THREE_IMG;
        } else {
            return TYPE_ONE_IMG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        FirstViewHolder firstViewHolder = null;//左边图
        SecondViewHolder secondViewHolder = null;//一张图
        ThreadViewHolder threadViewHolder = null;//三张图
        if (datas.get(position).getImgnewextra() != null) {
            Log.d("HeadlineAdapter", "datas.get(position).getImgnewextra().size():" + datas.get(position).getImgnewextra().size());
            Log.d("HeadlineAdapter", "position:" + position);
        }
        if (convertView == null) {
            switch (type) {
                case TYPE_LEFT:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_headline_first, parent, false);
                    firstViewHolder = new FirstViewHolder(convertView);
                    convertView.setTag(firstViewHolder);
                    break;
                case TYPE_ONE_IMG:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_headline_second, parent, false);
                    secondViewHolder = new SecondViewHolder(convertView);
                    convertView.setTag(secondViewHolder);
                    break;
                case TYPE_THREE_IMG:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_headline_thread, parent, false);
                    threadViewHolder = new ThreadViewHolder(convertView);
                    convertView.setTag(threadViewHolder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_LEFT:
                    firstViewHolder = (FirstViewHolder) convertView.getTag();
                    break;
                case TYPE_ONE_IMG:
                    secondViewHolder = (SecondViewHolder) convertView.getTag();
                    break;
                case TYPE_THREE_IMG:
                    threadViewHolder = (ThreadViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case TYPE_LEFT:
//                Glide.with(context).load(datas.get(position).getImgsrc())
//                        .override(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 7).into(firstViewHolder.iv_item_headline_first);
                Picasso.with(context).load(datas.get(position).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 7)
                        .config(Bitmap.Config.RGB_565)
                        .into(firstViewHolder.iv_item_headline_first);
                firstViewHolder.tv_item_headline_first_title.setText(datas.get(position).getTitle());
                if ("S".equals(datas.get(position).getInterest())) {
                    firstViewHolder.iv_item_headline_first_zhiding.setVisibility(View.VISIBLE);
                    firstViewHolder.tv_item_headline_first_from.setVisibility(View.GONE);
                    firstViewHolder.tv_item_headline_first_number.setVisibility(View.GONE);
                } else {
                    firstViewHolder.tv_item_headline_first_from.setText(datas.get(position).getSource());
                    firstViewHolder.tv_item_headline_first_number.setText(datas.get(position).getReplyCount() + "跟帖");
                }
                break;
            case TYPE_ONE_IMG:
//                Glide.with(context).load(datas.get(position).getImgsrc()).into(secondViewHolder.iv_item_headline_second);
                Picasso.with(context).load(datas.get(position).getImgsrc())
                        .config(Bitmap.Config.RGB_565)
                        .into(secondViewHolder.iv_item_headline_second);
                secondViewHolder.tv_item_headline_second_title.setText(datas.get(position).getTitle());
                secondViewHolder.tv_item_headline_second_from.setText(datas.get(position).getSource());
                secondViewHolder.tv_item_headline_second_number.setText(datas.get(position).getReplyCount() + "跟帖");
                break;
            case TYPE_THREE_IMG:
//                Glide.with(context).load(datas.get(position).getImgsrc()).override(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8).into(threadViewHolder.iv_item_headline_thread_one);
//                Glide.with(context).load(datas.get(position).getImgnewextra().get(0).getImgsrc()).override(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8).into(threadViewHolder.iv_item_headline_thread_two);
//                Glide.with(context).load(datas.get(position).getImgnewextra().get(1).getImgsrc()).override(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8).into(threadViewHolder.iv_item_headline_thread_three);
                Picasso.with(context).load(datas.get(position).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .config(Bitmap.Config.RGB_565)
                        .into(threadViewHolder.iv_item_headline_thread_one);
                Picasso.with(context).load(datas.get(position).getImgnewextra().get(0).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .config(Bitmap.Config.RGB_565)
                        .into(threadViewHolder.iv_item_headline_thread_two);
                Picasso.with(context).load(datas.get(position).getImgnewextra().get(1).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .config(Bitmap.Config.RGB_565)
                        .into(threadViewHolder.iv_item_headline_thread_three);
                threadViewHolder.tv_item_headline_thread_title.setText(datas.get(position).getTitle());
                threadViewHolder.tv_item_headline_thread_from.setText(datas.get(position).getSource());
                threadViewHolder.tv_item_headline_thread_number.setText(datas.get(position).getReplyCount() + "跟帖");
                break;
        }
        return convertView;
    }

    //左边图
    class FirstViewHolder {
        private TextView tv_item_headline_first_title, tv_item_headline_first_from, tv_item_headline_first_number;
        private ImageView iv_item_headline_first, iv_item_headline_first_zhiding;

        public FirstViewHolder(View view) {
            tv_item_headline_first_title = (TextView) view.findViewById(R.id.tv_item_headline_first_title);
            tv_item_headline_first_from = (TextView) view.findViewById(R.id.tv_item_headline_first_from);
            tv_item_headline_first_number = (TextView) view.findViewById(R.id.tv_item_headline_first_number);
            iv_item_headline_first = (ImageView) view.findViewById(R.id.iv_item_headline_first);
            iv_item_headline_first_zhiding = (ImageView) view.findViewById(R.id.iv_item_headline_first_zhiding);
        }
    }

    //一张图
    class SecondViewHolder {
        private TextView tv_item_headline_second_title, tv_item_headline_second_from, tv_item_headline_second_number;
        private ImageView iv_item_headline_second;

        public SecondViewHolder(View view) {
            tv_item_headline_second_title = (TextView) view.findViewById(R.id.tv_item_headline_second_title);
            tv_item_headline_second_from = (TextView) view.findViewById(R.id.tv_item_headline_second_from);
            tv_item_headline_second_number = (TextView) view.findViewById(R.id.tv_item_headline_second_number);
            iv_item_headline_second = (ImageView) view.findViewById(R.id.iv_item_headline_second);
        }
    }

    //三张图
    class ThreadViewHolder {
        private TextView tv_item_headline_thread_title, tv_item_headline_thread_from, tv_item_headline_thread_number;
        private ImageView iv_item_headline_thread_one, iv_item_headline_thread_two, iv_item_headline_thread_three;

        public ThreadViewHolder(View view) {
            tv_item_headline_thread_title = (TextView) view.findViewById(R.id.tv_item_headline_thread_title);
            tv_item_headline_thread_from = (TextView) view.findViewById(R.id.tv_item_headline_thread_from);
            tv_item_headline_thread_number = (TextView) view.findViewById(R.id.tv_item_headline_thread_number);
            iv_item_headline_thread_one = (ImageView) view.findViewById(R.id.iv_item_headline_thread_one);
            iv_item_headline_thread_two = (ImageView) view.findViewById(R.id.iv_item_headline_thread_two);
            iv_item_headline_thread_three = (ImageView) view.findViewById(R.id.iv_item_headline_thread_three);
        }
    }
}
