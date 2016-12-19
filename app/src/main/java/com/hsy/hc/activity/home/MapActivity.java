package com.hsy.hc.activity.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.district.DistrictItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.hsy.hc.BaseActivity;
import com.hsy.hc.MyApplication;
import com.hsy.hc.R;
import com.hsy.hc.adapter.MapPageAdapter;
import com.hsy.hc.adapter.PoisListAdapter;
import com.hsy.hc.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends BaseActivity implements ViewPager.OnPageChangeListener, LocationSource, AMapLocationListener, AMap.OnCameraChangeListener, PoiSearch.OnPoiSearchListener, DistrictSearch.OnDistrictSearchListener {

    @BindView(R.id.map_address)
    TextView mapAddress;
    @BindView(R.id.map_address_lin)
    LinearLayout mapAddressLin;
    @BindView(R.id.map_cancel)
    TextView mapCancel;
    @BindView(R.id.map_nearby_view1)
    View mapNearbyView1;
    @BindView(R.id.map_nearby1)
    LinearLayout mapNearby1;
    @BindView(R.id.map_nearby_view2)
    View mapNearbyView2;
    @BindView(R.id.map_nearby2)
    LinearLayout mapNearby2;
    @BindView(R.id.map_viewpage)
    ViewPager mapViewpage;
    @BindView(R.id.map_nearby1_text)
    TextView mapNearby1Text;
    @BindView(R.id.map_nearby2_text)
    TextView mapNearby2Text;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.map_center)
    ImageView mapCenter;
    @BindView(R.id.map_search)
    SearchView mapSearch;

    private AMap aMap = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    // 当前行政区划
    private DistrictItem currentDistrictItem = null;
    // 下级行政区划集合
    private Map<String, List<DistrictItem>> subDistrictMap = new HashMap<String, List<DistrictItem>>();


    private MapPageAdapter mapPageAdapter;
    private ArrayList<View> viewContainter = new ArrayList<View>();
    private ArrayList<PoiItem> poiList = new ArrayList<>();
    private PoisListAdapter poisListAdapter;
    private ListView nearby_list1;
    private ListView nearby_list2;
    private String city;
    private int city_type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MyApplication.getInstance().addActivity(this);
        ButterKnife.bind(this);
        map.onCreate(savedInstanceState);
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        city_type = intent.getIntExtra("city_type", 0);
        Initialization();
        initview();
    }

    private void initview() {
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.layout_map1, null);
        View view2 = inflater.inflate(R.layout.layout_map2, null);
        nearby_list1 = (ListView) view1.findViewById(R.id.nearby_list1);
        nearby_list2 = (ListView) view2.findViewById(R.id.nearby_list2);
        poisListAdapter = new PoisListAdapter(this);
        nearby_list1.setOnItemClickListener(onItemClickListener1);
        nearby_list1.setAdapter(poisListAdapter);
        viewContainter.add(view1);
        viewContainter.add(view2);
        mapPageAdapter = new MapPageAdapter(viewContainter);
        mapViewpage.setAdapter(mapPageAdapter);
        mapViewpage.setCurrentItem(0);
        mapViewpage.addOnPageChangeListener(this);
        mapSearch.setIconifiedByDefault(false);
        mapSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {//点击搜索后
                searchPoi(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {//输入
                return false;
            }
        });
    }

    /**
     * 初始化地图
     */
    private void Initialization() {
        //初始化地图变量
        if (aMap == null) {
            aMap = map.getMap();
        }
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17.0f));
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类
        mUiSettings.setScaleControlsEnabled(true);//显示比例尺控件
        mUiSettings.setZoomControlsEnabled(false);//缩放按钮是否显示
        if (city_type == 1) {
            aMap.setLocationSource(this);
            aMap.setMyLocationEnabled(true);
        } else if (city_type == 2) {
            District_Search();
        }
    }

    private void District_Search() {
        aMap.clear();
        DistrictSearch search = new DistrictSearch(MapActivity.this);
        DistrictSearchQuery query = new DistrictSearchQuery();
        query.setKeywords(city);//传入关键字
        query.setShowBoundary(true);//是否返回边界值
        search.setQuery(query);
        search.setOnDistrictSearchListener(this);//绑定监听器
        search.searchDistrictAnsy();//开始搜索
    }


    @OnClick({R.id.map_address_lin, R.id.map_cancel, R.id.map_nearby1, R.id.map_nearby2})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.map_address_lin:
                finish();
                break;
            case R.id.map_cancel:
                finish();
                break;
            case R.id.map_nearby1:
                mapViewpage.setCurrentItem(0);
                break;
            case R.id.map_nearby2:
                mapViewpage.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position) {
            case 0:
                mapNearby1Text.setTextColor(getResources().getColor(R.color.blue_1b));
                mapNearbyView1.setVisibility(View.VISIBLE);
                mapNearby2Text.setTextColor(getResources().getColor(R.color.black_5d));
                mapNearbyView2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mapNearby1Text.setTextColor(getResources().getColor(R.color.black_5d));
                mapNearbyView1.setVisibility(View.INVISIBLE);
                mapNearby2Text.setTextColor(getResources().getColor(R.color.blue_1b));
                mapNearbyView2.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 选中地址回首页
     */
    AdapterView.OnItemClickListener onItemClickListener1 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            PoiItem poiItem = (PoiItem) poisListAdapter.getItem(i);
            HomeFragment.getInstance().setAddress(poiItem.getTitle());
            MyApplication.getInstance().exit();
        }
    };

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                aMap.setOnCameraChangeListener(this);//定位成功后再设置周边检索监听，否则会发生两次监听
            } else {
                Toast.makeText(MapActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onDistrictSearched(DistrictResult districtResult) {
        if (districtResult == null || districtResult.getDistrict() == null) {
            return;
        }
        final DistrictItem item = districtResult.getDistrict().get(0);
        if (item == null) {
            return;
        }
        LatLonPoint centerLatLng = item.getCenter();
        if (centerLatLng != null) {
            aMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(new LatLng(centerLatLng.getLatitude(), centerLatLng.getLongitude()), 8));
        }
        String[] polyStr = item.districtBoundary();
        if (polyStr == null || polyStr.length == 0) {
            return;
        }
        for (String str : polyStr) {
            String[] lat = str.split(";");
            PolylineOptions polylineOption = new PolylineOptions();
            boolean isFirst = true;
            LatLng firstLatLng = null;
            for (String latstr : lat) {
                String[] lats = latstr.split(",");
                if (isFirst) {
                    isFirst = false;
                    firstLatLng = new LatLng(Double
                            .parseDouble(lats[1]), Double
                            .parseDouble(lats[0]));
                }
                polylineOption.add(new LatLng(Double
                        .parseDouble(lats[1]), Double
                        .parseDouble(lats[0])));
            }
            if (firstLatLng != null) {
                polylineOption.add(firstLatLng);
            }
            polylineOption.width(5).color(Color.BLUE);
            aMap.addPolyline(polylineOption);
        }
        aMap.setOnCameraChangeListener(this);//定位成功后再设置周边检索监听，否则会发生两次监听

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        Log.e("point2", cameraPosition.target.toString());
        query = new PoiSearch.Query("", "", city);
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查询页码
        poiSearch = new PoiSearch(this, query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(cameraPosition.target.latitude,
                cameraPosition.target.longitude), 1000));//设置周边搜索的中心点以及半径
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {
                poiList = poiResult.getPois();
                poisListAdapter.clear();
                poisListAdapter.addItem(poiList);
                poisListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    /**
     * 搜索方法
     *
     * @param keyWord //搜索内容
     */
    private void searchPoi(String keyWord) {
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", city);
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                if (i == 1000) {
                    if (poiResult != null && poiResult.getQuery() != null) {// 搜索poi的结果
                        // 取得搜索到的poiitems有多少页
                        List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                        if (poiItems != null && poiItems.size() > 0) {
                            aMap.clear();// 清理之前的图标
                            PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
                            poiOverlay.removeFromMap();
                            poiOverlay.addToMap();
                            poiOverlay.zoomToSpan();
                        } else {
                            Toast.makeText(MapActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MapActivity.this, "没有数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MapActivity.this, i + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        map.onSaveInstanceState(outState);
    }

}
