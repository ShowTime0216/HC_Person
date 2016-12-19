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
 * 16/9/21 10:42
 * 注释: 我的收藏adapter
 */
public class CollectionAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public CollectionAdapter(Context context, List<ServiceData> data) {
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
            convertView = inflater.inflate(R.layout.item_collection, null);
            viewHolder.item_collection_img = (ImageView) convertView.findViewById(R.id.item_collection_img);
            viewHolder.item_collection_title = (TextView) convertView.findViewById(R.id.item_collection_title);
            viewHolder.item_collection_introduce = (TextView) convertView.findViewById(R.id.item_collection_introduce);
            viewHolder.item_collection_price = (TextView) convertView.findViewById(R.id.item_collection_price);
            viewHolder.item_collection_priced = (TextView) convertView.findViewById(R.id.item_collection_priced);
            viewHolder.item_collection_number = (TextView) convertView.findViewById(R.id.item_collection_number);
            viewHolder.item_collection_praise = (TextView) convertView.findViewById(R.id.item_collection_praise);
            viewHolder.item_collection_preferential = (TextView) convertView.findViewById(R.id.item_collection_preferential);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_collection_title.setText(list.get(position).getName());
        viewHolder.item_collection_introduce.setText(list.get(position).getText1());
        viewHolder.item_collection_price.setText(list.get(position).getText2());
        viewHolder.item_collection_priced.setText(list.get(position).getContent());
        viewHolder.item_collection_priced.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.item_collection_number.setText(list.get(position).getText3());
        viewHolder.item_collection_praise.setText(list.get(position).getText4());
        viewHolder.item_collection_preferential.setText(list.get(position).getText5());

        return convertView;
    }

    class ViewHolder {
        ImageView item_collection_img; // 图像
        TextView item_collection_title; // 商品名称
        TextView item_collection_introduce; // 商品介绍
        TextView item_collection_price; // 价格
        TextView item_collection_priced; // 原价
        TextView item_collection_number; // 下单量
        TextView item_collection_praise; // 好评
        TextView item_collection_preferential; // 优惠信息

    }
}
