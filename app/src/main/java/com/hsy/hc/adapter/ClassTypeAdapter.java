package com.hsy.hc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hsy.hc.R;
import com.hsy.hc.entity.ServiceData;

import java.util.List;

/**
 * 作者:liupeng
 * 16/9/18 10:58
 * 注释: 分类左侧类型列表
 */
public class ClassTypeAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public ClassTypeAdapter(Context context, List<ServiceData> data) {
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
            convertView = inflater.inflate(R.layout.item_class_type, null);
            viewHolder.item_type_title = (TextView) convertView.findViewById(R.id.item_type_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_type_title.setText(list.get(position).getName());

        return convertView;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    private int selectItem = -1;

    class ViewHolder {
        TextView item_type_title;

    }
}
