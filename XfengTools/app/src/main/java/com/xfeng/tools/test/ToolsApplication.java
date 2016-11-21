package com.xfeng.tools.test;

import android.app.Application;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   ToolsApplication
 * @CreateTime:  2016/11/20 下午10:31
 * @Description: Application
 */
public class ToolsApplication extends Application{

    private static ToolsApplication instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this ;
    }

    public ToolsApplication getInstance(){
        return instance ;
    }

}
