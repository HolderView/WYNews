package com.example.dllo.wynews.ui.adapter.hotlive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.HotLiveBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 */
public class HotLiveAdapter extends BaseAdapter {
    private List<HotLiveBean.LiveReviewBean> datas;
    private Context context;

    public HotLiveAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<HotLiveBean.LiveReviewBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas==null?null:datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_hot_live,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(datas.get(position).getImage()).resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH),ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/4).into(viewHolder.iv_item_hot_live_live);
        viewHolder.tv_item_hot_live_room_name.setText(datas.get(position).getRoomName());
        viewHolder.tv_item_hot_live_user_count.setText(datas.get(position).getUserCount()+"参与");
        return convertView;
    }
    class ViewHolder{
        private TextView tv_item_hot_live_room_name,tv_item_hot_live_user_count;
        private ImageView iv_item_hot_live_live;
        public ViewHolder(View view){
            tv_item_hot_live_room_name= (TextView) view.findViewById(R.id.tv_item_hot_live_room_name);
            tv_item_hot_live_user_count= (TextView) view.findViewById(R.id.tv_item_hot_live_user_count);
            iv_item_hot_live_live= (ImageView) view.findViewById(R.id.iv_item_hot_live_live);
        }
    }
}
