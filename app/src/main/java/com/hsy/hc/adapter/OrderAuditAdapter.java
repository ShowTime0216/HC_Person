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
 * 16/9/18 18:15
 * 注释: 订单页面的adapter
 */
public class OrderAuditAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public OrderAuditAdapter(Context context, List<ServiceData> data) {
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
            convertView = inflater.inflate(R.layout.item_order_audit, null);
            viewHolder.order_audit_item_title = (TextView) convertView.findViewById(R.id.order_audit_item_title);
            viewHolder.order_audit_item_details = (TextView) convertView.findViewById(R.id.order_audit_item_details);
            viewHolder.order_audit_item_number = (TextView) convertView.findViewById(R.id.order_audit_item_number);
            viewHolder.order_audit_item_price = (TextView) convertView.findViewById(R.id.order_audit_item_price);
            viewHolder.order_audit_item_prices = (TextView) convertView.findViewById(R.id.order_audit_item_prices);

            viewHolder.order_audit_item_type = (TextView) convertView.findViewById(R.id.order_audit_item_type);
            viewHolder.order_audit_item_preferential = (TextView) convertView.findViewById(R.id.order_audit_item_preferential);
            viewHolder.order_audit_item_btn_left = (TextView) convertView.findViewById(R.id.order_audit_item_btn_left);
            viewHolder.order_audit_item_btn_centre = (TextView) convertView.findViewById(R.id.order_audit_item_btn_centre);
            viewHolder.order_audit_item_btn_right = (TextView) convertView.findViewById(R.id.order_audit_item_btn_right);
            viewHolder.order_audit_item_order_number = (TextView) convertView.findViewById(R.id.order_audit_item_order_number);
            viewHolder.order_audit_item_img = (ImageView) convertView.findViewById(R.id.order_audit_item_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.order_audit_item_title.setText(list.get(position).getName());
        viewHolder.order_audit_item_details.setText(list.get(position).getContent());
        viewHolder.order_audit_item_number.setText(list.get(position).getText1());
        viewHolder.order_audit_item_price.setText(list.get(position).getText2());
        viewHolder.order_audit_item_prices.setText(list.get(position).getText3());
        viewHolder.order_audit_item_preferential.setVisibility(View.VISIBLE);
        viewHolder.order_audit_item_preferential.setText("满10000才能使用优惠券");
        viewHolder.order_audit_item_btn_centre.setText("取消订单");
        viewHolder.order_audit_item_btn_right.setText("立即支付");

        return convertView;
    }

    class ViewHolder {
        TextView order_audit_item_order_number; // 订单号
        TextView order_audit_item_title; // 订单名称
        TextView order_audit_item_details; // 商品详细
        TextView order_audit_item_number; // 订单数量
        TextView order_audit_item_price; // 订单单价
        TextView order_audit_item_prices; // 订单总价（合计）
        TextView order_audit_item_type; // 订单类型
        TextView order_audit_item_preferential; // 优惠
        TextView order_audit_item_btn_left; // 订单操作按钮（左边）
        TextView order_audit_item_btn_centre; // 订单操作按钮（中间）
        TextView order_audit_item_btn_right; // 订单操作按钮（右边）
        ImageView order_audit_item_img; // 图片

    }
}
