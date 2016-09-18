package com.example.dllo.wynews.ui.adapter.hotlive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.wynews.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 */
public class HotLiveAdapter extends BaseAdapter {
    private List<String> datas;
    private Context context;

    public HotLiveAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<String> datas) {
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
        viewHolder.textView.setText(datas.get(position));
        return convertView;
    }
    class ViewHolder{
        private TextView textView;
        public ViewHolder(View view){
            textView= (TextView) view.findViewById(R.id.tv_hot_view);
        }
    }
}
