package com.hsy.hc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.activity.mine.CollectionActivity;
import com.hsy.hc.activity.mine.LoginActivity;
import com.hsy.hc.activity.mine.SettingActivity;
import com.hsy.hc.activity.mine.UserInformationActivity;
import com.hsy.hc.activity.mine.VouchersActivity;
import com.hsy.hc.adapter.LikeGridViewAdapter;
import com.hsy.hc.entity.ServiceData;
import com.hsy.hc.entity.User;
import com.hsy.hc.view.CircleImageView;
import com.hsy.hc.view.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hsy_name on 16/9/1.
 * 个人中心
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_login)
    TextView mineLogin;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.mine_item_code)
    RelativeLayout mineItemCode;
    @BindView(R.id.mine_item_order)
    RelativeLayout mineItemOrder;
    @BindView(R.id.mine_item_vouchers)
    RelativeLayout mineItemVouchers;
    @BindView(R.id.mine_item_collection)
    RelativeLayout mineItemCollection;
    @BindView(R.id.mine_item_customer)
    RelativeLayout mineItemCustomer;
    @BindView(R.id.mine_item_setting)
    RelativeLayout mineItemSetting;
    @BindView(R.id.mine_gv)
    MyGridView mineGv;
    @BindView(R.id.mine_number)
    TextView mineNumber;
    @BindView(R.id.mine_information)
    LinearLayout mineInformation;
    @BindView(R.id.mine_avatar)
    CircleImageView mineAvatar;
    @BindView(R.id.text_right)
    TextView textRight;

    private View view;
    private LikeGridViewAdapter likeGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        if (User.getInstance() != null) {
            mineLogin.setVisibility(View.GONE);
            mineInformation.setVisibility(View.VISIBLE);
        } else {
            mineInformation.setVisibility(View.GONE);
            mineLogin.setVisibility(View.VISIBLE);
        }

        titleText.setText("我的");

        likeGridViewAdapter = new LikeGridViewAdapter(getActivity(), list2());
        mineGv.setAdapter(likeGridViewAdapter);
    }

    @OnClick({R.id.mine_item_code, R.id.mine_item_order, R.id.mine_item_vouchers, R.id.mine_item_collection, R.id.mine_item_customer,
            R.id.mine_item_setting, R.id.mine_avatar, R.id.mine_login})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.mine_item_code: // 二维码快速报修
                break;
            case R.id.mine_item_order: // 我的订单
                break;
            case R.id.mine_item_vouchers: // 代金券
                intent = new Intent(getActivity(), VouchersActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_item_collection: // 收藏
                intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_item_customer: // 客服
                break;
            case R.id.mine_item_setting: // 设置
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_avatar: // 头像
                intent = new Intent(getActivity(), UserInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_login: // 登录按钮
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
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
