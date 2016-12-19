package com.hsy.hc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsy.hc.R;
import com.hsy.hc.entity.ServiceData;

import java.util.List;

/**
 * 作者:liupeng
 * 16/9/27 16:51
 * 注释: 消息分组adapter
 */
public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public MessageAdapter(Context context, List<ServiceData> data) {
        this.mContext = context;
        this.list = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_message, null);
            viewHolder.item_message_img = (ImageView) convertView.findViewById(R.id.item_message_img);
            viewHolder.item_message_title = (TextView) convertView.findViewById(R.id.item_message_title);
            viewHolder.item_message_detail = (TextView) convertView.findViewById(R.id.item_message_detail);
            viewHolder.item_message_time = (TextView) convertView.findViewById(R.id.item_message_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_message_title.setText(list.get(position).getName());
        viewHolder.item_message_detail.setText(list.get(position).getContent());
        viewHolder.item_message_time.setText(list.get(position).getText1());
        return convertView;
    }

    class ViewHolder {

        ImageView item_message_img;
        TextView item_message_title;
        TextView item_message_detail;
        TextView item_message_time;

    }
}
