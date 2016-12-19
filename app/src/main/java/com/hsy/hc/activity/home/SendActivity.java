package com.hsy.hc.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;
import com.hsy.hc.adapter.HomeOrderAdapter;
import com.hsy.hc.entity.ServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/27 14:47
 * 注释: 首页的保内送修
 */
public class SendActivity extends BaseActivity {

    @BindView(R.id.home_send_list)
    ListView homeSendList;


    private Context context;
    private HomeOrderAdapter homeOrderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        context = SendActivity.this;
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        homeOrderAdapter = new HomeOrderAdapter(context, list1());
        homeSendList.setAdapter(homeOrderAdapter);

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
            serviceData.setText6("满10000元才能使用优惠券");
            list.add(serviceData);
        }
        return list;
    }
}
