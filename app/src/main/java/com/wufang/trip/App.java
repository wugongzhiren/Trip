package com.wufang.trip;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/7/26.
 */
public class App extends Application {
    private OkHttpClient okHttpClient;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/
        //阿里OSS初始化
        //AliOss.init(context);
        //Configuration config = new Configuration.Builder().build();
              //  .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
              ////  .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
              //  .connectTimeout(10) // 链接超时。默认10秒
              //  .responseTimeout(60) // 服务器响应超时。默认60秒
             //   .recorder(recorder)  // recorder分片上传时，已上传片记录器。默认null
             //   .recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
              //  .zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
             //   .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        //UploadManager uploadManager = new UploadManager(config);
/*RongIM.connect(testtoken, new RongIMClient.ConnectCallback() {
    @Override
    public void onTokenIncorrect() {

    }

    @Override
    public void onSuccess(String s) {
        Toast.makeText(getApplicationContext(),"连接融云服务器成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(RongIMClient.ErrorCode errorCode) {

    }
});*/
     /*   bean = new Bmob_UserBean();
        bean.setObjectId("fa02b47eb3");*/
        context = getApplicationContext();
        //fresco初始化
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(this, config);


        //ImageLoad配置
      /*  ImageLoaderConfiguration config1 = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config1);*/
/*//阿里云旺配置
        final String APP_KEY = "23441577";
//必须首先执行这部分代码, 如果在":TCMSSevice"进程中，无需进行云旺（OpenIM）和app业务的初始化，以节省内存;
        SysUtil.setApplication(this);
        if (SysUtil.isTCMSServiceProcess(this)) {
            return;
        }
//第一个参数是Application Context
//这里的APP_KEY即应用创建时申请的APP_KEY，同时初始化必须是在主进程中
        if (SysUtil.isMainProcess()) {
            YWAPI.init(this, APP_KEY);
        }*/
//必须首先执行这部分代码, 如果在":TCMSSevice"进程中，无需进行云旺（OpenIM）和app业务的初始化，以节省内存;

    }



    public static Context getContext() {
        return context;
    }


    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }




}
