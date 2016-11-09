package com.feicuiedu.mvpdemo.register;

import com.feicuiedu.mvpdemo.model.User;
import com.feicuiedu.mvpdemo.model.UserResult;
import com.feicuiedu.mvpdemo.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 123 on 2016/11/9.
 */
// 业务类：网络请求、数据库操作。。。。耗时的操作
public class RegisterPresenter {

    /**
     * 对外提供一个方法：主要做注册的业务实现
     * 怎么调到视图的方法呢？
     * 1. Activity，传个上下文：生命周期的
     * 2. 接口回调的方式：我们要使用的方式
     */
    private RegisterView registerView;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
    }

    public interface RegisterView {
        void showProgress();

        void hideProgress();

        void showMessage(String msg);

        void navigationToMain();
    }

    public void register(User user) {
        registerView.showProgress();
        // 去进行请求
        RetrofitClient.getInstances().register(user).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                registerView.hideProgress();
                if (response != null && response.isSuccessful()) {
                    UserResult userResult = response.body();
                    if (userResult == null) {
                        registerView.showMessage("发生了错误~~");
                        return;
                    }
                    if (userResult.getErrcode() == 1) {
                        registerView.showMessage("注册成功了");
                        registerView.navigationToMain();
                        return;
                    }
                    registerView.showMessage(userResult.getErrmsg());
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                registerView.hideProgress();
                registerView.showMessage("不好意思，请求失败了" + t.getMessage());
            }
        });
    }
}
