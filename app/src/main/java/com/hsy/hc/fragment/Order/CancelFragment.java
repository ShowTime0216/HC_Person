package com.hsy.hc.fragment.Order;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * 16/9/18 14:25
 * 注释: 已取消
 */
public class CancelFragment extends BaseFragment {


    @BindView(R.id.order_cancel_lv)
    ListView orderCancelLv;


    private View view;
    private OrderAuditAdapter orderAuditAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cancel, container, false);

        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {

        orderAuditAdapter = new OrderAuditAdapter(getActivity(), list2());
        orderCancelLv.setAdapter(orderAuditAdapter);

    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("网络配置服务");
            serviceData.setContent("家庭网络配置服务");
            serviceData.setText1("x2");
            serviceData.setText2("¥99.9");
            serviceData.setText3("¥198.8");
            list.add(serviceData);
        }
        return list;
    }
}
