package com.hsy.hc.interfaces;

import com.hsy.hc.entity.LRRmodel;
import com.hsy.hc.entity.ResultModel;
import com.hsy.hc.entity.User;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by hsy on 16/9/8.
 */
public interface APIService {

    /**
     * 获取验证码（注册）
     *
     * @param mobile
     * @return
     */
    @GET("user/security-code/register")
    Call<ResultModel<LRRmodel>> getCode(@Query("mobile")CharSequence mobile);


    /**
     * 注册
     *
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/index/create")
    Call<ResultModel<LRRmodel>> register(@Field("mobile") CharSequence mobile, @Field("code") CharSequence code, @Field("password") CharSequence password);


    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/index/login")
    Call<ResultModel<LRRmodel>> login(@Field("mobile") CharSequence mobile, @Field("password") CharSequence password);


    /**
     * 获取验证码(重置密码)
     *
     * @param mobile
     * @return
     */
    @GET("user/security-code/reset-password")
    Call<ResultModel<LRRmodel>> getResetCode(@Query("mobile") CharSequence mobile);

    /**
     * 重置密码
     *
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/index/reset-password")
    Call<ResultModel<LRRmodel>> resetPassword(@Field("mobile") CharSequence mobile, @Field("code") CharSequence code, @Field("password") CharSequence password);

    /**
     * 修改密码
     *
     * @param old_password
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/index/update-password")
    Call<ResultModel> ChangePassword(@Field("old_password") String old_password, @Field("password") String password);

    /**
     * QQ登录--不要验证
     *
     * @return
     */
    @GET("oauth/qq")
    Call<ResultModel<LRRmodel>> qqLogin(@QueryMap Map<String, Object> map);

    /**
     * 新浪微博登录--不要验证
     *
     * @return
     */
    @GET("oauth/weibo")
    Call<ResultModel<LRRmodel>> sinaLogin(@QueryMap Map<String, Object> map);

    /**
     * 微信登录--不要验证
     *
     * @param code
     * @return
     */
    @GET("oauth/wechat")
    Call<ResultModel<LRRmodel>> wechatLogin(@Query("code") String code);


    /**
     * 获取基本信息
     *
     * @return
     */
    @GET("user/index/view")
    Call<ResultModel<User>> Users();

    /**
     * 修改资料
     *
     * @param map
     * @return
     */
    @Multipart
    @POST("user/index/update")
    Call<ResultModel> UserUpdate(@PartMap Map<String, RequestBody> map);
}
