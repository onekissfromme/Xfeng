package com.xfeng.tools.http;

import android.content.Context;
import android.text.TextUtils;

import com.leke.http.plug.LekeResponse;
import com.leke.http.plug.lekehttp.LekeHttp;
import com.strong.leke.teacher.LekeTeacherApplication;
import com.strong.leke.teacher.common.LekeConfig;
import com.strong.leke.teacher.gateway.IpTableCache;
import com.strong.leke.teacher.util.DataUtils;
import com.strong.leke.teacher.util.LogUtils;
import com.strong.lib.util.NumberFormatUtil;
import com.yolanda.nohttp.rest.Request;

import java.net.URL;
import java.util.Map;

/**
 * Descripthion:
 * LekeHttp网络请求实现类
 * 项目和网络的中间连接类,主要作用就是:根据URL来进行ip替换和URL的重新组装
 * 此类不封装到LekeHttp中,直接复制到项目中
 *
 * User:            xuyuqiang
 * <p>
 * Date:            2016-06-07 13:51
 * <p>
 * Version:         V 2.7.0
 */

public class LekeNoHttp extends BaseLekeNoHttp{

    /**
     *
     * 网络请求
     *
     * @param what              用来标志请求, 当多个请求使用同一个{@link HttpListener}时, 在回调方法中会返回这个what 默认-1,不标记
     * @param method            请求类型:POST OR GET  默认POST
     * @param responseType      返回类型:String or JSON  默认:String
     * @param params            请求参数,必填*
     * @param context           context初始化dialog , 默认为null,不启动dialog
     * @param canCancel         是否允许用户取消请求
     * @param listener          请求成功时的回调
     * @param errorListener     请求失败时的回到
     * @param _s				请求服务类型
     * @param _m				请求接口类型
     */
    public LekeNoHttp(int what ,int method , int responseType , Map<String , Object> params , String _s , String _m , Context context , boolean canCancel , LekeResponse.Listener listener, LekeResponse.ErrorListener errorListener ) {
        super();
        String url = initUrl(_s, _m) ;
        doPost(what, method, responseType, params, context, canCancel, listener, errorListener, url);

    }
    /**
     *
     * 网络请求
     *
     * @param what                  用来标志请求, 当多个请求使用同一个{@link HttpListener}时, 在回调方法中会返回这个what 默认-1,不标记
     * @param method                请求类型:POST OR GET  默认POST
     * @param params                请求参数,必填*
     * @param listener              请求成功时的回调
     * @param errorListener         请求失败时的回到
     * @param _s				    请求服务类型
     * @param _m				    请求接口类型
     */
    public LekeNoHttp(int what ,int method , Map<String , Object> params ,String _s , String _m , LekeResponse.Listener listener, LekeResponse.ErrorListener errorListener ) {
        super();
        String url = initUrl(_s, _m) ;
        doPost(what, method, -1, params, null, false, listener, errorListener, url);

    }

    /**
     *
     * 网络请求
     *
     * @param params                请求参数,必填*
     * @param listener              请求成功时的回调
     * @param errorListener         请求失败时的回到
     * @param _s				    请求服务类型
     * @param _m				    请求接口类型
     */
    public LekeNoHttp( Map<String , Object> params ,String _s , String _m , LekeResponse.Listener listener, LekeResponse.ErrorListener errorListener ) {
        super();
        String url = initUrl(_s, _m) ;
        doPost(-1, -1, -1, params, null, false, listener, errorListener, url);

    }
    /**
     *
     * 特殊网络请求
     *      此网络请求主要使用于登录前,登录前服务器是不会反悔ticket的,此时的ticket需要调用者自己传过来
     *
     * @param ticket                网络请求是秘钥
     * @param params                请求参数,必填*
     * @param _s				    请求服务类型
     * @param _m				    请求接口类型
     * @param listener              请求成功时的回调
     * @param errorListener         请求失败时的回到
     */
    public LekeNoHttp(String ticket , Map<String , Object> params ,String _s , String _m , LekeResponse.Listener listener, LekeResponse.ErrorListener errorListener){
        String url = initUrl(ticket , _s, _m) ;
        doPost(-1, -1, -1, params, null, false, listener, errorListener, url);
    }

    public LekeNoHttp(int what , Request<String> request , LekeResponse.Listener listener, LekeResponse.ErrorListener errorListener){
        request.addHeader("HOST" , DataUtils.KEY_TUTOR_HOST);
        request.add("ticket", LekeTeacherApplication.getInstance().mParameters.getToken());
        new LekeHttp(what , request , listener , errorListener) ;
    }

	/**
     * 需要其他的Host
     */
    public LekeNoHttp(int what, Request<String> request, String host, LekeResponse.Listener listener, LekeResponse.ErrorListener errorListener) {
        request.addHeader("HOST", host);
        request.add("ticket", LekeTeacherApplication.getInstance().mParameters.getToken());
        new LekeHttp(what, request, listener, errorListener);
    }


    public void doPost(int what , int method , int responseType , Map<String , Object> params , Context context , boolean canCancel , LekeResponse.Listener listener, LekeResponse.ErrorListener errorListener, String url){
        LekeHttp lekeHttp = new LekeHttp(what, method, responseType, params, context, canCancel, listener, errorListener, url) ;
    }

    /**
     * 根据请求服务类型和接口类型组装URL
     *
     * User: xuyuqiang
     * Data: 16/6/7 14:17
     * @param _s
     * @param _m
     * @return
     *       组装好的URL : String
     */
    private String initUrl(String _s , String _m){
        String url = LekeConfig.HOME_WORK_NEW;
        /** 获取host值 **/

        String ticket = "";
		if (app != null && app.mParameters.getToken() != null) {
			ticket = app.mParameters.getToken();
		}
        url = url + "?ticket="
                + ticket + "&_s=" + _s
                + "&_m=" + _m + "&device=android" + "&version="
                + LekeConfig.REQUEST_VERSION;

        if (LekeConfig.ENVIRONMENT == LekeConfig.ApiEnvironment.online) {
            return changeHost(url);
        }else{
            return url ;
        }
    }

    /**
     * URL组装方法,此方法主要是用在登录之前
     * User: xuyuqiang
     * Data: 16/6/14 13:51
     * @param ticket    调用者传过来的ticket,如果为空便自动赋值为10位随机数
     * @param _s
     * @param _m
     * @return
     *      组装好的URL:String
     */
    private String initUrl(String ticket , String _s , String _m){
        String url = LekeConfig.HOME_WORK_NEW;
        /** 判断是否为空,如果为空便赋值一个10位随机数 */
        if (TextUtils.isEmpty(ticket)){
            ticket = NumberFormatUtil.buildRandom(10) + "" ;
        }

        url = url + "?ticket="
                + ticket + "&_s=" + _s
                + "&_m=" + _m + "&device=android" + "&version="
                + LekeConfig.REQUEST_VERSION;

        if (LekeConfig.ENVIRONMENT == LekeConfig.ApiEnvironment.online) {
            return changeHost(url);
        }else{
            return url ;
        }
    }

    /**
     * 域名转换为IP
     *
     * User: xuyuqiang
     * Data: 16/6/7 14:18
     * @param url    请求的域名URL
     * @return
     *      对应IP:String
     */
    private String changeHost(String url){

        String host = getHost(url) ;
        String requestIp = IpTableCache.getIpByDomain(host);  //此处直接调用项目中的IpTableCache.getIpByDomain(host); 进行转换
        LogUtils.logD("host is : " + host + " *** ip is : " + requestIp);
        if (TextUtils.isEmpty(requestIp)){
            return url ;
        }

        return url.replaceAll(host , requestIp) ;
    }

    /**
     *
     * User: xuyuqiang
     * Data: 16/6/3 15:50
     * @param host    原始的URL地址
     * @return
     *
     *      重新编译后的URL地址 : String
     */
    private String getHost(String host){
        try{
            URL url = new URL(host) ;
            return url.getHost() ;
        }catch (Exception e){

        }
        return "" ;
    }
}
