package com.hsy.hc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.adapter.LikeGridViewAdapter;
import com.hsy.hc.adapter.ShoppingAdapter;
import com.hsy.hc.entity.ServiceData;
import com.hsy.hc.view.MyGridView;
import com.hsy.hc.view.MyListView_Sl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/14 11:03
 * 注释: 购物车
 */
public class ShoppingFragment extends BaseFragment {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.shopping_lv)
    MyListView_Sl shoppingLv;
    @BindView(R.id.shopping_gv)
    MyGridView shoppingGv;

    private View view;
    private ShoppingAdapter shoppingAdapter;
    private LikeGridViewAdapter likeGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, container, false);

        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {

        titleText.setText("购物车");
        textRight.setText("编辑");
        textRight.setVisibility(View.VISIBLE);

        shoppingAdapter = new ShoppingAdapter(getActivity(), list1());
        shoppingLv.setAdapter(shoppingAdapter);

        likeGridViewAdapter = new LikeGridViewAdapter(getActivity(), list2());
        shoppingGv.setAdapter(likeGridViewAdapter);

    }


    private List<ServiceData> list1() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("联想Lenovo G480 磨砂黑");
            serviceData.setContent("这款笔记本太笨重了，噪音很大");
            serviceData.setText1("满100000才能使用优惠券");
            serviceData.setText2("3");
            serviceData.setText3("¥1499.0");
            list.add(serviceData);
        }
        return list;
    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("应用软件");
            serviceData.setContent("常用软件安装、病毒查杀、系统安装");
            serviceData.setText1("满100000才能使用优惠券");
            serviceData.setText2("¥200.0-1000.0");
            serviceData.setText3("¥500.0");
            serviceData.setText4("下单量999");
            serviceData.setText5("99%好评");
            list.add(serviceData);
        }
        return list;
    }
}
