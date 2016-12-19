package com.hsy.hc.activity.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;
import com.hsy.hc.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:liupeng
 * 16/9/23 15:38
 * 注释: 用户信息
 */
public class UserInformationActivity extends BaseActivity {
    @BindView(R.id.user_img)
    CircleImageView userImg;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_integral)
    TextView userIntegral;
    @BindView(R.id.user_contract)
    TextView userContract;
    @BindView(R.id.user_number)
    TextView userNumber;
    @BindView(R.id.user_date)
    TextView userDate;
    @BindView(R.id.user_time)
    TextView userTime;
    @BindView(R.id.user_contract_details)
    RelativeLayout userContractDetails;
    @BindView(R.id.user_department)
    TextView userDepartment;
    @BindView(R.id.user_department_person)
    TextView userDepartmentPerson;
    @BindView(R.id.user_company_person)
    TextView userCompanyPerson;
    @BindView(R.id.user_address)
    TextView userAddress;
    @BindView(R.id.user_change_pw)
    RelativeLayout userChangePw;
    @BindView(R.id.title_text)
    TextView titleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        titleText.setText("账户管理");

    }

    @OnClick({R.id.user_img, R.id.user_contract_details, R.id.user_change_pw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_img:
                break;
            case R.id.user_contract_details:
                break;
            case R.id.user_change_pw:
                break;
        }
    }
}
