package com.feicuiedu.mvpdemo.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.feicuiedu.mvpdemo.R;
import com.feicuiedu.mvpdemo.SuccessfulActivity;
import com.feicuiedu.mvpdemo.model.User;
import com.feicuiedu.mvpdemo.model.UserResult;
import com.feicuiedu.mvpdemo.network.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_Username)
    EditText etUsername;
    @BindView(R.id.et_Passrword)
    EditText etPassrword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_Register)
    public void onClick() {

        /**
         * 网络请求，业务
         * 分析视图：具体实现视图在Activity里面实现
         * 1. 显示进度条，告诉你我正在请求
         * 2. 隐藏进度条，请求结束
         * 3. 显示提示信息，弹个吐司友好的提示
         * 4. 导航到主页面
         */

        showProgress();

        User user = new User(etUsername.getText().toString(), etPassrword.getText().toString());

        // 去进行请求
        RetrofitClient.getInstances().register(user).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                hideProgress();
                if (response!=null&&response.isSuccessful()){
                    UserResult userResult = response.body();
                    if (userResult==null){
                        showMessage("发生了错误~~");
                        return;
                    }
                    if (userResult.getErrcode()==1){
                        showMessage("注册成功了");
                        navigationToMain();
                        return;
                    }
                    showMessage(userResult.getErrmsg());
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                hideProgress();
                showMessage("不好意思，请求失败了"+t.getMessage());
            }
        });
    }
    private ProgressDialog progressDialog;

    // 视图分析的具体实现
    public void showProgress() {
        progressDialog=ProgressDialog.show(this,"注册","正在注册中，亲，不要着急啊~");
    }

    public void hideProgress() {
        if (progressDialog!=null) {
//            progressDialog.hide();
            progressDialog.dismiss();
        }
    }

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void navigationToMain() {
        startActivity(new Intent(this, SuccessfulActivity.class));
    }
}
