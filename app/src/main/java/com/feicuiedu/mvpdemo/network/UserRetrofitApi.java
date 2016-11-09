package com.feicuiedu.mvpdemo.network;

import com.feicuiedu.mvpdemo.model.LoginResult;
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


    @POST("/Handler/UserHandler.ashx?action=register")
    Call<UserResult> register(@Body User user);

    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);

}
