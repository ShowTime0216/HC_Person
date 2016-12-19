package com.hsy.hc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.hc.R;
import com.hsy.hc.entity.ServiceData;

import java.util.List;

/**
 * 作者:liupeng
 * 16/9/20 11:59
 * 注释: 购物车adapter
 */
public class ShoppingAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public ShoppingAdapter(Context context, List<ServiceData> data) {
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
            convertView = inflater.inflate(R.layout.item_shopping, null);
            viewHolder.item_shopping_box = (CheckBox) convertView.findViewById(R.id.item_shopping_box);
            viewHolder.item_shopping_img = (ImageView) convertView.findViewById(R.id.item_shopping_img);
            viewHolder.item_shopping_btn_min = (LinearLayout) convertView.findViewById(R.id.item_shopping_btn_min);
            viewHolder.item_shopping_btn_max = (LinearLayout) convertView.findViewById(R.id.item_shopping_btn_max);
            viewHolder.item_shopping_name = (TextView) convertView.findViewById(R.id.item_shopping_name);
            viewHolder.item_shopping_details = (TextView) convertView.findViewById(R.id.item_shopping_details);
            viewHolder.item_shopping_preferential = (TextView) convertView.findViewById(R.id.item_shopping_preferential);
            viewHolder.item_shopping_number = (TextView) convertView.findViewById(R.id.item_shopping_number);
            viewHolder.item_shopping_price = (TextView) convertView.findViewById(R.id.item_shopping_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_shopping_name.setText(list.get(position).getName());
        viewHolder.item_shopping_details.setText(list.get(position).getContent());

        viewHolder.item_shopping_preferential.setText(list.get(position).getText1());
        viewHolder.item_shopping_number.setText(list.get(position).getText2());
        viewHolder.item_shopping_price.setText(list.get(position).getText3());

        return convertView;
    }

    class ViewHolder {
        CheckBox item_shopping_box; // 是否选中item
        ImageView item_shopping_img; // 商品图像
        TextView item_shopping_name; // 商品名称
        TextView item_shopping_details; // 商品详细
        TextView item_shopping_preferential; // 优惠信息
        LinearLayout item_shopping_btn_min; // 减少
        LinearLayout item_shopping_btn_max; // 增加
        TextView item_shopping_number; // 商品数量
        TextView item_shopping_price; // 商品价格

    }
}
