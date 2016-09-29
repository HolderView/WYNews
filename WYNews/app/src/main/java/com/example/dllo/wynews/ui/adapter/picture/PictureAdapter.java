package com.example.dllo.wynews.ui.adapter.picture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.PictureBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class PictureAdapter extends BaseAdapter {
    private List<PictureBean> datas;
    private Context context;

    public void setDatas(List<PictureBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public PictureAdapter(Context context) {
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

    private static final int TYPE_ONE = 0;
    private static final int TYPE_MORE = 1;

    @Override
    public int getItemViewType(int position) {
        int a = Integer.parseInt(datas.get(position).getSetid()) % 2;
        if (a == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_MORE;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FirstViewHolder firstViewHolder = null;
        SecondViewHolder secondViewHolder = null;
        int b = getItemViewType(position);
        if (convertView == null) {
            switch (b) {
                case TYPE_ONE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_picture_first, parent, false);
                    firstViewHolder = new FirstViewHolder(convertView);
                    convertView.setTag(firstViewHolder);
                    break;
                case TYPE_MORE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_picture_second, parent, false);
                    secondViewHolder = new SecondViewHolder(convertView);
                    convertView.setTag(secondViewHolder);
                    break;
            }
        } else {
            switch (b) {
                case TYPE_ONE:
                    firstViewHolder = (FirstViewHolder) convertView.getTag();
                    break;
                case TYPE_MORE:
                    secondViewHolder = (SecondViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (b) {
            case TYPE_ONE:
                if (datas.get(position).getPics().size() != 0 && null != datas.get(position).getPics()) {
                    Picasso.with(context).load(datas.get(position).getPics().get(0))
                            .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH),ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/4)
                            .into(firstViewHolder.iv_item_picture_bg_first);
                } else {
                    Picasso.with(context).load(datas.get(position).getCover())
                            .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH),ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/4)
                            .into(firstViewHolder.iv_item_picture_bg_first);
                }
                firstViewHolder.tv_item_picture_number_first.setText(datas.get(position).getImgsum() + "pics");
                firstViewHolder.tv_item_picture_title_first.setText(datas.get(position).getSetname());
                firstViewHolder.tv_item_picture_count_number_first.setText(datas.get(position).getReplynum() + "跟帖");
                break;
            case TYPE_MORE:
                if (datas.get(position).getPics() != null) {
                    Picasso.with(context).load(datas.get(position).getPics().get(0))
                            .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/3*2,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/4)
                            .into(secondViewHolder.iv_item_picture_second_one);
                    Picasso.with(context).load(datas.get(position).getPics().get(1))
                            .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/3,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/8)
                            .into(secondViewHolder.iv_item_picture_second_two);
                    Picasso.with(context).load(datas.get(position).getPics().get(2))
                            .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH)/3,ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/8)
                            .into(secondViewHolder.iv_item_picture_second_three);
                }
                secondViewHolder.tv_item_picture_number_second.setText(datas.get(position).getImgsum() + "pics");
                secondViewHolder.tv_item_picture_title_second.setText(datas.get(position).getSetname());
                secondViewHolder.tv_item_picture_count_number_second.setText(datas.get(position).getReplynum() + "跟帖");
                break;
        }

        return convertView;
    }

    class FirstViewHolder {
        private TextView tv_item_picture_number_first, tv_item_picture_title_first, tv_item_picture_count_number_first;
        private ImageView iv_item_picture_bg_first;

        public FirstViewHolder(View view) {
            tv_item_picture_number_first = (TextView) view.findViewById(R.id.tv_item_picture_number_first);
            tv_item_picture_title_first = (TextView) view.findViewById(R.id.tv_item_picture_title_first);
            tv_item_picture_count_number_first = (TextView) view.findViewById(R.id.tv_item_picture_count_number_first);
            iv_item_picture_bg_first = (ImageView) view.findViewById(R.id.iv_item_picture_bg_first);
        }
    }

    class SecondViewHolder {
        private TextView tv_item_picture_number_second, tv_item_picture_title_second, tv_item_picture_count_number_second;
        private ImageView iv_item_picture_second_one, iv_item_picture_second_two, iv_item_picture_second_three;

        public SecondViewHolder(View view) {
            tv_item_picture_number_second = (TextView) view.findViewById(R.id.tv_item_picture_number_second);
            tv_item_picture_title_second = (TextView) view.findViewById(R.id.tv_item_picture_title_second);
            tv_item_picture_count_number_second = (TextView) view.findViewById(R.id.tv_item_picture_count_number_second);
            iv_item_picture_second_one = (ImageView) view.findViewById(R.id.iv_item_picture_second_one);
            iv_item_picture_second_two = (ImageView) view.findViewById(R.id.iv_item_picture_second_two);
            iv_item_picture_second_three = (ImageView) view.findViewById(R.id.iv_item_picture_second_three);
        }
    }
}
