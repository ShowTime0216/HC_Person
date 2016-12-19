package com.hsy.hc.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:liupeng
 * 16/9/21 10:37
 * 注释: 设置
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.setting_help)
    RelativeLayout settingHelp;
    @BindView(R.id.setting_opinion)
    RelativeLayout settingOpinion;
    @BindView(R.id.setting_about)
    RelativeLayout settingAbout;
    @BindView(R.id.logout_layout)
    LinearLayout logoutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        titleText.setText("设置");

    }

    @OnClick({R.id.setting_help, R.id.setting_opinion, R.id.setting_about, R.id.logout_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_help:
                break;
            case R.id.setting_opinion:
                break;
            case R.id.setting_about:
                break;
            case R.id.logout_layout:
                break;
        }
    }
}
