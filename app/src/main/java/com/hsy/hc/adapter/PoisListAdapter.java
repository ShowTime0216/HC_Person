package com.hsy.hc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;

/**
 * Created by hsy on 16/9/6.
 */
public class PoisListAdapter extends Adapter {

    private Context context;
    private LayoutInflater inflater;

    public PoisListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PoiItem poiItem = (PoiItem) getItem(position);
        holder.pois_name.setText(poiItem.getTitle());
        holder.pois_address.setText(poiItem.getSnippet());

        return convertView;
    }

    private class ViewHolder {
        private TextView pois_name;
        private TextView pois_address;
    }
}
