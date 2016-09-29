package com.example.dllo.wynews.ui.adapter.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.VideoBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/28.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<VideoBean.视频Bean> datas;
    private Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<VideoBean.视频Bean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getCover())
                .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 2, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 6)
                .into(holder.iv_video_bg);
        if (!datas.get(position).getTopicImg().isEmpty()) {
            Picasso.with(context).load(datas.get(position).getTopicImg())
                    .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 20, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 30)
                    .into(holder.iv_video_src);
        }
        //将length转换为正常时间格式
        int time = datas.get(position).getLength();
        int minute = time / 60;
        int sec = time % 60;
        String secnew;
        if (sec < 10) {
            secnew = "0" + sec;
        }else {
            secnew=sec+"";
        }
        if (minute < 10) {
            holder.tv_video_time.setText("0" + minute + ":" + secnew);
        } else {
            holder.tv_video_time.setText(minute + ":" + secnew);
        }
        holder.tv_video_number.setText(" / " + datas.get(position).getPlayCount() + "播放");
        holder.tv_video_title.setText(datas.get(position).getTitle());
        if (!datas.get(position).getTopicName().isEmpty()) {
            holder.tv_video_from.setText(datas.get(position).getTopicName());
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_video_bg, iv_video_src;
        private TextView tv_video_number, tv_video_time, tv_video_title, tv_video_from;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_video_bg = (ImageView) itemView.findViewById(R.id.iv_video_bg);
            iv_video_src = (ImageView) itemView.findViewById(R.id.iv_video_src);
            tv_video_number = (TextView) itemView.findViewById(R.id.tv_video_number);
            tv_video_time = (TextView) itemView.findViewById(R.id.tv_video_time);
            tv_video_title = (TextView) itemView.findViewById(R.id.tv_video_title);
            tv_video_from = (TextView) itemView.findViewById(R.id.tv_video_from);

        }
    }
}
