package com.strong.appstore.http;

import com.strong.appstore.main.StrongAppStoreApplication;

/**
 * Descripthion:    网络请求的实现父类
 * User:            xuyuqiang
 * <p>
 * Date:            2016-06-07 14:00
 * <p>
 * Version:         V 2.7.0
 */

public class BaseLekeNoHttp {

    public StrongAppStoreApplication app;


    public BaseLekeNoHttp() {
        app = StrongAppStoreApplication.getInstance();
    }
}
