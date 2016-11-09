package com.feicuiedu.mvpdemo.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.feicuiedu.mvpdemo.R;
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

        User user = new User(etUsername.getText().toString(),etPassrword.getText().toString());


        // 去进行请求
        RetrofitClient.getInstances().register(user).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                Toast.makeText(RegisterActivity.this, "--请求成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "--请求失败", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
