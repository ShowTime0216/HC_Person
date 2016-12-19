package com.hsy.hc.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;
import com.hsy.hc.adapter.LikeGridViewAdapter;
import com.hsy.hc.entity.ServiceData;
import com.hsy.hc.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/28 10:07
 * 注释: 本月推荐列表
 */
public class RecommendListActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.recommend_gv)
    MyGridView recommendGv;

    private Context context;
    private LikeGridViewAdapter likeGridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list);
        context = RecommendListActivity.this;
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        titleText.setText("本月推荐");

        likeGridViewAdapter = new LikeGridViewAdapter(context, list2());
        recommendGv.setAdapter(likeGridViewAdapter);

    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("应用软件");
            serviceData.setContent("常用软件安装、病毒查杀、系统安装");
            serviceData.setText1("满100000才能使用优惠券");
            serviceData.setText2("¥200.0-1000.0");
            serviceData.setText3("¥500.0");
            serviceData.setText4("下单量999");
            serviceData.setText5("99%好评");
            list.add(serviceData);
        }
        return list;
    }
}
