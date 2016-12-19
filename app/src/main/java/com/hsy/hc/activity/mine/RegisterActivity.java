package com.hsy.hc.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:liupeng
 * 16/9/22 15:33
 * 注释: 注册页面
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.codepassword)
    EditText codepassword;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.agree)
    CheckBox agree;
    @BindView(R.id.protocol)
    TextView protocol;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.text_left)
    TextView textLeft;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        titleText.setText("注册");
        textLeft.setText("取消");
        textLeft.setVisibility(View.VISIBLE);

    }


    @OnClick({R.id.code, R.id.protocol, R.id.register, R.id.text_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.code:
                break;
            case R.id.protocol:
                break;
            case R.id.register:
                break;
            case R.id.text_left:
                finish();
                break;
        }
    }

}
