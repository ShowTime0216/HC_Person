package com.hsy.hc.adapter;

import android.content.Context;
import android.graphics.Paint;
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
 * 16/9/14 12:10
 * 注释: 快捷报修GirdView适配器
 */
public class QuickGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<ServiceData> list;
    private LayoutInflater layoutInflater;

    public QuickGridAdapter(Context context, List<ServiceData> data) {
        this.mContext = context;
        this.list = data;
        layoutInflater = LayoutInflater.from(mContext);
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
            convertView = layoutInflater.inflate(R.layout.item_home_gv, null);
            viewHolder.item_quick_img = (ImageView) convertView.findViewById(R.id.item_quick_img);
            viewHolder.item_quick_name = (TextView) convertView.findViewById(R.id.item_quick_name);
            viewHolder.item_quick_price = (TextView) convertView.findViewById(R.id.item_quick_price);
            viewHolder.item_quick_priced = (TextView) convertView.findViewById(R.id.item_quick_priced);
            viewHolder.item_quick_number = (TextView) convertView.findViewById(R.id.item_quick_number);
            viewHolder.item_quick_praise = (TextView) convertView.findViewById(R.id.item_quick_praise);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_quick_name.setText(list.get(position).getName());
        viewHolder.item_quick_price.setText(list.get(position).getText1());
        viewHolder.item_quick_priced.setText(list.get(position).getText2());
        viewHolder.item_quick_priced.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.item_quick_number.setText(list.get(position).getText3());
        viewHolder.item_quick_praise.setText(list.get(position).getText4());

        return convertView;
    }

    class ViewHolder {
        ImageView item_quick_img; // 商品图像
        TextView item_quick_name; // 商品名称
        TextView item_quick_price; // 现价
        TextView item_quick_priced; // 原价
        TextView item_quick_number; // 下单量
        TextView item_quick_praise; // 好评量

    }
}
