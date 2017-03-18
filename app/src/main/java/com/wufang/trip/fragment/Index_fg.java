package com.wufang.trip.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;
import com.wufang.trip.Constant;
import com.wufang.trip.R;
import com.wufang.trip.bean.Site;
import com.wufang.trip.bean.Strategy;
import com.wufang.trip.function.Login;
import com.wufang.trip.function.Reg;
import com.wufang.trip.retrofit.SiteManageService;
import com.wufang.trip.retrofit.StrategyService;
import com.wufang.trip.retrofit.UserManageService;
import com.wufang.trip.tool.ShowToast;
import com.wufang.trip.tool.ToStringConverterFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 首页
 * Created by Administrator on 2016/7/6.
 */
public class Index_fg extends android.support.v4.app.Fragment {

    private SwipeRefreshLayout refreshLayout;
    Retrofit retrofit;
    SiteManageService service;
    StrategyService service1;
    Call<List<Site>> call;
    Call<List<Strategy>> call1;
    private SimpleDraweeView img1;
    private SimpleDraweeView img2;
    private SimpleDraweeView img3;
    private SimpleDraweeView img4;
    private SimpleDraweeView img5;
    private SimpleDraweeView img6;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fg, container, false);
        retrofit = new Retrofit.Builder()
                .addConverterFactory(new ToStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(Constant.BASEURL)
                .build();
        service = retrofit.create(SiteManageService.class);
        service1 = retrofit.create(StrategyService.class);
        initView(view);
        //initdata(view);

        return view;
    }

    private void initView(final View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        img1 = (SimpleDraweeView) view.findViewById(R.id.img1);
        img2 = (SimpleDraweeView) view.findViewById(R.id.img2);
        img3 = (SimpleDraweeView) view.findViewById(R.id.img3);
        img4 = (SimpleDraweeView) view.findViewById(R.id.img4);
        img5 = (SimpleDraweeView) view.findViewById(R.id.img5);
        img6 = (SimpleDraweeView) view.findViewById(R.id.img6);
        text1 = (TextView) view.findViewById(R.id.text1);
        text2 = (TextView) view.findViewById(R.id.text2);
        text3 = (TextView) view.findViewById(R.id.text3);
        text4 = (TextView) view.findViewById(R.id.text4);
        text5 = (TextView) view.findViewById(R.id.text5);
        text6 = (TextView) view.findViewById(R.id.text6);
        refreshLayout.setColorSchemeColors(Color.BLACK, Color.BLUE, Color.GRAY, Color.RED);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {

                refreshLayout.setRefreshing(true);
                //Toast.makeText(Clocle_help_activity.this, "开始测试", Toast.LENGTH_SHORT).show();
                //首次进入，预加载
                call = service.getsite();
                call1 = service1.getStrategy();
                call.enqueue(new Callback<List<Site>>() {
                    @Override
                    public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                        // ShowToast.showToast(Reg.this, "注册成功");
                        List<Site> sites = response.body();

                        img1.setImageURI(sites.get(0).getImageUrls().split(",")[0]);
                        img2.setImageURI(sites.get(1).getImageUrls().split(",")[0]);
                        img3.setImageURI(sites.get(2).getImageUrls().split(",")[0]);
                        text1.setText(sites.get(0).getDescByOneWord());
                        text2.setText(sites.get(1).getDescByOneWord());
                        text3.setText(sites.get(2).getDescByOneWord());
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<Site>> call, Throwable t) {
                        refreshLayout.setRefreshing(false);

                    }
                });
                call1.enqueue(new Callback<List<Strategy>>() {
                    @Override
                    public void onResponse(Call<List<Strategy>> call, Response<List<Strategy>> response) {
                        // ShowToast.showToast(Reg.this, "注册成功");
                        List<Strategy> strategys = response.body();

                        img4.setImageURI(strategys.get(0).getImageUrls().split(",")[0]);
                        img5.setImageURI(strategys.get(1).getImageUrls().split(",")[0]);
                        img6.setImageURI(strategys.get(2).getImageUrls().split(",")[0]);
                        text4.setText(strategys.get(0).getTitle());
                        text5.setText(strategys.get(1).getTitle());
                        text6.setText(strategys.get(2).getTitle());
                        refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<Strategy>> call, Throwable t) {

                    }
                    //refreshLayout.setRefreshing(false);


                });

                refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        call.enqueue(new Callback<List<Site>>() {
                            @Override
                            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                                // ShowToast.showToast(Reg.this, "注册成功");

                                refreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onFailure(Call<List<Site>> call, Throwable t) {
                                refreshLayout.setRefreshing(false);

                            }
                        });
                    }
                });
            }
        });
    }





}









