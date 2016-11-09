package com.feicuiedu.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.feicuiedu.mvpdemo.login.LoginActivity;
import com.feicuiedu.mvpdemo.register.RegisterActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:{
                startActivity(new Intent(this, LoginActivity.class));
            }
                break;
            case R.id.btn_register:{
                startActivity(new Intent(this, RegisterActivity.class));
            }
                break;
        }
    }
}
