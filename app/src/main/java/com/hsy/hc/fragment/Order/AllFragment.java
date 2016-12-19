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
 * 16/9/18 14:24
 * 注释:
 */
public class AllFragment extends BaseFragment {


    @BindView(R.id.order_all_lv)
    ListView orderAllLv;

    private View view;
    private OrderAuditAdapter orderAuditAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all, container, false);

        ButterKnife.bind(this, view);
        initView();
        Log.e("onCreateView--all", "onCreateView--all");
        return view;
    }

    private void initView() {

        orderAuditAdapter = new OrderAuditAdapter(getActivity(), list2());
        orderAllLv.setAdapter(orderAuditAdapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.e("onResume--all--visi", "onResume--all");
        } else {
            Log.e("onPause--all--visi", "onPause--all");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Log.e("onPause--all--hidd", "onPause--all");
        } else {
            Log.e("onResume--all--hidd", "onResume--all");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume--all", "onResume--all");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause--all", "onPause--all");
    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("乱七八糟的服务");
            serviceData.setContent("装系统啦、杀毒啦、拆电脑啦");
            serviceData.setText1("x10");
            serviceData.setText2("¥1998.9");
            serviceData.setText3("¥29999.9");
            list.add(serviceData);
        }
        return list;
    }
}
