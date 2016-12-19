package com.hsy.hc.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hsy.hc.MyApplication;
import com.hsy.hc.R;
import com.hsy.hc.entity.Citys;
import com.hsy.hc.util.JsonUtil;
import com.hsy.hc.view.MyLetterListView;
import com.hsy.hc.view.MyListView_Sl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class CityActivity extends AppCompatActivity implements OnScrollListener {

    private ListAdapter listAdapter;
    private BaseAdapter adapter;
    private ListView personList;
    private ListView resultList;
    private MyLetterListView letterListView; // A-Z listview
    private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    private EditText sh;
    private TextView tv_noresult;
    private List<Citys> city_result;
    private ResultListAdapter resultListAdapter;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private String currentCity; // 用于保存定位到的城市
    private int locateProcess = 1; // 记录当前定位的状态 正在定位-定位成功-定位失败
    private boolean isNeedFresh;

    private ImageView cityback;
    private double Latitude;
    private double Longitude;
    public static CityActivity cityActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citys);
        MyApplication.getInstance().addActivity(this);
        if (cityActivity == null)
            cityActivity = this;
        initview();
        initListener();
    }

    public void initview() {
        // TODO Auto-generated method stub
        city_result = new ArrayList<>();
        cityback = (ImageView) findViewById(R.id.cityback);
        personList = (ListView) findViewById(R.id.list_view);
        resultList = (ListView) findViewById(R.id.search_result);
        sh = (EditText) findViewById(R.id.sh);
        tv_noresult = (TextView) findViewById(R.id.tv_noresult);
        sh.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString() == null || "".equals(s.toString())) {
                    letterListView.setVisibility(View.VISIBLE);
                    personList.setVisibility(View.VISIBLE);
                    resultList.setVisibility(View.GONE);
                    tv_noresult.setVisibility(View.GONE);
                } else {
                    city_result.clear();
                    letterListView.setVisibility(View.GONE);
                    personList.setVisibility(View.GONE);
                    getResultCityList(s.toString());
                    if (city_result.size() <= 0) {
                        tv_noresult.setVisibility(View.VISIBLE);
                        resultList.setVisibility(View.GONE);
                    } else {
                        tv_noresult.setVisibility(View.GONE);
                        resultList.setVisibility(View.VISIBLE);
                        resultListAdapter.notifyDataSetChanged();
                    }
                   /*
                    letterListView.setVisibility(View.GONE);
                    personList.setVisibility(View.GONE);
                    resultList.setVisibility(View.VISIBLE);
                    List<Citys> list = getResultCityList(s.toString());
                    Log.i("lalalal",list.get(0).toString());

                    resultListAdapter.notifyDataSetChanged();*/
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        resultListAdapter = new ResultListAdapter(CityActivity.this, city_result);
        resultList.setAdapter(resultListAdapter);
        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//--------------------------------------------------------------------------
                Citys citys = (Citys) resultListAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), citys.getCity_name(), Toast.LENGTH_SHORT).show();
                Log.i("city", citys.getCity_name() + "--------city搜索---------------------------------------");
//                Intent intent = new Intent(CityActivity.this, MainActivity.class);
//                intent.putExtra("city", citys.getCity_name().substring(0, 2));
//                intent.putExtra("city_id", citys.getCity_id());
//                setResult(RESULT_OK, intent);
                Intent intent = new Intent(CityActivity.this, MapActivity.class);
                intent.putExtra("city", citys.getCity_name());
                if (citys.getCity_name().equals(currentCity)) {
                    intent.putExtra("city_type", 1);//和定位城市一样
                } else {
                    intent.putExtra("city_type", 2);//不一样
                }
                startActivity(intent);
                // finish();
            }
        });

        letterListView = (MyLetterListView) findViewById(R.id.MyLetterListView01);
        letterListView
                .setOnTouchingLetterChangedListener(new LetterListViewListener());

        isNeedFresh = true;
        listAdapter = new ListAdapter(CityActivity.this, hotCityInit(), getCityList());
        locateProcess = 1;
        personList.setOnScrollListener(this);
        personList.setAdapter(listAdapter);
        personList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position >= 3) {
//---------------------------------------------------------------------
                    Citys citys = (Citys) listAdapter.getItem(position-3);
                    Toast.makeText(getApplicationContext(), citys.getCity_name(), Toast.LENGTH_SHORT).show();
                    Log.i("city", citys.getCity_name() + "--------city列表---------------------------------------");
//                    Intent intent = new Intent(CityActivity.this, MainActivity.class);
//                    intent.putExtra("city", citys.getCity_name().substring(0, 2));
//                    intent.putExtra("city_id", citys.getCity_id());
//                    setResult(RESULT_OK, intent);
                    Intent intent = new Intent(CityActivity.this, MapActivity.class);
                    intent.putExtra("city", citys.getCity_name());
                    if (citys.getCity_name().equals(currentCity)) {
                        intent.putExtra("city_type", 1);//和定位城市一样
                    } else {
                        intent.putExtra("city_type", 2);//不一样
                    }
                    startActivity(intent);
                    // finish();
                }
            }
        });

        InitLocation();
        mLocationClient.startLocation();
    }

    public void initListener() {
        // TODO Auto-generated method stub
        cityback.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    private void InitLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.getContext());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
    }

    /**
     * 按字母排序（城市）
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Citys> getCityList() {
        List<Citys> list;
        JsonUtil jsonUtil = new JsonUtil();
        list = jsonUtil.getCitysList(CityActivity.this);
        Collections.sort(list, comparator);
        return list;
    }

    private void getResultCityList(String keyword) {
        JsonUtil jsonUtil = new JsonUtil();
        List<Citys> list = jsonUtil.getCitysList(CityActivity.this);
        for (Citys citys : list) {
            if (citys.getCity_name().contains(keyword) | citys.getInitial().contains(keyword.toUpperCase())) {
                city_result.add(citys);
                Log.i("haha", citys.toString());
            }
        }
    }

    /**
     * a-z排序
     */
    @SuppressWarnings("rawtypes")
    Comparator comparator = new Comparator<Citys>() {
        @Override
        public int compare(Citys lhs, Citys rhs) {
            String a = lhs.getInitial();
            String b = rhs.getInitial();
            int flag = a.compareTo(b);
            if (flag == 0) {
                return a.compareTo(b);
            } else {
                return flag;
            }
        }
    };

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    currentCity = aMapLocation.getCity().substring(0,
                            aMapLocation.getCity().length() - 1);
                    Latitude = aMapLocation.getLatitude();
                    Longitude = aMapLocation.getLongitude();
                    locateProcess = 2; // 定位成功
                    personList.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                } else {
                    locateProcess = 3; // 定位失败
                    personList.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };


    private class ResultListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<Citys> results = new ArrayList<>();

        public ResultListAdapter(Context context, List<Citys> results) {
            inflater = LayoutInflater.from(context);
            this.results = results;
        }

        @Override
        public int getCount() {
            return results.size();
        }

        @Override
        public Object getItem(int position) {
            return results.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) convertView
                        .findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(results.get(position).getCity_name());
            return convertView;
        }

        class ViewHolder {
            TextView name;
        }
    }

    public class ListAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<Citys> cityses;
        private List<Citys> hotList;
        private String pinyin = " ";
        final int VIEW_TYPE = 5;

        public ListAdapter(Context context, List<Citys> hotList, List<Citys> list) {
            this.inflater = LayoutInflater.from(context);
            this.context = context;
            this.cityses = list;
            this.hotList = hotList;

            alphaIndexer = new HashMap<String, Integer>();
            for (int i = 0; i < list.size(); i++) {
                // 当前汉语拼音首字母
                String currentStr = list.get(i).getInitial();
                // 上一个汉语拼音首字母，如果不存在为" "
                String previewStr = (i - 1) >= 0 ? list.get(i - 1).getInitial() : " ";
                if (!previewStr.equals(currentStr)) {
                    String name = list.get(i).getInitial();
                    alphaIndexer.put(name, i);
                }
            }
        }

        @Override
        public int getViewTypeCount() {
            return VIEW_TYPE;
        }

        @Override
        public int getItemViewType(int position) {
            return position < 4 ? position : 4;
        }

        @Override
        public int getCount() {
            return cityses.size();
        }

        @Override
        public Object getItem(int position) {
            return cityses.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ViewHolder holder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final TextView city;
            int viewType = getItemViewType(position);
            if (viewType == 0) {
                convertView = inflater.inflate(R.layout.total_item, null);
            } else if (viewType == 1) { // 定位
                convertView = inflater.inflate(R.layout.frist_list_item, null);
                TextView locateHint = (TextView) convertView
                        .findViewById(R.id.locateHint);
                city = (TextView) convertView.findViewById(R.id.lng_city);
                city.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (locateProcess == 2) {
//-------------------------------------------------------
                            Toast.makeText(getApplicationContext(),
                                    city.getText().toString(),
                                    Toast.LENGTH_SHORT).show();
                            Log.i("city", city.getText().toString() + "--------city定位---------------------------------------");
                           /* Intent intent = new Intent(CityActivity.this, MainActivity.class);
                            intent.putExtra("city", city.getText().toString());
                            intent.putExtra("city_id", location(city.getText().toString()));
                            setResult(RESULT_OK, intent);
                            finish();*/
                            Intent intent = new Intent(CityActivity.this, MapActivity.class);
                            intent.putExtra("city", city.getText().toString());
                            intent.putExtra("city_type", 1);
                            startActivity(intent);
                            // finish();
                        } else if (locateProcess == 3) {
                            locateProcess = 1;
                            personList.setAdapter(listAdapter);
                            listAdapter.notifyDataSetChanged();
                            mLocationClient.stopLocation();
                            isNeedFresh = true;
                            InitLocation();
                            currentCity = "";
                            mLocationClient.startLocation();
                        }
                    }

                });
                ProgressBar pbLocate = (ProgressBar) convertView
                        .findViewById(R.id.pbLocate);
                if (locateProcess == 1) { // 正在定位
                    locateHint.setText("正在定位");
                    city.setVisibility(View.GONE);
                    pbLocate.setVisibility(View.VISIBLE);
                } else if (locateProcess == 2) { // 定位成功
                    locateHint.setText("当前定位城市");
                    city.setVisibility(View.VISIBLE);
                    city.setText(currentCity);
                    mLocationClient.stopLocation();
                    pbLocate.setVisibility(View.GONE);
                } else if (locateProcess == 3) {
                    locateHint.setText("未定位到城市,请选择");
                    city.setVisibility(View.VISIBLE);
                    city.setText("重新选择");
                    pbLocate.setVisibility(View.GONE);
                }
            } else if (viewType == 2) {
                convertView = inflater.inflate(R.layout.recent_city, null);
                MyListView_Sl hotCity = (MyListView_Sl) convertView
                        .findViewById(R.id.recent_city);
                hotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
//-----------------------------------------------
                        Toast.makeText(getApplicationContext(),
                                hotCityInit().get(position).getCity_name(),
                                Toast.LENGTH_SHORT).show();
                        Log.i("city", hotCityInit().get(position).getCity_name() + "--------热门城市---------------------------------------");
                        //请求返回方法
//                        Intent intent = new Intent(CityActivity.this, MainActivity.class);
//                        intent.putExtra("city", hotCityInit().get(position).getCity_name());
//                        setResult(RESULT_OK, intent);
                        Intent intent = new Intent(CityActivity.this, MapActivity.class);
                        intent.putExtra("city", hotCityInit().get(position).getCity_name());
                        if (hotCityInit().get(position).getCity_name().equals(currentCity)) {
                            intent.putExtra("city_type", 1);//和定位城市一样
                        } else {
                            intent.putExtra("city_type", 2);//不一样
                        }
                        startActivity(intent);
                        //finish();
                    }
                });
                hotCity.setAdapter(new HotCityAdapter(context, this.hotList));
                TextView hotHint = (TextView) convertView
                        .findViewById(R.id.recentHint);
                hotHint.setText("国内热门城市");
            } else {
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.list_items, null);
                    holder = new ViewHolder();
                    holder.alpha = (TextView) convertView
                            .findViewById(R.id.alpha);
                    holder.name = (TextView) convertView
                            .findViewById(R.id.name);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                if (position >= 3) {
                    holder.name.setText(cityses.get(position-3).getCity_name());
                    if (position == 3) {
                        holder.alpha.setVisibility(View.VISIBLE);
                        holder.alpha.setText(cityses.get(position-3).getInitial());
                    } else {
                        String currentStr = cityses.get(position-3).getInitial();
                        String previewStr = (position - 4) >= 0 ? cityses.get(position - 4).getInitial() : " ";
                        if (!previewStr.equals(currentStr)) {
                            holder.alpha.setVisibility(View.VISIBLE);
                            holder.alpha.setText(currentStr);
                        } else {
                            holder.alpha.setVisibility(View.GONE);
                        }
                    }
                }
            }
            return convertView;
        }

        private class ViewHolder {
            TextView alpha; // 首字母标题
            TextView name; // 城市名字
        }
    }

    @Override
    protected void onStop() {
        mLocationClient.stopLocation();
        super.onStop();
    }

    class HotCityAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private List<Citys> hotCitys;

        public HotCityAdapter(Context context, List<Citys> hotCitys) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            this.hotCitys = hotCitys;
        }

        @Override
        public int getCount() {
            return hotCitys.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.list_item, null);
            TextView city = (TextView) convertView.findViewById(R.id.name);
            city.setText(hotCitys.get(position).getCity_name());
            return convertView;
        }
    }

    private class LetterListViewListener implements
            MyLetterListView.OnTouchingLetterChangedListener {

        @Override
        public void onTouchingLetterChanged(final String s) {
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                personList.setSelection(position);
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL
                || scrollState == SCROLL_STATE_FLING) {
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
    }

    /**
     * 热门城市
     */
    public ArrayList<Citys> hotCityInit() {
        ArrayList<Citys> city_hot = new ArrayList<>();
        Citys city = new Citys(73, "上海", "2", 1);
        city_hot.add(city);
        city = new Citys(401, "南京", "2", 2);
        city_hot.add(city);
        city = new Citys(402, "杭州", "2", 3);
        city_hot.add(city);
        return city_hot;
    }

    /**
     * 查找定位城市id
     *
     * @param city
     */
    public int location(String city) {
        int city_id = 0;
        JsonUtil jsonUtil = new JsonUtil();
        List<Citys> list = jsonUtil.getCitysList(CityActivity.this);
        for (Citys citys : list) {
            if (citys.getCity_name().contains(city)) {
                city_id = citys.getCity_id();
                return city_id;
            }
        }
        return city_id;
    }
}