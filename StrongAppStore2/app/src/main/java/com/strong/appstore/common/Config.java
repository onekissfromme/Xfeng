package com.strong.appstore.common;


/**
 * User:        xuyuqiang
 * Date:        2016/11/21 10:39
 * Viewsion:    1.0.9
 * ClassName:   Config
 * Description: AppStore 的配置信息
 **/
public class Config {

    /** 是否处于Debug状态的标识 */
    public final static boolean IS_DEBUG = true;
    /** sharepreference 的文件名字 */
    public final static String SP_FILENAME = "STRONGAPPSTORE_SP" ;
    /** 网络请求的Host */
    public static final String KEY_TUTOR_HOST = "tutor.leke.cn";
    /** 网络请求的UIL **/
    public static String HOMEWORK_NEW = "http://api.leke.cn";
    /** 网络请求的UIL **/
    public final static String HOME_WORK_NEW = HOMEWORK_NEW + "/api/w/invoke.htm";
    /** 协议版本号 */
    public final static String REQUEST_VERSION = "1.0.0";
    /** 设置为线上 */
    public static ApiEnvironment ENVIRONMENT = ApiEnvironment.online;

    public enum ApiEnvironment {
        test,
        pre,
        online
    }
}
