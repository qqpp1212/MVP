package com.feicuiedu.mvpdemo.network;

import com.feicuiedu.mvpdemo.model.User;
import com.feicuiedu.mvpdemo.model.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 123 on 2016/11/8.
 */

public interface UserRetrofitApi {

    // 在这个接口里面使用Retrofit来构建接口请求
    // 稍后会讲解注解这个内容

    @POST("/Handler/UserHandler.ashx?action=register")
    Call<UserResult> register(@Body User user);

}
