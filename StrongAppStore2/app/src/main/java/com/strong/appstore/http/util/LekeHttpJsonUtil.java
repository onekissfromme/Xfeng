package com.strong.appstore.http.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.strong.appstore.common.Config;
import com.strong.appstore.http.BaseResponseBean;
import com.strong.appstore.utils.LogUtils;

/**
 * Descripthion:    网络请求返回后转化实体bean的json工具类
 * User:            xuyuqiang
 * <p/>
 * Date:            2016-06-07 15:31
 * <p/>
 * Version:         V 2.7.0
 */

public class LekeHttpJsonUtil {
    private static final String TAG = "LekeHttpJsonUtil" ;

    /**
     * Json转换方法,基于新的接口返回数据
     * User: xuyuqiang
     * Data: 16/6/7 15:42
     * @param json      需要转换的json字符串
     * @param clazz     需要转换的实体bean,此类需要继承BaseResponseBean
     * @return
     *      转化对象 : BaseResponseBean
     */
    public static BaseResponseBean getResponseBean(String json , Class clazz){
        BaseResponseBean baseResponseBean = null ;
        /** 如果提供的json字符串不符合要求,直接返回null **/
        if (TextUtils.isEmpty(json)){
            return  null ;
        }
        if (Config.IS_DEBUG) {
            LogUtils.i(json);
        }
        try{
            Object obj = null ;
            Gson gson = new Gson() ;
            JsonObject jsonObj = new JsonParser().parse(json).getAsJsonObject() ;
            JsonObject dataJsonObj = jsonObj.getAsJsonObject("datas") ;
            if (!dataJsonObj.isJsonNull()) {
                obj = gson.fromJson(dataJsonObj, clazz);
            }

            baseResponseBean = (BaseResponseBean)obj ;
            JsonElement successElement = jsonObj.get("success") ;
            if (!successElement.isJsonNull()){
                baseResponseBean.success = successElement.getAsBoolean() ;
            }
            JsonElement messageElement = jsonObj.get("message") ;
            if (!messageElement.isJsonNull()){
                baseResponseBean.message = messageElement.getAsString() ;
            }
            JsonElement ticketElement = jsonObj.get("ticket") ;
            if (!ticketElement.isJsonNull()){
                baseResponseBean.ticket = ticketElement.getAsString() ;
            }
            JsonElement codeElement = jsonObj.get("code") ;
            if (!codeElement.isJsonNull()){
                baseResponseBean.code = codeElement.getAsInt() ;
            }
            JsonElement jsessionidElement = jsonObj.get("jsessionid") ;
            if (!jsessionidElement.isJsonNull()){
                baseResponseBean.jsessionid = jsessionidElement.getAsLong() ;
            }

            return baseResponseBean ;
        }catch (Exception e){
            if (Config.IS_DEBUG) {
                LogUtils.e(TAG , e);
            }
            return baseResponseBean ;
        }
    }
}
