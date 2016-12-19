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
 * 16/9/18 14:26
 * 注释: 待审核
 */
public class AuditFragment extends BaseFragment {

    @BindView(R.id.order_audit_lv)
    ListView orderAuditLv;

    private View view;
    private OrderAuditAdapter orderAuditAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_audit, container, false);
        ButterKnife.bind(this, view);
        initView();

        Log.e("onCreateView--audit", "onCreateView--audit");

        return view;
    }

    private void initView() {

        orderAuditAdapter = new OrderAuditAdapter(getActivity(), list2());
        orderAuditLv.setAdapter(orderAuditAdapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.e("onResume--audit--visi", "onResume--audit");
        } else {
            Log.e("onPause--audit--visi", "onPause--audit");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Log.e("onPause--audit--hidd", "onPause--audit");
        } else {
            Log.e("onResume--audit--hidd", "onResume--audit");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume--audit", "onResume--audit");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause--audit", "onPause--audit");
    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("包月限次服务");
            serviceData.setContent("设备类型 响应时间 服务时间");
            serviceData.setText1("x3");
            serviceData.setText2("¥1998.9");
            serviceData.setText3("¥4999.9");
            list.add(serviceData);
        }
        return list;
    }
}
