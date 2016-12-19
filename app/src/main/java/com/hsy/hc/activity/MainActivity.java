package com.hsy.hc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsy.hc.BaseActivity;
import com.hsy.hc.R;
import com.hsy.hc.fragment.ClassFragment;
import com.hsy.hc.fragment.HomeFragment;
import com.hsy.hc.fragment.MineFragment;
import com.hsy.hc.fragment.OrderFragment;
import com.hsy.hc.fragment.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.main_frag)
    FrameLayout mainFrag;
    @BindView(R.id.tab_home_img)
    ImageView tabHomeImg;
    @BindView(R.id.tab_home_tv)
    TextView tabHomeTv;
    @BindView(R.id.tab_home_layout)
    LinearLayout tabHomeLayout;
    @BindView(R.id.tab_class_img)
    ImageView tabClassImg;
    @BindView(R.id.tab_class_tv)
    TextView tabClassTv;
    @BindView(R.id.tab_class_layout)
    LinearLayout tabClassLayout;
    @BindView(R.id.tab_order_img)
    ImageView tabOrderImg;
    @BindView(R.id.tab_order_tv)
    TextView tabOrderTv;
    @BindView(R.id.tab_order_layout)
    LinearLayout tabOrderLayout;
    @BindView(R.id.tab_shopping_img)
    ImageView tabShoppingImg;
    @BindView(R.id.tab_shopping_tv)
    TextView tabShoppingTv;
    @BindView(R.id.tab_shopping_layout)
    LinearLayout tabShoppingLayout;
    @BindView(R.id.tab_mine_img)
    ImageView tabMineImg;
    @BindView(R.id.tab_mine_tv)
    TextView tabMineTv;
    @BindView(R.id.tab_mine_layout)
    LinearLayout tabMineLayout;

    public static MainActivity mainActivity;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ClassFragment classFragment;
    private OrderFragment orderFragment;
    private ShoppingFragment shoppingFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 透明状态栏
        if (mainActivity == null) {
            mainActivity = this;
        }
        initView();
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
//        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        bottomLayout.measure(w, h);
//        bottomHeight = bottomLayout.getMeasuredHeight();

        setTabSelection(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            HomeFragment.getInstance().onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick({R.id.tab_home_layout, R.id.tab_class_layout, R.id.tab_order_layout, R.id.tab_shopping_layout, R.id.tab_mine_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_home_layout:
                setTabSelection(0);
                break;
            case R.id.tab_class_layout:
                setTabSelection(1);
                break;
            case R.id.tab_order_layout:
                setTabSelection(2);
                break;
            case R.id.tab_shopping_layout:
                setTabSelection(3);
                break;
            case R.id.tab_mine_layout:
                setTabSelection(4);
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
                tabHomeImg.setImageResource(R.mipmap.ic_launcher);
                tabHomeTv.setTextColor(getResources().getColor(R.color.blue_1b));
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_frag, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                tabClassImg.setImageResource(R.mipmap.ic_launcher);
                tabClassTv.setTextColor(getResources().getColor(R.color.blue_1b));
                if (classFragment == null) {
                    classFragment = new ClassFragment();
                    transaction.add(R.id.main_frag, classFragment);
                } else {
                    transaction.show(classFragment);
                }
                break;
            case 2:
                tabOrderImg.setImageResource(R.mipmap.ic_launcher);
                tabOrderTv.setTextColor(getResources().getColor(R.color.blue_1b));
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.main_frag, orderFragment);
                } else {
                    transaction.show(orderFragment);
                }
                break;
            case 3:
                tabShoppingImg.setImageResource(R.mipmap.ic_launcher);
                tabShoppingTv.setTextColor(getResources().getColor(R.color.blue_1b));
                if (shoppingFragment == null) {
                    shoppingFragment = new ShoppingFragment();
                    transaction.add(R.id.main_frag, shoppingFragment);
                } else {
                    transaction.show(shoppingFragment);
                }
                break;
            case 4:
                tabMineImg.setImageResource(R.mipmap.ic_launcher);
                tabMineTv.setTextColor(getResources().getColor(R.color.blue_1b));
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.main_frag, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (classFragment != null) {
            transaction.hide(classFragment);
        }
        if (orderFragment != null) {
            transaction.hide(orderFragment);
        }
        if (shoppingFragment != null) {
            transaction.hide(shoppingFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    private void changeBackground() {
        tabHomeImg.setImageResource(R.mipmap.ic_launcher);
        tabClassImg.setImageResource(R.mipmap.ic_launcher);
        tabOrderImg.setImageResource(R.mipmap.ic_launcher);
        tabShoppingImg.setImageResource(R.mipmap.ic_launcher);
        tabMineImg.setImageResource(R.mipmap.ic_launcher);
        tabHomeTv.setTextColor(getResources().getColor(R.color.black_5d));
        tabClassTv.setTextColor(getResources().getColor(R.color.black_5d));
        tabOrderTv.setTextColor(getResources().getColor(R.color.black_5d));
        tabShoppingTv.setTextColor(getResources().getColor(R.color.black_5d));
        tabMineTv.setTextColor(getResources().getColor(R.color.black_5d));
    }
}
