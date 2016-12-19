package com.hsy.hc.net;

import com.hsy.hc.entity.ResultModel;
import com.hsy.hc.entity.User;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by hsy on 16/9/8.
 */
public class PersonalNet {

    /**
     * 获取基本信息
     *
     * @return
     */
    public static Call<ResultModel<User>> Users(){
        return RetrofitNet.headerRequest().Users();
    }

    /**
     * 修改资料
     *
     * @param map
     * @return
     */
    public static Call<ResultModel> UserUpdate(Map<String, RequestBody> map){
        return RetrofitNet.headerRequest().UserUpdate(map);
    }

}
