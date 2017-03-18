package com.wufang.trip.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wufang.trip.Base_activity;
import com.wufang.trip.MainActivity;
import com.wufang.trip.R;
import com.wufang.trip.bean.User;
import com.wufang.trip.retrofit.UserManageService;
import com.wufang.trip.tool.ShowToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Login extends Base_activity implements View.OnClickListener {
    private EditText name;
    private EditText password;
    private Button reg;
    private Button login;
    private ProgressBar progress;
    Retrofit retrofit;
    UserManageService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        retrofit = getRetrofit();
        service = retrofit.create(UserManageService.class);


        bindView();
        // 不存在设置默认图片
    }

    private void bindView() {
        //imageView= (ImageView) findViewById(R.id.bg_imageview);
        progress = (ProgressBar) findViewById(R.id.reg_progress);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        reg = (Button) findViewById(R.id.login_bt);

        login = (Button) findViewById(R.id.backtoreg);
        name.setOnClickListener(this);
        login.setOnClickListener(this);
        password.setOnClickListener(this);
        reg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_bt:
                String nametem = name.getText().toString();
                if (nametem.equals("")) {
                    Toast.makeText(this, "请输入用户名！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pstem = password.getText().toString();
                if (pstem.equals("")) {
                    Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                Call<String> callReg = service.login(nametem, pstem);
                callReg.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body().equals("SUCCESS")) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            Login.this.finish();
                        }
                        ShowToast.showToast(Login.this, "登录失败");
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        ShowToast.showToast(Login.this, "服务器开小差了，请重新登录");
                    }
                });


                break;
            case R.id.backtoreg:
                Intent intent = new Intent(Login.this, Reg.class);
                startActivity(intent);
                Login.this.finish();
                break;


        }
    }
}