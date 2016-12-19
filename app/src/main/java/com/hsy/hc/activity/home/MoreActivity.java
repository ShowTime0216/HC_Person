package com.hsy.hc.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:liupeng
 * 16/9/27 11:34
 * 注释: 更多页面
 */
public class MoreActivity extends BaseActivity {

    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.more_recycling_layout)
    RelativeLayout moreRecyclingLayout;
    @BindView(R.id.more_send_layout)
    RelativeLayout moreSendLayout;

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        context = MoreActivity.this;
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        titleText.setText("更多");


    }

    @OnClick({R.id.more_recycling_layout, R.id.more_send_layout})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.more_recycling_layout: // 闲置回收
                break;
            case R.id.more_send_layout: // 保内送修
                intent = new Intent(context, SendActivity.class);
                startActivity(intent);
                break;
        }
    }
}
