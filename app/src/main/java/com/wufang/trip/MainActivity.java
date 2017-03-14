package com.wufang.trip;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MainActivity extends Base_activity {
    private ViewPager mainVp;
    private TabLayout mainTab;

    private ArrayList<String> titles;//viewpager的标题
    private ArrayList<Fragment> fragments ;//装载进viewpager的fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainVp= (ViewPager) findViewById(R.id.main_vp);
        mainTab= (TabLayout) findViewById(R.id.main_tab);
        titles=new ArrayList<>();
        fragments=new ArrayList<>();
        titles.add("分享");
        titles.add("消息");
        titles.add("发现");
        titles.add("我的");
        fragments.add(new Index_fg());
        fragments.add(new Friend_fg());
        fragments.add(new Faxian_fg());
        fragments.add(new My_fg());
        MyfragmentPagerAdapter adapter=new MyfragmentPagerAdapter(getSupportFragmentManager(),titles,fragments);
        mainVp.setAdapter(adapter);
        mainTab.setupWithViewPager(mainVp);
    }
}
