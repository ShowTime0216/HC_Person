package com.hsy.hc.fragment.classes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.adapter.ClassOrderAdapter;
import com.hsy.hc.entity.ServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/21 16:24
 * 注释: 保内送修
 */
public class SendFragment extends BaseFragment {

    @BindView(R.id.send_list)
    ListView sendList;

    private View view;
    private ClassOrderAdapter classOrderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_send, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        classOrderAdapter = new ClassOrderAdapter(getActivity(), list1());
        sendList.setAdapter(classOrderAdapter);

    }

    private List<ServiceData> list1() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("配件更换");
            serviceData.setContent("原价:¥500.00");
            serviceData.setText1("家庭网路的配置服务");
            serviceData.setText2("¥200.0-¥300.0");
            serviceData.setText3("下单量200");
            serviceData.setText4("99%好评");
            serviceData.setText5("库存10000");
            list.add(serviceData);
        }
        return list;
    }
}
