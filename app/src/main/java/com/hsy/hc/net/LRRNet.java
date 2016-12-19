package com.hsy.hc.net;

import com.hsy.hc.entity.LRRmodel;
import com.hsy.hc.entity.ResultModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.QueryMap;

/**
 * Created by hsy on 16/9/8.
 */
public class LRRNet {

    /**
     * 获取验证码（注册）
     * @param mobile
     * @return
     */
    public static Call<ResultModel<LRRmodel>> getCode(CharSequence mobile){
        return RetrofitNet.request().getCode(mobile);
    }

    /**
     * 注册
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    public static Call<ResultModel<LRRmodel>> register(CharSequence mobile, CharSequence code, CharSequence password){
        return RetrofitNet.request().register(mobile,code,password);
    }

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    public static Call<ResultModel<LRRmodel>> login(CharSequence mobile, CharSequence password) {
        return RetrofitNet.request().login(mobile, password);
    }

    /**
     * 获取验证码(重置密码)
     *
     * @param mobile
     * @return
     */
    public static Call<ResultModel<LRRmodel>> getResetCode(CharSequence mobile) {
        return RetrofitNet.request().getResetCode(mobile);
    }

    /**
     * 重置密码
     *
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    public static Call<ResultModel<LRRmodel>> resetPassword(CharSequence mobile, CharSequence code, CharSequence password) {
        return RetrofitNet.request().resetPassword(mobile, code, password);
    }

    /**
     * 修改密码
     *
     * @param old_password
     * @param password
     * @return
     */
    public static Call<ResultModel> ChangePassword(String old_password, String password){
        return RetrofitNet.headerRequest().ChangePassword(old_password,password);
    }

    /**
     * QQ登录
     *
     * @param map
     * @return
     */
    public static Call<ResultModel<LRRmodel>> qqLogin(@QueryMap Map<String, Object> map) {
        return RetrofitNet.request().qqLogin(map);
    }

    /**
     * 新浪微博登录
     *
     * @param map
     * @return
     */
    public static Call<ResultModel<LRRmodel>> sinaLogin(@QueryMap Map<String, Object> map) {
        return RetrofitNet.request().sinaLogin(map);
    }

    /**
     * 微信登录
     *
     * @param code
     * @return
     */
    public static Call<ResultModel<LRRmodel>> wechatLogin(String code) {
        return RetrofitNet.request().wechatLogin(code);
    }

}
