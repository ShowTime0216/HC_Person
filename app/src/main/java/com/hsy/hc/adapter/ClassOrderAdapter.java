package com.hsy.hc.adapter;

import android.content.Context;
import android.graphics.Paint;
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
 * 16/9/14 16:51
 * 注释: 订单adapter
 */
public class ClassOrderAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public ClassOrderAdapter(Context context, List<ServiceData> data) {
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
            convertView = inflater.inflate(R.layout.item_order, null);
            viewHolder.item_order_title = (TextView) convertView.findViewById(R.id.item_order_title);
            viewHolder.item_order_introduce = (TextView) convertView.findViewById(R.id.item_order_introduce);
            viewHolder.item_order_price = (TextView) convertView.findViewById(R.id.item_order_price);
            viewHolder.item_order_priced = (TextView) convertView.findViewById(R.id.item_order_priced);
            viewHolder.item_order_number = (TextView) convertView.findViewById(R.id.item_order_number);
            viewHolder.item_order_praise = (TextView) convertView.findViewById(R.id.item_order_praise);
            viewHolder.item_order_inventory = (TextView) convertView.findViewById(R.id.item_order_inventory);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_order_title.setText(list.get(position).getName());
        viewHolder.item_order_introduce.setText(list.get(position).getText1());
        viewHolder.item_order_price.setText(list.get(position).getText2());
        viewHolder.item_order_priced.setText(list.get(position).getContent());
        viewHolder.item_order_priced.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.item_order_number.setText(list.get(position).getText3());
        viewHolder.item_order_praise.setText(list.get(position).getText4());
        viewHolder.item_order_inventory.setText(list.get(position).getText5());

        return convertView;
    }

    class ViewHolder {
        TextView item_order_title; // 商品名称
        TextView item_order_introduce; // 商品介绍
        TextView item_order_price; // 价格
        TextView item_order_priced; // 原价
        TextView item_order_number; // 下单量
        TextView item_order_praise; // 好评
        TextView item_order_inventory; // 库存

    }
}
