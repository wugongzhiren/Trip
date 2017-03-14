package com.wufang.trip.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.wufang.trip.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 * Created by Administrator on 2016/7/6.
 */
public class Index_fg extends android.support.v4.app.Fragment{




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fg, container, false);
        initdata(view);

        return view;
    }

    private void initdata(View view) {
        //从服务器获取数据
    }


}









