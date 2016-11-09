package com.feicuiedu.mvpdemo.network;

import com.feicuiedu.mvpdemo.model.LoginResult;
import com.feicuiedu.mvpdemo.model.User;
import com.feicuiedu.mvpdemo.model.UserResult;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

/**
 * Created by 123 on 2016/11/8.
 */

public class RetrofitClient implements UserRetrofitApi{

    private static RetrofitClient retrofitClient;
    private static final String BASE_URL = "http://admin.syfeicuiedu.com";
    private final UserRetrofitApi userRetrofitApi;

    private RetrofitClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                // 添加了Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                // 为Retrofit添加OkHttp
                .client(okHttpClient)
                .build();
        userRetrofitApi = retrofit.create(UserRetrofitApi.class);

    }

    public static synchronized RetrofitClient getInstances() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    @Override
    public Call<UserResult> register(@Body User user) {
        return userRetrofitApi.register(user);
    }

    @Override
    public Call<LoginResult> login(@Body User user) {
        return userRetrofitApi.login(user);
    }
}
