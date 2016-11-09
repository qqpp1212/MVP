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

public class RegisterActivity extends AppCompatActivity implements RegisterView{

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
        /**
         * 当前Activity主要做了什么：本来应该做的视图，业务操作，这样写的话，Activity是不是太过于臃肿了？
         * 怎么去给Activity减压呢？
         * 1. 业务是不是可以抽离出去呢？
         *
         */

        User user = new User(etUsername.getText().toString(), etPassrword.getText().toString());
        new RegisterPresenter(this).register(user);
    }


    private ProgressDialog progressDialog;

    // 视图分析的具体实现
    @Override
    public void showProgress() {
        progressDialog=ProgressDialog.show(this,"注册","正在注册中，亲，不要着急啊~");
    }

    @Override
    public void hideProgress() {
        if (progressDialog!=null) {
//            progressDialog.hide();
            progressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigationToMain() {
        startActivity(new Intent(this, SuccessfulActivity.class));
    }
}
