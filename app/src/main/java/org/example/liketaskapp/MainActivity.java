package org.example.liketaskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input_email) TextView tv_email;
    @BindView(R.id.input_password) TextView tv_password;
    private RestManager restManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        restManager = new RestManager();
    }
    @OnClick(R.id.bt_login) void login() {
        String email = tv_email.getText().toString();
        String password = tv_password.getText().toString();
        if (!(email.equals(""))&&!(password.equals(""))) {
            login(email,password);
        }
    }
    private void login(String email,String password) {
        GetResponse getResponse = restManager.getRetrofit().create(GetResponse.class);
        Call<LoginResponse> call = getResponse.login(email,password);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }else{
                    //in case of any error in server
                    Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Check your internet connection",Toast.LENGTH_SHORT).show();
            }

        });
    }
}
