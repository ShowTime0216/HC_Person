package com.hsy.hc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.adapter.TabLayoutAdapter;
import com.hsy.hc.fragment.Order.AllFragment;
import com.hsy.hc.fragment.Order.AuditFragment;
import com.hsy.hc.fragment.Order.CancelFragment;
import com.hsy.hc.fragment.Order.FinishFragment;
import com.hsy.hc.fragment.Order.PayFragment;
import com.hsy.hc.fragment.Order.UnfinishedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hsy_name on 16/3/25.
 * 订单
 */
public class OrderFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.order_viewPager)
    ViewPager orderViewPager;
    @BindView(R.id.order_tab)
    TabLayout orderTab;

    private View view;
    private static OrderFragment orderFragment;

    public static OrderFragment getInstance() {
        if (orderFragment == null) orderFragment = new OrderFragment();
        return orderFragment;
    }

    private TabLayoutAdapter tabLayoutAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private String titles[] = new String[]{"待审核", "待支付", "未完成", "已完成", "已取消", "全部"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        initView();
        Log.e("onCreateView--order", "onCreateView--order");
        return view;
    }

    private void initView() {
        titleText.setText("订单");
        fragments.add(new AuditFragment());
        fragments.add(new PayFragment());
        fragments.add(new UnfinishedFragment());
        fragments.add(new FinishFragment());
        fragments.add(new CancelFragment());
        fragments.add(new AllFragment());
        tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager(), fragments, titles);
        orderViewPager.setAdapter(tabLayoutAdapter);
        orderViewPager.addOnPageChangeListener(this);
        orderTab.setupWithViewPager(orderViewPager);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.e("onResume--order--visi", "onResume--order");
        } else {
            Log.e("onPause--order--visi", "onPause--order");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            Log.e("onPause--order--hidd", "onPause--order");
        } else {
            Log.e("onResume--order--hidd", "onResume--order");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume--order", "onResume--order");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause--order", "onPause--order");
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
