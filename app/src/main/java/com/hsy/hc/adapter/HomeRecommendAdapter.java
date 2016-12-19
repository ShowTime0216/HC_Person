package com.hsy.hc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.hc.R;
import com.hsy.hc.entity.ServiceData;

import java.util.List;

/**
 * 作者:liupeng
 * 16/9/14 15:11
 * 注释: 首页本月推荐列表adapter
 */
public class HomeRecommendAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ServiceData> list;

    public HomeRecommendAdapter(Context context, List<ServiceData> data) {
        this.mContext = context;
        this.list = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_recommend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.item_recommend_tv.setText(list.get(position).getName());
        viewHolder.item_recommend_price.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_recommend_tv;
        TextView item_recommend_price;

        public ViewHolder(View itemView) {
            super(itemView);
            item_recommend_tv = (TextView) itemView.findViewById(R.id.item_recommend_tv);
            item_recommend_price = (TextView) itemView.findViewById(R.id.item_recommend_price);
        }
    }
}
