package com.feicuiedu.mvpdemo.login;

import com.feicuiedu.mvpdemo.model.LoginResult;
import com.feicuiedu.mvpdemo.model.User;
import com.feicuiedu.mvpdemo.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 123 on 2016/11/9.
 */

// 业务类
public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(User user){

        loginView.showProgress();

        RetrofitClient.getInstances().login(user).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                loginView.hideProgress();
                if (response.isSuccessful()){
                    LoginResult result = response.body();
                    if (result==null){
                        loginView.showMessage("可能发生了错误");
                        return;
                    }
                    if (result.getErrcode()==1){
                        loginView.showMessage("登录成功");
                        loginView.navigationToMain();
                        return;
                    }
                    loginView.showMessage(result.getErrmsg());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                loginView.hideProgress();
                loginView.showMessage("请求失败了"+t.getMessage());
            }
        });
    }

}
