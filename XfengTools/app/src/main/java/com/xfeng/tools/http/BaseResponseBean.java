package com.xfeng.tools.http;

import com.xfeng.tools.http.util.LekeHttpJsonUtil;

import java.io.Serializable;

/**
 * Descripthion:    网络数据返回后实体组装时的基类
 * User:            xuyuqiang
 * <p/>
 * Date:            2016-06-07 15:28
 * <p/>
 * Version:         V 2.7.0
 */

public class BaseResponseBean implements Serializable{

    public boolean success ;
    public String message ;
    public String ticket ;
    public int code ;
    public long jsessionid ;

    public static BaseResponseBean getEitry(String json){
        return LekeHttpJsonUtil.getResponseBean(json, BaseResponseBean.class) ;
    }
}
