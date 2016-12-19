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
 * 16/9/20 10:07
 * 注释: 为你推荐、猜你喜欢GridView的adapter
 */
public class LikeGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ServiceData> list;
    private LayoutInflater layoutInflater;

    public LikeGridViewAdapter(Context context, List<ServiceData> data) {
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
            convertView = layoutInflater.inflate(R.layout.item_like_gv, null);
            viewHolder.item_like_gv_img = (ImageView) convertView.findViewById(R.id.item_like_gv_img);
            viewHolder.item_like_gv_name = (TextView) convertView.findViewById(R.id.item_like_gv_name);
            viewHolder.item_like_gv_preferential = (TextView) convertView.findViewById(R.id.item_like_gv_preferential);
            viewHolder.item_like_gv_detail = (TextView) convertView.findViewById(R.id.item_like_gv_detail);
            viewHolder.item_like_gv_price = (TextView) convertView.findViewById(R.id.item_like_gv_price);
            viewHolder.item_like_gv_priced = (TextView) convertView.findViewById(R.id.item_like_gv_priced);
            viewHolder.item_like_gv_number = (TextView) convertView.findViewById(R.id.item_like_gv_number);
            viewHolder.item_like_gv_praise = (TextView) convertView.findViewById(R.id.item_like_gv_praise);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_like_gv_name.setText(list.get(position).getName());
        viewHolder.item_like_gv_preferential.setText(list.get(position).getText1());
        viewHolder.item_like_gv_detail.setText(list.get(position).getContent());
        viewHolder.item_like_gv_price.setText(list.get(position).getText2());
        viewHolder.item_like_gv_priced.setText("原价: " + list.get(position).getText3());
        viewHolder.item_like_gv_priced.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.item_like_gv_number.setText(list.get(position).getText4());
        viewHolder.item_like_gv_praise.setText(list.get(position).getText5());
        return convertView;
    }

    class ViewHolder {
        ImageView item_like_gv_img; // 图像
        TextView item_like_gv_name; // 商品名称
        TextView item_like_gv_preferential; // 优惠活动
        TextView item_like_gv_detail; // 商品介绍
        TextView item_like_gv_price; // 价格
        TextView item_like_gv_priced; // 原价
        TextView item_like_gv_number; // 下单量
        TextView item_like_gv_praise; // 好评率
    }
}
