package com.wufang.trip.function;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.wufang.trip.Base_activity;
import com.wufang.trip.MainActivity;
import com.wufang.trip.R;
import com.wufang.trip.tool.SharePreferences;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Welcome extends Base_activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome);
        ImageView mImageView = (ImageView) findViewById(R.id.welcome);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mImageView, "scaleY", 1f, 1.3f);
        animatorY.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mImageView, "scaleX", 1f, 1.3f);
        animatorX.setInterpolator(new AccelerateDecelerateInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.play(animatorY).with(animatorX);
        set.setDuration(5000);
        set.start();

        //判断是否第一次使用
        SharePreferences sharePreferences = new SharePreferences();
        if (sharePreferences.isLogin()) {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }
        else {
            Intent intent=new Intent(this, Reg.class);
            startActivity(intent);
            finish();
        }
    }
}
