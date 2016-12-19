package com.hsy.hc.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsy.hc.BaseFragment;
import com.hsy.hc.R;
import com.hsy.hc.activity.MainActivity;
import com.hsy.hc.activity.home.MessageActivity;
import com.hsy.hc.activity.home.MoreActivity;
import com.hsy.hc.adapter.CollectionAdapter;
import com.hsy.hc.adapter.HomeRecommendAdapter;
import com.hsy.hc.entity.ServiceData;
import com.hsy.hc.view.MyListView_Sl;
import com.hsy.hc.view.MyScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hsy_name on 16/3/25.
 * 首页
 */
public class HomeFragment extends BaseFragment implements MyScrollView.OnScrollListener, MyScrollView.ScrollViewListener {
    @BindView(R.id.main_address)
    TextView mainAddress;
    @BindView(R.id.main_head_rel)
    RelativeLayout mainHeadRel;
    @BindView(R.id.recommend_lv)
    RecyclerView recommendLv;
    @BindView(R.id.home_service_lv)
    MyListView_Sl homeServiceLv;
    @BindView(R.id.home_service_layout)
    LinearLayout homeServiceLayout;
    @BindView(R.id.home_custom_layout)
    LinearLayout homeCustomLayout;
    @BindView(R.id.home_consumables_layout)
    LinearLayout homeConsumablesLayout;
    @BindView(R.id.home_accessories_layout)
    LinearLayout homeAccessoriesLayout;
    @BindView(R.id.home_integral_layout)
    LinearLayout homeIntegralLayout;
    @BindView(R.id.home_code_layout)
    LinearLayout homeCodeLayout;
    @BindView(R.id.home_quick_layout)
    LinearLayout homeQuickLayout;
    @BindView(R.id.home_more_layout)
    LinearLayout homeMoreLayout;
    @BindView(R.id.home_scrollview)
    MyScrollView homeScrollview;
    @BindView(R.id.banner_layout)
    LinearLayout bannerLayout;
    @BindView(R.id.title_right_layout)
    LinearLayout titleRightLayout;
    @BindView(R.id.home_recommend_layout)
    LinearLayout homeRecommendLayout;

    private String img1 = "http://b344.photo.store.qq.com/psb?/V111YZBy0Fmora/df*X4h.ShNiA*vAV4Hp83ZwM5K7BIszLFJCy4bBlsFA!/c/dFgBAAAAAAAA&bo=AASAAkAG6AMFAAg!";
    private String img2 = "http://b347.photo.store.qq.com/psb?/V111YZBy0Fmora/MeyaBuIvb6RcxPpdkAXLwCGqHKf.8qr0ItlUdF6be.s!/c/dFsBAAAAAAAA&bo=AASAAkAG6AMFAAg!";
    private String img3 = "http://b347.photo.store.qq.com/psb?/V111YZBy0Fmora/L11xR8b26oDubT4z0Nrf8eK18I8TKLoJHnU.H97MawA!/c/dFsBAAAAAAAA&bo=AASAAkAG6AMFAAg!";
    private String img4 = "http://b346.photo.store.qq.com/psb?/V111YZBy0Fmora/4t6Vha9mn1PZHGEvcLA3gHohAJLK0GVVzAlc1rxiIlM!/c/dFoBAAAAAAAA&bo=AASAAkAG6AMFAAg!";
    private String img5 = "http://b348.photo.store.qq.com/psb?/V111YZBy0Fmora/eqI8G0jeLJJAvQsMUprrY*CBGQZitPuRSNwpC14mvFc!/c/dFwBAAAAAAAA&bo=AASAAkAG6AMFAAg!";
    private String img6 = "http://b107.photo.store.qq.com/psb?/V111YZBy0Fmora/AjQXzYXwu3tpnWiJR9EKmUgZqdOncrX.LC.eO*d0lBU!/c/dGsAAAAAAAAA&bo=AASAAkAG6AMFAAg!";
    private String img7 = "http://b349.photo.store.qq.com/psb?/V111YZBy0Fmora/xEQK*8qnvaoo9GOErQ3gI7Z*LVjc2uFVtgW*PnVZ76E!/c/dF0BAAAAAAAA&bo=AASAAkAG6AMFAAg!";
    private String[] images = new String[]{img1, img2, img3, img4, img5, img6, img7};

    private Banner ad_banner;
    private String main_address;
    private int bannerLayoutHeight = 0; // banner控件高度
    private int scrollHeight = 0; // 滑动高度
    private int titleHeight = 0; // 顶部title高度
    private int searchLayoutTop = 0;
    private View view;
    private static HomeFragment homeFragment;
    private HomeRecommendAdapter recommendAdapter; // 本月推荐list
    private CollectionAdapter collectionAdapter; // 第三方服务list
    public static int itemIndex;

    public static HomeFragment getInstance() {
        if (homeFragment == null) homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        height = dm.heightPixels; // 高度

//        searchMain.setFocusable(true);
//        searchMain.setFocusableInTouchMode(true);
//        searchMain.requestFocus();
        ad_banner = (Banner) view.findViewById(R.id.ad_banner);
        ad_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        ad_banner.setIndicatorGravity(BannerConfig.CENTER);
        ad_banner.setDelayTime(3000);
        ad_banner.setImages(images);
        ad_banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
            }
        });

        homeScrollview.setOnScrollListener(this);
        ScrollHeight();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendLv.setLayoutManager(linearLayoutManager);
        recommendAdapter = new HomeRecommendAdapter(getActivity(), list1());
        recommendLv.setAdapter(recommendAdapter);

        collectionAdapter = new CollectionAdapter(getActivity(), list2());
        homeServiceLv.setAdapter(collectionAdapter);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == getActivity().RESULT_OK) {
                if (data != null) {
                    String city = data.getStringExtra("city");
                    int city_id = data.getIntExtra("city_id", 0);
                    mainAddress.setText(city);
                }
            }
        }
    }

    /**
     * 设置地址
     *
     * @param Address
     */
    public void setAddress(String Address) {
        main_address = Address;
        mainAddress.setText(Address);
    }

    /**
     * 计算渐变距离(height_b)
     * 悬停高度(searchLayoutTop)
     */
    private void ScrollHeight() {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        bannerLayout.measure(w, h);
        bannerLayoutHeight = bannerLayout.getMeasuredHeight();
        scrollHeight = bannerLayoutHeight - getStatusBarHeight();
        homeScrollview.setScrollViewListener(this);
        searchLayoutTop = scrollHeight;//实际两者是同一高度
        mainHeadRel.measure(w, h);
        titleHeight = mainHeadRel.getMeasuredHeight();
    }

    /**
     * 获取手机状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onScroll(int scrollY) {

    }

    @Override
    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {  //设置标题的背景颜色
            mainHeadRel.setBackgroundColor(Color.argb((int) 0, 27, 157, 255));
        } else if (y > 0 && y <= scrollHeight) { // 滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / scrollHeight;
            float alpha = (255 * scale);
            mainHeadRel.setBackgroundColor(Color.argb((int) alpha, 27, 157, 255));
        } else {  //滑动到banner下面设置普通颜色
            mainHeadRel.setBackgroundColor(Color.argb((int) 255, 27, 157, 255));
        }
    }

    private List<ServiceData> list1() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("配件更换" + i);
            serviceData.setContent("¥485.5");
            list.add(serviceData);
        }
        return list;
    }

    private List<ServiceData> list2() {
        List<ServiceData> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            ServiceData serviceData = new ServiceData();
            serviceData.setName("拆了重装");
            serviceData.setContent("原价:¥500.00");
            serviceData.setText1("家庭网路的配置服务、更换显示屏");
            serviceData.setText2("¥200.0-¥300.0");
            serviceData.setText3("下单量999");
            serviceData.setText4("99%好评");
            serviceData.setText5("满100000元才能使用优惠券");
            list.add(serviceData);
        }
        return list;
    }

    @OnClick({R.id.home_service_layout, R.id.home_custom_layout, R.id.home_consumables_layout, R.id.home_accessories_layout, R.id.home_integral_layout,
            R.id.home_code_layout, R.id.home_quick_layout, R.id.home_more_layout, R.id.title_right_layout, R.id.home_recommend_layout})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.home_service_layout: // 预约服务
                itemIndex = 0;
                MainActivity.mainActivity.setTabSelection(1);
                break;
            case R.id.home_custom_layout: // 企业定制
                break;
            case R.id.home_consumables_layout: // 耗材更换
                itemIndex = 1;
                MainActivity.mainActivity.setTabSelection(1);
                break;
            case R.id.home_accessories_layout: // 配件更换
                itemIndex = 2;
                MainActivity.mainActivity.setTabSelection(1);
                break;
            case R.id.home_integral_layout: // 积分商城
                itemIndex = 3;
                MainActivity.mainActivity.setTabSelection(1);
                break;
            case R.id.home_code_layout: // 二维码报修
                break;
            case R.id.home_quick_layout: // 快捷报修
                itemIndex = 4;
                MainActivity.mainActivity.setTabSelection(1);
                break;
            case R.id.home_more_layout: // 更多
                intent = new Intent(getActivity(), MoreActivity.class);
                startActivity(intent);
                break;
            case R.id.title_right_layout: // 消息
                intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.home_recommend_layout: // 本月推荐
                intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
        }
    }

}

