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

import com.adapter.Dynamic_Rv_Adapter;
import com.bean.Dynamic;
import com.function.Dynamic_Detail;
import com.function.Dynamic_publish;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 首页
 * Created by Administrator on 2016/7/6.
 */
public class Index_fg extends android.support.v4.app.Fragment{
    public   List<Dynamic> messages= new ArrayList<Dynamic>();//动态的数据
    private RecyclerView recyclerView;
    private FloatingActionButton dynamic_action_bt;
    private Dynamic_Rv_Adapter adapter;//动态适配器

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fg, container, false);
        dynamic_action_bt= (FloatingActionButton) view.findViewById(R.id.dynamic_action_bt);
        dynamic_action_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Dynamic_publish.class);
                startActivity(intent);
            }
        });
        recyclerView= (RecyclerView) view.findViewById(R.id.main_rv);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager( 2 , StaggeredGridLayoutManager. VERTICAL ); //两列，纵向排列

        recyclerView.setLayoutManager(mLayoutManager) ;
        initData();
        return view;
    }

    private void initData() {
        BmobQuery<Dynamic> query=new BmobQuery<>();
        query.include("user");
        query.order("-createdAt").findObjects(new FindListener<Dynamic>() {
            @Override
            public void done(List<Dynamic> list, BmobException e) {
                if(e==null){
                    messages.addAll(list);
                    adapter=new Dynamic_Rv_Adapter(getActivity(),messages);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        //设置view点击事件
        adapter.setItemListener(new Dynamic_Rv_Adapter.OnItemClickListener() {
            @Override
            public void ItemClick(Dynamic dynamic) {
                //事件回调
                Intent intent=new Intent(getActivity(),Dynamic_Detail.class);
                intent.putExtra("dynamic",dynamic);
            }
        });
    }
}









