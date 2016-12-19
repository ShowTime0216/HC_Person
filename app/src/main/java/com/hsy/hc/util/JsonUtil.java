package com.hsy.hc.util;

import android.content.Context;

import com.hsy.hc.R;
import com.hsy.hc.entity.Citys;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsy on 16/9/5.
 */
public class JsonUtil {

    private String Tag="PingYinJsonUtil";
    HanyuPinyinOutputFormat format;

    public HanyuPinyinOutputFormat getFormat() {
        if (format != null) {
            return format;
        } else {
            format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
            format.setVCharType(HanyuPinyinVCharType.WITH_V);
            return format;
        }
    }

    public List<Citys> getCitysList(Context context) {
        List<Citys> citysList = new ArrayList<>();

        try {
            InputStream is = context.getResources().openRawResource(R.raw.city);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("cityArr");
            for (int i = 0; i < jsonArray.length(); i++) {
                Citys citys = new Citys();
                System.out.println(i);
                JSONObject item = jsonArray.getJSONObject(i);
                citys.setCity_id(item.getInt("city_id"));
                citys.setCity_name(item.getString("city_name"));
                citys.setInitial(item.getString("initial"));
                citys.setProvince_id(item.getInt("province_id"));
                //PinYin(item.getString("city_name"));
                citysList.add(citys);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return citysList;
    }
}
