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
 * 16/9/21 15:33
 * 注释: 代金券列表
 */
public class VouchersAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public VouchersAdapter(Context context, List<ServiceData> data) {
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
            convertView = inflater.inflate(R.layout.item_vouchers, null);
            viewHolder.item_vouchers_price = (TextView) convertView.findViewById(R.id.item_vouchers_price);
            viewHolder.item_vouchers_name = (TextView) convertView.findViewById(R.id.item_vouchers_name);
            viewHolder.item_vouchers_time = (TextView) convertView.findViewById(R.id.item_vouchers_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_vouchers_price.setText(list.get(position).getName());
        viewHolder.item_vouchers_name.setText(list.get(position).getContent());
        viewHolder.item_vouchers_time.setText(list.get(position).getText1());

        return convertView;
    }

    class ViewHolder {
        TextView item_vouchers_price; // 代金券额度
        TextView item_vouchers_name; // 代金券名称
        TextView item_vouchers_time; // 使用期限

    }
}
