package com.hsy.hc.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;
import com.hsy.hc.adapter.CollectionAdapter;
import com.hsy.hc.entity.ServiceData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者:liupeng
 * 16/9/21 10:37
 * 注释: 我的收藏
 */
public class CollectionActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.collection_lv)
    ListView collectionLv;

    private Context context;
    private CollectionAdapter collectionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        context = CollectionActivity.this;
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        titleText.setText("我的收藏");

        collectionAdapter = new CollectionAdapter(context, list2());
        collectionLv.setAdapter(collectionAdapter);

    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("拆了重装");
            serviceData.setContent("原价:¥1500.00");
            serviceData.setText1("家庭网路的配置服务、拆机清灰、更换键盘");
            serviceData.setText2("¥1200.0-¥1300.0");
            serviceData.setText3("下单量999");
            serviceData.setText4("99%好评");
            serviceData.setText5("满100000元才能使用优惠券");
            list.add(serviceData);
        }
        return list;
    }
}
