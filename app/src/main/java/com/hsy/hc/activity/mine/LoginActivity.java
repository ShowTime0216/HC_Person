package com.hsy.hc.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:liupeng
 * 16/9/14 11:03
 * 注释: 登录
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.text_right)
    TextView textRight;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_btn)
    TextView loginBtn;
    @BindView(R.id.back_password)
    TextView backPassword;
    @BindView(R.id.login_wechat)
    ImageView loginWechat;
    @BindView(R.id.login_qq)
    ImageView loginQq;
    @BindView(R.id.login_sina)
    ImageView loginSina;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        titleText.setText("登录");
        textRight.setText("注册");
        textRight.setVisibility(View.VISIBLE);

    }


    @OnClick({R.id.text_right, R.id.login_btn, R.id.back_password, R.id.login_wechat, R.id.login_qq, R.id.login_sina})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.text_right: // 注册
                intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn: // 登录
                intent = new Intent(context, PerfectInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.back_password: // 找回密码
                intent = new Intent(context, BackPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.login_wechat: // 微信登录
                break;
            case R.id.login_qq: // QQ登录
                break;
            case R.id.login_sina: // 新浪微博登录
                break;
        }
    }
}
