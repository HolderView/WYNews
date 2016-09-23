package com.example.dllo.wynews.ui.adapter.select;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.SelectBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dllo on 16/9/21.
 */
public class SelectAdapter extends BaseAdapter {
    private List<SelectBean.T1467284926140Bean> datas;
    private Context context;

    public SelectAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SelectBean.T1467284926140Bean> datas) {
        this.datas = datas;
        Log.d("zzz", "datas.size():" + datas.size());
        notifyDataSetChanged();
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


    private static final int TYPE_ONE_IMG = 2;
    private static final int THREE_IMG = 1;
    private static final int LEFT_IMG = 0;

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).getImgType() == 1) {
            return TYPE_ONE_IMG;
        } else if ("photoset".equals(datas.get(position).getSkipType()) && datas.get(position).getImgextra().size() == 2 && datas.get(position).getOrder() != 1) {
            return THREE_IMG;
        } else {
            return LEFT_IMG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ThreadViewHolder threadViewHolder = null;// 一张大图
        SecondViewHolder secondViewHolder = null; // 3
        FirstViewHolder firstViewHolder = null; // 左侧图片


        if (convertView == null) {
            switch (type) {
                case TYPE_ONE_IMG:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_select_thread, parent, false);
                    threadViewHolder = new ThreadViewHolder(convertView);
                    convertView.setTag(threadViewHolder);
                    break;
                case THREE_IMG:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_select_second, parent, false);
                    secondViewHolder = new SecondViewHolder(convertView);
                    convertView.setTag(secondViewHolder);
                    break;
                case LEFT_IMG:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_select_first, parent, false);
                    firstViewHolder = new FirstViewHolder(convertView);
                    convertView.setTag(firstViewHolder);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_ONE_IMG:
                    threadViewHolder = (ThreadViewHolder) convertView.getTag();
                    break;
                case THREE_IMG:
                    secondViewHolder = (SecondViewHolder) convertView.getTag();
                    break;
                case LEFT_IMG:
                    firstViewHolder = (FirstViewHolder) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case TYPE_ONE_IMG:
                // position 0-19
                // datas 1-20
                Picasso.with(context).load(datas.get(position).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH), ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 5).into(threadViewHolder.iv_item_select_thread);
                threadViewHolder.tv_item_select_thread_title.setText(datas.get(position).getTitle());
                threadViewHolder.tv_item_select_thread_from.setText(datas.get(position).getSource());
                threadViewHolder.tv_item_select_thread_number.setText(datas.get(position).getVotecount() + "跟帖");
                break;
            case THREE_IMG:
                Picasso.with(context).load(datas.get(position).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8).into(secondViewHolder.iv_item_select_second_one);
                Picasso.with(context).load(datas.get(position).getImgextra().get(0).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8).into(secondViewHolder.iv_item_select_second_two);
                Picasso.with(context).load(datas.get(position).getImgextra().get(1).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8).into(secondViewHolder.iv_item_select_second_three);
                secondViewHolder.tv_item_select_second_title.setText(datas.get(position).getTitle());
                secondViewHolder.tv_item_select_second_from.setText(datas.get(position).getSource());
                secondViewHolder.tv_item_select_second_number.setText(datas.get(position).getVotecount() + "跟帖");
                break;
            case LEFT_IMG:
                Picasso.with(context).load(datas.get(position).getImgsrc()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 4, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8).into(firstViewHolder.iv_item_select_first);
                firstViewHolder.tv_item_select_first_title.setText(datas.get(position).getTitle());
                firstViewHolder.tv_item_select_first_from.setText(datas.get(position).getSource());
                firstViewHolder.tv_item_select_first_number.setText(datas.get(position).getVotecount() + "跟帖");
                break;

        }
        return convertView;
    }

    //一张小图片右边文字
    class FirstViewHolder {
        private TextView tv_item_select_first_title, tv_item_select_first_from, tv_item_select_first_number;
        private ImageView iv_item_select_first;

        public FirstViewHolder(View view) {
            iv_item_select_first = (ImageView) view.findViewById(R.id.iv_item_select_first);
            tv_item_select_first_title = (TextView) view.findViewById(R.id.tv_item_select_first_title);
            tv_item_select_first_from = (TextView) view.findViewById(R.id.tv_item_select_first_from);
            tv_item_select_first_number = (TextView) view.findViewById(R.id.tv_item_select_first_number);

        }
    }

    //三张图片
    class SecondViewHolder {
        private TextView tv_item_select_second_title, tv_item_select_second_from, tv_item_select_second_number;
        private ImageView iv_item_select_second_one, iv_item_select_second_two, iv_item_select_second_three;

        public SecondViewHolder(View view) {
            tv_item_select_second_from = (TextView) view.findViewById(R.id.tv_item_select_second_from);
            tv_item_select_second_title = (TextView) view.findViewById(R.id.tv_item_select_second_title);
            tv_item_select_second_number = (TextView) view.findViewById(R.id.tv_item_select_second_number);
            iv_item_select_second_one = (ImageView) view.findViewById(R.id.iv_item_select_second_one);
            iv_item_select_second_two = (ImageView) view.findViewById(R.id.iv_item_select_second_two);
            iv_item_select_second_three = (ImageView) view.findViewById(R.id.iv_item_select_second_three);
        }

    }

    //一张大图片
    class ThreadViewHolder {
        private TextView tv_item_select_thread_title, tv_item_select_thread_from, tv_item_select_thread_number;
        private ImageView iv_item_select_thread;

        public ThreadViewHolder(View view) {
            tv_item_select_thread_title = (TextView) view.findViewById(R.id.tv_item_select_thread_title);
            iv_item_select_thread = (ImageView) view.findViewById(R.id.iv_item_select_thread);
            tv_item_select_thread_from = (TextView) view.findViewById(R.id.tv_item_select_thread_from);
            tv_item_select_thread_number = (TextView) view.findViewById(R.id.tv_item_select_thread_number);


        }
    }

}
