package com.strong.appstore.main;

import android.app.Application;

/**
 * User:        xuyuqiang
 * Date:        2016/11/21 10:20
 * Viewsion:    1.0.9
 * ClassName:   StrongAppStoreApplication
 * Description: 应用中心的Application
 **/

public class StrongAppStoreApplication extends Application {

    private static StrongAppStoreApplication instance ;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this ;
    }

    public static StrongAppStoreApplication getInstance(){
        return instance ;
    }
}
