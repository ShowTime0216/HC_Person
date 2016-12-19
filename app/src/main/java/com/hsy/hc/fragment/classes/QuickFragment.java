package com.hsy.hc.fragment.classes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.adapter.QuickGridAdapter;
import com.hsy.hc.entity.ServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/21 16:23
 * 注释: 快捷报修
 */
public class QuickFragment extends BaseFragment {

    @BindView(R.id.quick_gv)
    GridView quickGv;

    private View view;
    private QuickGridAdapter quickGridAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quick, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {

        quickGridAdapter = new QuickGridAdapter(getActivity(), list1());
        quickGv.setAdapter(quickGridAdapter);
    }

    private List<ServiceData> list1() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("Mac系统重装");
            serviceData.setText1("¥200.0");
            serviceData.setText2("原价:¥500.00");
            serviceData.setText3("下单量500");
            serviceData.setText4("98%好评");
            list.add(serviceData);
        }
        return list;
    }
}
