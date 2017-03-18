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

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Reg extends Base_activity implements View.OnClickListener {
    private EditText name;
    private EditText password;
    private Button reg;
    private TextView phonereg;
    private Button login;
    private ProgressBar progress;
    Retrofit retrofit;
    UserManageService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_layout);
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
        reg = (Button) findViewById(R.id.reg_bt);

        login = (Button) findViewById(R.id.backtologin);
        name.setOnClickListener(this);
        login.setOnClickListener(this);
        password.setOnClickListener(this);
        reg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_bt:
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
                Call<String> callReg = service.reg(nametem, pstem);
                callReg.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        ShowToast.showToast(Reg.this, "注册成功");
                        Intent intent = new Intent(Reg.this, Login.class);
                        startActivity(intent);
                        Reg.this.finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        ShowToast.showToast(Reg.this, "注册失败，请重试");
                        progress.setVisibility(View.GONE);
                    }
                });


                break;
            case R.id.backtologin:
                Intent intent = new Intent(Reg.this, Login.class);
                startActivity(intent);
                Reg.this.finish();
                break;


        }
    }
}
