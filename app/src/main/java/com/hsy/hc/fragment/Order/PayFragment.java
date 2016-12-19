package com.hsy.hc.fragment.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.adapter.OrderAuditAdapter;
import com.hsy.hc.entity.ServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/18 14:27
 * 注释: 待支付
 */
public class PayFragment extends BaseFragment {


    @BindView(R.id.order_pay_lv)
    ListView orderPayLv;

    private View view;
    private OrderAuditAdapter orderAuditAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pay, container, false);


        ButterKnife.bind(this, view);
        initView();
        Log.e("onCreateView--pay", "onCreateView--pay");
        return view;
    }

    private void initView() {

        orderAuditAdapter = new OrderAuditAdapter(getActivity(), list2());
        orderPayLv.setAdapter(orderAuditAdapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.e("onResume--pay--visi", "onResume--pay");
        } else {
            Log.e("onPause--pay--visi", "onPause--pay");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Log.e("onPause--pay--hidd", "onPause--pay");
        } else {
            Log.e("onResume--pay--hidd", "onResume--pay");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume--pay", "onResume--pay");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause--pay", "onPause--pay");
    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("Windows系统安装服务");
            serviceData.setContent("Windows系统安装服务");
            serviceData.setText1("x10");
            serviceData.setText2("¥99.9");
            serviceData.setText3("¥999.0");
            list.add(serviceData);
        }
        return list;
    }
}
