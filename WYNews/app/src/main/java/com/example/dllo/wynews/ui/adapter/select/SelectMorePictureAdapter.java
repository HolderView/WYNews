package com.example.dllo.wynews.ui.adapter.select;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.SelectMorePicBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/8.
 */
public class SelectMorePictureAdapter extends PagerAdapter {
    private List<View> views = new ArrayList<>();
    private SelectMorePicBean datas;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDatas(SelectMorePicBean datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.getPhotos().size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_select_more_pic, container, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_select_more_pic);
//        Glide.with(context).load(datas.getPhotos().get(position).getImgurl())
//                .override(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH),ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/2)
//                .into(imageView);
        Picasso.with(context).load(datas.getPhotos().get(position).getImgurl())
                .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH),ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT)/2)
                .config(Bitmap.Config.RGB_565)
                .into(imageView);
        container.addView(convertView);
//        views.add(convertView);
        return convertView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(views.get(position));
        container.removeView(container.getChildAt(position));
    }
}
