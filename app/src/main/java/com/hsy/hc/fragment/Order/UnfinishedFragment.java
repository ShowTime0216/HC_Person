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
 * 注释: 未完成
 */
public class UnfinishedFragment extends BaseFragment {


    @BindView(R.id.order_unFinish_lv)
    ListView orderUnFinishLv;


    private View view;
    private OrderAuditAdapter orderAuditAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_unfinish, container, false);


        ButterKnife.bind(this, view);
        initView();
        Log.e("onCreateView--un", "onCreateView--un");
        return view;
    }

    private void initView() {

        orderAuditAdapter = new OrderAuditAdapter(getActivity(), list2());
        orderUnFinishLv.setAdapter(orderAuditAdapter);

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.e("onResume--un--visi", "onResume--un");
        } else {
            Log.e("onPause--un--visi", "onPause--un");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Log.e("onPause--un--hidd", "onPause--un");
        } else {
            Log.e("onResume--un--hidd", "onResume--un");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume--un", "onResume--un");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause--un", "onPause--un");
    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("病毒查杀");
            serviceData.setContent("病毒查杀");
            serviceData.setText1("x2");
            serviceData.setText2("¥98.9");
            serviceData.setText3("¥199.9");
            list.add(serviceData);
        }
        return list;
    }
}
