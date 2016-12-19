package com.hsy.hc.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;
import com.hsy.hc.adapter.VouchersAdapter;
import com.hsy.hc.entity.ServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/21 15:24
 * 注释: 代金券
 */
public class VouchersActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.vouchers_list)
    ListView vouchersList;
    @BindView(R.id.exchange_layout)
    RelativeLayout exchangeLayout;

    private Context context;
    private VouchersAdapter vouchersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vouchers);
        context = VouchersActivity.this;
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        titleText.setText("代金券");

        vouchersAdapter = new VouchersAdapter(context, list1());
        vouchersList.setAdapter(vouchersAdapter);

    }

    private List<ServiceData> list1() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("¥200");
            serviceData.setContent("代金券200元");
            serviceData.setText1("2016-01-01至2017-01-01");
            list.add(serviceData);
        }
        return list;
    }
}
