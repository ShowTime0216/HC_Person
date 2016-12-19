package com.hsy.hc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.fragment.classes.AccessoriesFragment;
import com.hsy.hc.fragment.classes.ConsumablesFragment;
import com.hsy.hc.fragment.classes.IntegralFragment;
import com.hsy.hc.fragment.classes.QuickFragment;
import com.hsy.hc.fragment.classes.SendFragment;
import com.hsy.hc.fragment.classes.ServiceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者:liupeng
 * 16/9/14 11:03
 * 注释: 分类
 */
public class ClassFragment extends BaseFragment {

    @BindView(R.id.class_left_service_img)
    ImageView classLeftServiceImg;
    @BindView(R.id.class_left_service_text)
    TextView classLeftServiceText;
    @BindView(R.id.class_left_service_layout)
    LinearLayout classLeftServiceLayout;
    @BindView(R.id.class_left_consumables_img)
    ImageView classLeftConsumablesImg;
    @BindView(R.id.class_left_consumables_text)
    TextView classLeftConsumablesText;
    @BindView(R.id.class_left_consumables_layout)
    LinearLayout classLeftConsumablesLayout;
    @BindView(R.id.class_left_accessories_img)
    ImageView classLeftAccessoriesImg;
    @BindView(R.id.class_left_accessories_text)
    TextView classLeftAccessoriesText;
    @BindView(R.id.class_left_accessories_layout)
    LinearLayout classLeftAccessoriesLayout;
    @BindView(R.id.class_left_integral_img)
    ImageView classLeftIntegralImg;
    @BindView(R.id.class_left_integral_text)
    TextView classLeftIntegralText;
    @BindView(R.id.class_left_integral_layout)
    LinearLayout classLeftIntegralLayout;
    @BindView(R.id.class_left_quick_img)
    ImageView classLeftQuickImg;
    @BindView(R.id.class_left_quick_text)
    TextView classLeftQuickText;
    @BindView(R.id.class_left_quick_layout)
    LinearLayout classLeftQuickLayout;
    @BindView(R.id.class_left_send_img)
    ImageView classLeftSendImg;
    @BindView(R.id.class_left_send_text)
    TextView classLeftSendText;
    @BindView(R.id.class_left_send_layout)
    LinearLayout classLeftSendLayout;
    @BindView(R.id.class_frag)
    FrameLayout classFrag;

    private View view;
    private FragmentManager fragmentManager;
    private ServiceFragment serviceFragment;
    private ConsumablesFragment consumablesFragment;
    private AccessoriesFragment accessoriesFragment;
    private IntegralFragment integralFragment;
    private QuickFragment quickFragment;
    private SendFragment sendFragment;
    public static ClassFragment classFragment;

    public static ClassFragment getInstance() {
        if (classFragment == null) {
            classFragment = new ClassFragment();
        }
        return classFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_class, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        fragmentManager = getActivity().getSupportFragmentManager();
        if (null != String.valueOf(HomeFragment.itemIndex)) {
            Log.e("itemIndex", HomeFragment.itemIndex + "");
            setTabSelection(HomeFragment.itemIndex);
        } else {
            Log.e("------", "----------");
            setTabSelection(0);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){

        } else {
            if (null != String.valueOf(HomeFragment.itemIndex)) {
                Log.e("itemIndex---", HomeFragment.itemIndex + "");
                setTabSelection(HomeFragment.itemIndex);
            } else {
                setTabSelection(0);
            }
        }
    }

    @OnClick({R.id.class_left_service_layout, R.id.class_left_consumables_layout, R.id.class_left_accessories_layout, R.id.class_left_integral_layout,
            R.id.class_left_quick_layout, R.id.class_left_send_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.class_left_service_layout: // 预约服务
                setTabSelection(0);
                break;
            case R.id.class_left_consumables_layout: // 耗材更换
                setTabSelection(1);
                break;
            case R.id.class_left_accessories_layout: // 配件更换
                setTabSelection(2);
                break;
            case R.id.class_left_integral_layout: // 积分商城
                setTabSelection(3);
                break;
            case R.id.class_left_quick_layout: // 快捷报修
                setTabSelection(4);
                break;
            case R.id.class_left_send_layout: // 保内送修
                setTabSelection(5);
                break;
        }
    }

    /**
     * fragment切换操作
     *
     * @param index
     */
    public void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction(); // 开启一个Fragment事务
        hideFragments(transaction); // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        changeBackground();
        switch (index) {
            case 0:
                classLeftServiceLayout.setBackgroundResource(R.color.blue_4c);
                classLeftServiceImg.setImageResource(R.mipmap.item03);
                classLeftServiceText.setTextColor(getResources().getColor(R.color.white));
                if (serviceFragment == null) {
                    serviceFragment = new ServiceFragment();
                    transaction.add(R.id.class_frag, serviceFragment);
                } else {
                    transaction.show(serviceFragment);
                }
                break;
            case 1:
                classLeftConsumablesLayout.setBackgroundResource(R.color.blue_4c);
                classLeftConsumablesImg.setImageResource(R.mipmap.item03);
                classLeftConsumablesText.setTextColor(getResources().getColor(R.color.white));
                if (consumablesFragment == null) {
                    consumablesFragment = new ConsumablesFragment();
                    transaction.add(R.id.class_frag, consumablesFragment);
                } else {
                    transaction.show(consumablesFragment);
                }
                break;
            case 2:
                classLeftAccessoriesLayout.setBackgroundResource(R.color.blue_4c);
                classLeftAccessoriesImg.setImageResource(R.mipmap.item03);
                classLeftAccessoriesText.setTextColor(getResources().getColor(R.color.white));
                if (accessoriesFragment == null) {
                    accessoriesFragment = new AccessoriesFragment();
                    transaction.add(R.id.class_frag, accessoriesFragment);
                } else {
                    transaction.show(accessoriesFragment);
                }
                break;
            case 3:
                classLeftIntegralLayout.setBackgroundResource(R.color.blue_4c);
                classLeftIntegralImg.setImageResource(R.mipmap.item03);
                classLeftIntegralText.setTextColor(getResources().getColor(R.color.white));
                if (integralFragment == null) {
                    integralFragment = new IntegralFragment();
                    transaction.add(R.id.class_frag, integralFragment);
                } else {
                    transaction.show(integralFragment);
                }
                break;
            case 4:
                classLeftQuickLayout.setBackgroundResource(R.color.blue_4c);
                classLeftQuickImg.setImageResource(R.mipmap.item03);
                classLeftQuickText.setTextColor(getResources().getColor(R.color.white));
                if (quickFragment == null) {
                    quickFragment = new QuickFragment();
                    transaction.add(R.id.class_frag, quickFragment);
                } else {
                    transaction.show(quickFragment);
                }
                break;
            case 5:
                classLeftSendLayout.setBackgroundResource(R.color.blue_4c);
                classLeftSendImg.setImageResource(R.mipmap.item03);
                classLeftSendText.setTextColor(getResources().getColor(R.color.white));
                if (sendFragment == null) {
                    sendFragment = new SendFragment();
                    transaction.add(R.id.class_frag, sendFragment);
                } else {
                    transaction.show(sendFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (serviceFragment != null) {
            transaction.hide(serviceFragment);
        }
        if (consumablesFragment != null) {
            transaction.hide(consumablesFragment);
        }
        if (accessoriesFragment != null) {
            transaction.hide(accessoriesFragment);
        }
        if (integralFragment != null) {
            transaction.hide(integralFragment);
        }
        if (quickFragment != null) {
            transaction.hide(quickFragment);
        }
        if (sendFragment != null) {
            transaction.hide(sendFragment);
        }
    }

    private void changeBackground() {
        classLeftServiceImg.setImageResource(R.mipmap.item03);
        classLeftConsumablesImg.setImageResource(R.mipmap.item03);
        classLeftAccessoriesImg.setImageResource(R.mipmap.item03);
        classLeftIntegralImg.setImageResource(R.mipmap.item03);
        classLeftQuickImg.setImageResource(R.mipmap.item03);
        classLeftSendImg.setImageResource(R.mipmap.item03);
        classLeftServiceText.setTextColor(getResources().getColor(R.color.black_5d));
        classLeftConsumablesText.setTextColor(getResources().getColor(R.color.black_5d));
        classLeftAccessoriesText.setTextColor(getResources().getColor(R.color.black_5d));
        classLeftIntegralText.setTextColor(getResources().getColor(R.color.black_5d));
        classLeftQuickText.setTextColor(getResources().getColor(R.color.black_5d));
        classLeftSendText.setTextColor(getResources().getColor(R.color.black_5d));
        classLeftServiceLayout.setBackgroundResource(R.color.white);
        classLeftConsumablesLayout.setBackgroundResource(R.color.white);
        classLeftAccessoriesLayout.setBackgroundResource(R.color.white);
        classLeftIntegralLayout.setBackgroundResource(R.color.white);
        classLeftQuickLayout.setBackgroundResource(R.color.white);
        classLeftSendLayout.setBackgroundResource(R.color.white);
    }
}
