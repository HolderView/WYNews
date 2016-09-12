package com.example.dllo.wynews.ui.adapter.classification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.wynews.R;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 */
public class ClassificationListViewAdapter extends BaseAdapter {
    private List<String> datas;
    private Context context;

    public ClassificationListViewAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_classification,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(datas.get(position));
        return convertView;
    }
    class ViewHolder {
        private TextView textView;
        public ViewHolder(View view){
            textView = (TextView) view.findViewById(R.id.tv_class);
        }
    }
}
