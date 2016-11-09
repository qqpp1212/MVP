package com.feicuiedu.mvpdemo.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.feicuiedu.mvpdemo.R;
import com.feicuiedu.mvpdemo.SuccessfulActivity;
import com.feicuiedu.mvpdemo.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.et_Username)
    EditText etUsername;
    @BindView(R.id.et_Passrword)
    EditText etPassrword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_Register)
    public void onClick() {
        // 去进行请求
        User user = new User(etUsername.getText().toString(),etPassrword.getText().toString());
        new LoginPresenter(this).login(user);
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "登录", "正在登录，不要着急啊~");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
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
