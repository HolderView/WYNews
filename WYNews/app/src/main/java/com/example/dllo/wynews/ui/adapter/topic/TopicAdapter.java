package com.example.dllo.wynews.ui.adapter.topic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.wynews.R;
import com.example.dllo.wynews.model.bean.TopicBean;
import com.example.dllo.wynews.tools.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/27.
 */
public class TopicAdapter extends BaseAdapter {
    private Context context;
    private List<TopicBean.DataBean.SubjectListBean> datas;

    public void setDatas(List<TopicBean.DataBean.SubjectListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public TopicAdapter(Context context) {
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

    private static final int TYPE_MORE_PIC = 0;
    private static final int TYPE_NO_PIC = 1;
    private static final int TYPE_ONLY = 2;

    @Override
    public int getItemViewType(int position) {
        Log.d("uio", "position:" + position);
//        Log.d("TopicAdapter", "datas.get(0).getData().getRecomendExpert():" + datas.get(0).getData().getRecomendExpert());
//        Log.d("yyyy", "datas.get(0).getData().getSubjectList().get(position).getTalkContent():" + datas.get(0).getData().getSubjectList().get(position).getType());

//        if (datas.get(0).getData().getRecomendExpert().getExpertList() != null && position == 4) {
//            return TYPE_ONLY;
//        } else
        if (datas.get(position).getType() == 0) {
            return TYPE_NO_PIC;
        } else {
            return TYPE_MORE_PIC;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("uuu", "position:" + position);
        int type = getItemViewType(position);
        Log.d("uuu", "type:" + type);
        FirstViewHolder firstViewHolder = null;
        SecondViewHolder secondViewHolder = null;
        OnlyViewHolder onlyViewHolder = null;
        OnlyOtherViewHolder onlyOtherViewHolder = null;
        if (convertView == null) {
            switch (type) {
                case TYPE_NO_PIC:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_first, parent, false);
                    firstViewHolder = new FirstViewHolder(convertView);
                    convertView.setTag(firstViewHolder);
                    break;
                case TYPE_MORE_PIC:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_second, parent, false);
                    secondViewHolder = new SecondViewHolder(convertView);
                    convertView.setTag(secondViewHolder);
                    break;
//                case TYPE_ONLY:
//                    if (datas.get(0).getData().getRecomendExpert().getPosition() == 3) {
//                        convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_only, parent, false);
//                        onlyViewHolder = new OnlyViewHolder(convertView);
//                        convertView.setTag(onlyViewHolder);
//                    } else if (datas.get(0).getData().getRecomendExpert().getPosition() == 4) {
//                        convertView = LayoutInflater.from(context).inflate(R.layout.item_topic_only_other, parent, false);
//                        onlyOtherViewHolder = new OnlyOtherViewHolder(convertView);
//                        convertView.setTag(onlyOtherViewHolder);
//                    }
//                    break;
            }
        } else {
            switch (type) {
                case TYPE_NO_PIC:
                    firstViewHolder = (FirstViewHolder) convertView.getTag();
                    break;
                case TYPE_MORE_PIC:
                    secondViewHolder = (SecondViewHolder) convertView.getTag();
                    break;
//                case TYPE_ONLY:
//                    if (datas.get(0).getData().getRecomendExpert().getPosition() == 3) {
//                        onlyViewHolder = (OnlyViewHolder) convertView.getTag();
//                    } else if (datas.get(0).getData().getRecomendExpert().getPosition() == 4) {
//                        onlyOtherViewHolder = (OnlyOtherViewHolder) convertView.getTag();
//                    }
//                    break;
            }
        }
        switch (type) {
            case TYPE_NO_PIC:
                if (!datas.get(position).getTalkContent().get(0).getUserHeadPicUrl().isEmpty()) {
                    Picasso.with(context).load(datas.get(position).getTalkContent().get(0).getUserHeadPicUrl())
                            .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 15, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 25)
                            .into(firstViewHolder.iv_item_topic_first_first);
                } else {
//                    firstViewHolder.iv_item_topic_first_first.setMaxWidth(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 15);
//                    firstViewHolder.iv_item_topic_first_first.setMaxHeight(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 25);
                    firstViewHolder.iv_item_topic_first_first.setImageResource(R.mipmap.a_5);
                }
                if (!datas.get(position).getTalkContent().get(1).getUserHeadPicUrl().isEmpty()) {
                    Picasso.with(context).load(datas.get(position).getTalkContent().get(1).getUserHeadPicUrl())
                            .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 15, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 25)
                            .into(firstViewHolder.iv_item_topic_first_second);
                } else {
//                    int wSecond=firstViewHolder.iv_item_topic_first_second.getWidth();
//                    wSecond=ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 15;
//                    firstViewHolder.iv_item_topic_first_second.setMaxWidth(40);
                    firstViewHolder.iv_item_topic_first_second.setImageResource(R.mipmap.a_5);
                }
                firstViewHolder.tv_item_topic_first_name.setText("#" + datas.get(position).getName() + "#");
                firstViewHolder.tv_item_topic_first_first.setText(" " + datas.get(position).getTalkContent().get(0).getContent() + " ");
                firstViewHolder.tv_item_topic_first_second.setText(" " + datas.get(position).getTalkContent().get(1).getContent() + " ");
                firstViewHolder.tv_item_topic_first_classification.setText(datas.get(position).getClassification());
                firstViewHolder.tv_item_first_concerncount.setText(datas.get(position).getConcernCount() + "关注");
                firstViewHolder.tv_item_first_talkcount.setText(datas.get(position).getTalkCount() + "讨论");
                break;
            case TYPE_MORE_PIC:
                Picasso.with(context).load(datas.get(position).getTalkPicture().get(0))
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 5)
                        .into(secondViewHolder.iv_item_topic_second_one);
                Picasso.with(context).load(datas.get(position).getTalkPicture().get(1))
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 5)
                        .into(secondViewHolder.iv_item_topic_second_two);
                Picasso.with(context).load(datas.get(position).getTalkPicture().get(2))
                        .resize(ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.WIDTH) / 3, ScreenSizeUtil.getScreenSize(context, ScreenSizeUtil.ScreenState.HEIGHT) / 5)
                        .into(secondViewHolder.iv_item_topic_second_three);
                secondViewHolder.tv_item_topic_second_name.setText("#" + datas.get(position).getName() + "#");
                secondViewHolder.tv_item_topic_second_classification.setText(datas.get(position).getClassification());
                secondViewHolder.tv_item_topic_second_concern_count.setText(datas.get(position).getConcernCount() + "关注");
                secondViewHolder.tv_item_topic_second_talk_count.setText(datas.get(position).getTalkCount() + "讨论");
                break;
//            case TYPE_ONLY:
//                if (datas.get(0).getData().getRecomendExpert().getPosition() == 3) {
//                    Picasso.with(context).load(datas.get(0).getData().getRecomendExpert().getExpertList().get(0).getHeadpicurl()).into(onlyViewHolder.iv_item_topic_only_one);
//                    Picasso.with(context).load(datas.get(0).getData().getRecomendExpert().getExpertList().get(1).getHeadpicurl()).into(onlyViewHolder.iv_item_topic_only_two);
//                    Picasso.with(context).load(datas.get(0).getData().getRecomendExpert().getExpertList().get(2).getHeadpicurl()).into(onlyViewHolder.iv_item_topic_only_three);
//                    onlyViewHolder.tv_item_topic_only_name_one.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(0).getName());
//                    onlyViewHolder.tv_item_topic_only_concern_count_one.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(0).getConcernCount() + "关注");
//                    onlyViewHolder.tv_item_topic_only_name_two.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(1).getName());
//                    onlyViewHolder.tv_item_topic_only_concern_count_two.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(1).getConcernCount() + "关注");
//                    onlyViewHolder.tv_item_topic_only_name_three.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(2).getName());
//                    onlyViewHolder.tv_item_topic_only_concern_count_three.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(2).getConcernCount() + "关注");
//                } else if (datas.get(0).getData().getRecomendExpert().getPosition() == 4) {
//                    Picasso.with(context).load(datas.get(0).getData().getRecomendExpert().getExpertList().get(0).getHeadpicurl()).into(onlyOtherViewHolder.iv_item_topic_only_one_other);
//                    Picasso.with(context).load(datas.get(0).getData().getRecomendExpert().getExpertList().get(1).getHeadpicurl()).into(onlyOtherViewHolder.iv_item_topic_only_two_other);
//                    Picasso.with(context).load(datas.get(0).getData().getRecomendExpert().getExpertList().get(2).getHeadpicurl()).into(onlyOtherViewHolder.iv_item_topic_only_three_other);
//                    Picasso.with(context).load(datas.get(0).getData().getRecomendExpert().getExpertList().get(3).getHeadpicurl()).into(onlyOtherViewHolder.iv_item_topic_only_four_other);
//                    onlyOtherViewHolder.tv_item_topic_only_name_one_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(0).getName());
//                    onlyOtherViewHolder.tv_item_topic_only_concern_count_one_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(0).getConcernCount() + "关注");
//                    onlyOtherViewHolder.tv_item_topic_only_name_two_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(1).getName());
//                    onlyOtherViewHolder.tv_item_topic_only_concern_count_two_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(1).getConcernCount() + "关注");
//                    onlyOtherViewHolder.tv_item_topic_only_name_three_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(2).getName());
//                    onlyOtherViewHolder.tv_item_topic_only_concern_count_three_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(2).getConcernCount() + "关注");
//                    onlyOtherViewHolder.tv_item_topic_only_name_four_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(3).getName());
//                    onlyOtherViewHolder.tv_item_topic_only_concern_count_four_other.setText(datas.get(0).getData().getRecomendExpert().getExpertList().get(3).getConcernCount() + "关注");
//                }
//                break;
        }
        return convertView;
    }

    //没有图片的行布局
    class FirstViewHolder {
        private TextView tv_item_topic_first_name, tv_item_topic_first_first, tv_item_topic_first_second, tv_item_topic_first_classification, tv_item_first_concerncount, tv_item_first_talkcount;
        private CircleImageView iv_item_topic_first_first, iv_item_topic_first_second;

        public FirstViewHolder(View view) {
            tv_item_topic_first_name = (TextView) view.findViewById(R.id.tv_item_topic_first_name);
            tv_item_topic_first_first = (TextView) view.findViewById(R.id.tv_item_topic_first_first);
            tv_item_topic_first_second = (TextView) view.findViewById(R.id.tv_item_topic_first_second);
            tv_item_topic_first_classification = (TextView) view.findViewById(R.id.tv_item_topic_first_classification);
            tv_item_first_concerncount = (TextView) view.findViewById(R.id.tv_item_first_concerncount);
            tv_item_first_talkcount = (TextView) view.findViewById(R.id.tv_item_first_talkcount);
            iv_item_topic_first_first = (CircleImageView) view.findViewById(R.id.iv_item_topic_first_first);
            iv_item_topic_first_second = (CircleImageView) view.findViewById(R.id.iv_item_topic_first_second);
        }
    }

    //唯一的行布局(三张图)
    class OnlyViewHolder {
        private TextView tv_item_topic_only_name_one, tv_item_topic_only_concern_count_one, tv_item_topic_only_name_two,
                tv_item_topic_only_concern_count_two, tv_item_topic_only_name_three, tv_item_topic_only_concern_count_three;
        private ImageView iv_item_topic_only_one, iv_item_topic_only_two, iv_item_topic_only_three;

        public OnlyViewHolder(View view) {
            tv_item_topic_only_name_one = (TextView) view.findViewById(R.id.tv_item_topic_only_name_one);
            tv_item_topic_only_concern_count_one = (TextView) view.findViewById(R.id.tv_item_topic_only_concern_count_one);
            tv_item_topic_only_name_two = (TextView) view.findViewById(R.id.tv_item_topic_only_name_two);
            tv_item_topic_only_concern_count_two = (TextView) view.findViewById(R.id.tv_item_topic_only_concern_count_two);
            tv_item_topic_only_name_three = (TextView) view.findViewById(R.id.tv_item_topic_only_name_three);
            tv_item_topic_only_concern_count_three = (TextView) view.findViewById(R.id.tv_item_topic_only_concern_count_three);
            iv_item_topic_only_one = (CircleImageView) view.findViewById(R.id.iv_item_topic_only_one);
            iv_item_topic_only_two = (CircleImageView) view.findViewById(R.id.iv_item_topic_only_two);
            iv_item_topic_only_three = (CircleImageView) view.findViewById(R.id.iv_item_topic_only_three);
        }
    }

    //唯一行布局(四张图)
    class OnlyOtherViewHolder {
        private TextView tv_item_topic_only_name_one_other, tv_item_topic_only_concern_count_one_other, tv_item_topic_only_name_two_other,
                tv_item_topic_only_concern_count_two_other, tv_item_topic_only_name_three_other, tv_item_topic_only_concern_count_three_other,
                tv_item_topic_only_name_four_other, tv_item_topic_only_concern_count_four_other;
        private ImageView iv_item_topic_only_one_other, iv_item_topic_only_two_other, iv_item_topic_only_three_other, iv_item_topic_only_four_other;

        public OnlyOtherViewHolder(View view) {
            tv_item_topic_only_name_one_other = (TextView) view.findViewById(R.id.tv_item_topic_only_name_one_other);
            tv_item_topic_only_concern_count_one_other = (TextView) view.findViewById(R.id.tv_item_topic_only_concern_count_one_other);
            tv_item_topic_only_name_two_other = (TextView) view.findViewById(R.id.tv_item_topic_only_name_two_other);
            tv_item_topic_only_concern_count_two_other = (TextView) view.findViewById(R.id.tv_item_topic_only_concern_count_two_other);
            tv_item_topic_only_name_three_other = (TextView) view.findViewById(R.id.tv_item_topic_only_name_three_other);
            tv_item_topic_only_concern_count_three_other = (TextView) view.findViewById(R.id.tv_item_topic_only_concern_count_three_other);
            tv_item_topic_only_name_four_other = (TextView) view.findViewById(R.id.tv_item_topic_only_name_four_other);
            tv_item_topic_only_concern_count_four_other = (TextView) view.findViewById(R.id.tv_item_topic_only_concern_count_four_other);
            iv_item_topic_only_one_other = (CircleImageView) view.findViewById(R.id.iv_item_topic_only_one_other);
            iv_item_topic_only_two_other = (CircleImageView) view.findViewById(R.id.iv_item_topic_only_two_other);
            iv_item_topic_only_three_other = (CircleImageView) view.findViewById(R.id.iv_item_topic_only_three_other);
            iv_item_topic_only_four_other = (CircleImageView) view.findViewById(R.id.iv_item_topic_only_four_other);
        }
    }

    //三张图片
    class SecondViewHolder {
        private TextView tv_item_topic_second_name, tv_item_topic_second_classification, tv_item_topic_second_concern_count,
                tv_item_topic_second_talk_count;
        private ImageView iv_item_topic_second_one, iv_item_topic_second_two, iv_item_topic_second_three;

        public SecondViewHolder(View view) {
            tv_item_topic_second_name = (TextView) view.findViewById(R.id.tv_item_topic_second_name);
            tv_item_topic_second_classification = (TextView) view.findViewById(R.id.tv_item_topic_second_classification);
            tv_item_topic_second_concern_count = (TextView) view.findViewById(R.id.tv_item_topic_second_concern_count);
            tv_item_topic_second_talk_count = (TextView) view.findViewById(R.id.tv_item_topic_second_talk_count);
            iv_item_topic_second_one = (ImageView) view.findViewById(R.id.iv_item_topic_second_one);
            iv_item_topic_second_two = (ImageView) view.findViewById(R.id.iv_item_topic_second_two);
            iv_item_topic_second_three = (ImageView) view.findViewById(R.id.iv_item_topic_second_three);
        }
    }
}
