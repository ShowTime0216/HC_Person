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
 * 16/9/27 15:24
 * 注释: 首页分类订单adapter
 */
public class HomeOrderAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public HomeOrderAdapter(Context context, List<ServiceData> data) {
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
            convertView = inflater.inflate(R.layout.item_home_order, null);
            viewHolder.item_home_order_img = (ImageView) convertView.findViewById(R.id.item_home_order_img);
            viewHolder.item_home_order_card = (ImageView) convertView.findViewById(R.id.item_home_order_card);
            viewHolder.item_home_order_name = (TextView) convertView.findViewById(R.id.item_home_order_name);
            viewHolder.item_home_order_preferential = (TextView) convertView.findViewById(R.id.item_home_order_preferential);
            viewHolder.item_home_order_detail = (TextView) convertView.findViewById(R.id.item_home_order_detail);
            viewHolder.item_home_order_price = (TextView) convertView.findViewById(R.id.item_home_order_price);
            viewHolder.item_home_order_priced = (TextView) convertView.findViewById(R.id.item_home_order_priced);
            viewHolder.item_home_order_number = (TextView) convertView.findViewById(R.id.item_home_order_number);
            viewHolder.item_home_order_inventory = (TextView) convertView.findViewById(R.id.item_home_order_inventory);
            viewHolder.item_home_order_praise = (TextView) convertView.findViewById(R.id.item_home_order_praise);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_home_order_name.setText(list.get(position).getName());
        viewHolder.item_home_order_preferential.setText(list.get(position).getText6());
        viewHolder.item_home_order_detail.setText(list.get(position).getText1());
        viewHolder.item_home_order_price.setText(list.get(position).getText2());
        viewHolder.item_home_order_priced.setText(list.get(position).getContent());
        viewHolder.item_home_order_priced.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.item_home_order_number.setText(list.get(position).getText3());
        viewHolder.item_home_order_inventory.setText(list.get(position).getText4());
        viewHolder.item_home_order_praise.setText(list.get(position).getText5());

        return convertView;
    }

    class ViewHolder {
        ImageView item_home_order_img; // 商品图像
        ImageView item_home_order_card; // 购物车
        TextView item_home_order_name; // 商品名称
        TextView item_home_order_preferential; // 优惠信息
        TextView item_home_order_detail; // 商品详细
        TextView item_home_order_price; // 现价
        TextView item_home_order_priced; // 原价
        TextView item_home_order_number; // 下单量
        TextView item_home_order_inventory; // 库存
        TextView item_home_order_praise; // 好评率

    }
}
