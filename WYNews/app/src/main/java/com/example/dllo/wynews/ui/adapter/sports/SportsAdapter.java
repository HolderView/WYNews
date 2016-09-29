package com.example.dllo.wynews.ui.adapter.sports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.SportsBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class SportsAdapter extends BaseAdapter {
    private List<SportsBean.T1348649079062Bean> datas;
    private Context context;

    public SportsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SportsBean.T1348649079062Bean> datas) {
        this.datas = datas;
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
    private static final int TYPE_ONE=0;
    private static final int TYPE_MORE=1;

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).getImgextra()!=null){
            return TYPE_MORE;
        }else {
            return TYPE_ONE;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int y=getItemViewType(position);
        FirstViewHolder firstViewHolder=null;
        SecondViewHolder secondViewHolder=null;
        if (convertView==null){
            switch (y){
                case TYPE_ONE:
                    convertView= LayoutInflater.from(context).inflate(R.layout.item_sports_first,parent,false);
                    firstViewHolder=new FirstViewHolder(convertView);
                    convertView.setTag(firstViewHolder);
                    break;
                case TYPE_MORE:
                    convertView=LayoutInflater.from(context).inflate(R.layout.item_sports_second,parent,false);
                    secondViewHolder=new SecondViewHolder(convertView);
                    convertView.setTag(secondViewHolder);
                    break;
            }
        }else {
            switch (y){
                case TYPE_ONE:
                    firstViewHolder= (FirstViewHolder) convertView.getTag();
                    break;
                case TYPE_MORE:
                    secondViewHolder= (SecondViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (y){
            case TYPE_ONE:
                Picasso.with(context).load(datas.get(position).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 7)
                        .into(firstViewHolder.iv_item_sports_first);
                firstViewHolder.tv_item_sports_first_title.setText(datas.get(position).getTitle());
                firstViewHolder.tv_item_sports_first_from.setText(datas.get(position).getSource());
                firstViewHolder.tv_item_sports_first_number.setText(datas.get(position).getReplyCount()+"跟帖");
                break;
            case TYPE_MORE:
                Picasso.with(context).load(datas.get(position).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .into(secondViewHolder.iv_item_sports_second_one);
                Picasso.with(context).load(datas.get(position).getImgextra().get(0).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .into(secondViewHolder.iv_item_sports_second_two);
                Picasso.with(context).load(datas.get(position).getImgextra().get(1).getImgsrc())
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 8)
                        .into(secondViewHolder.iv_item_sports_second_three);
                secondViewHolder.tv_item_sports_second_title.setText(datas.get(position).getTitle());
                secondViewHolder.tv_item_sports_second_from.setText(datas.get(position).getSource());
                secondViewHolder.tv_item_sports_second_number.setText(datas.get(position).getReplyCount()+"跟帖");
                break;
        }
        return convertView;
    }

    //左边一张图
    class FirstViewHolder {
        private TextView tv_item_sports_first_title, tv_item_sports_first_from, tv_item_sports_first_number;
        private ImageView iv_item_sports_first;

        public FirstViewHolder(View view) {
            tv_item_sports_first_title = (TextView) view.findViewById(R.id.tv_item_sports_first_title);
            tv_item_sports_first_from = (TextView) view.findViewById(R.id.tv_item_sports_first_from);
            tv_item_sports_first_number = (TextView) view.findViewById(R.id.tv_item_sports_first_number);
            iv_item_sports_first = (ImageView) view.findViewById(R.id.iv_item_sports_first);
        }
    }

    //三张图
    class SecondViewHolder {
        private TextView tv_item_sports_second_title, tv_item_sports_second_from, tv_item_sports_second_number;
        private ImageView iv_item_sports_second_one, iv_item_sports_second_two, iv_item_sports_second_three;

        public SecondViewHolder(View view) {
            tv_item_sports_second_title = (TextView) view.findViewById(R.id.tv_item_sports_second_title);
            tv_item_sports_second_from = (TextView) view.findViewById(R.id.tv_item_sports_second_from);
            tv_item_sports_second_number = (TextView) view.findViewById(R.id.tv_item_sports_second_number);
            iv_item_sports_second_one = (ImageView) view.findViewById(R.id.iv_item_sports_second_one);
            iv_item_sports_second_two = (ImageView) view.findViewById(R.id.iv_item_sports_second_two);
            iv_item_sports_second_three = (ImageView) view.findViewById(R.id.iv_item_sports_second_three);
        }
    }
    //一张大图
    class ThirdViewHolder{
        private TextView tv_item_sports_third_title,tv_item_sports_third_from,tv_item_sports_third_number;
        private ImageView iv_item_sports_third;
        public ThirdViewHolder(View view){
            tv_item_sports_third_title = (TextView) view.findViewById(R.id.tv_item_sports_third_title);
            tv_item_sports_third_from = (TextView) view.findViewById(R.id.tv_item_sports_third_from);
            tv_item_sports_third_number = (TextView) view.findViewById(R.id.tv_item_sports_third_number);
            iv_item_sports_third = (ImageView) view.findViewById(R.id.iv_item_sports_third);
        }
    }

}
