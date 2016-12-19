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
 * 16/9/18 14:26
 * 注释: 已完成
 */
public class FinishFragment extends BaseFragment {


    @BindView(R.id.order_finish_lv)
    ListView orderFinishLv;


    private View view;
    private OrderAuditAdapter orderAuditAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_finish, container, false);


        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    private void initView() {

        orderAuditAdapter = new OrderAuditAdapter(getActivity(), list2());
        orderFinishLv.setAdapter(orderAuditAdapter);

    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("Mac系统安装服务");
            serviceData.setContent("Mac系统安装服务");
            serviceData.setText1("x1");
            serviceData.setText2("¥99.0");
            serviceData.setText3("¥99.0");
            list.add(serviceData);
        }
        return list;
    }
}
