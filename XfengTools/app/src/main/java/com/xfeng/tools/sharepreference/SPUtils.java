package com.xfeng.tools.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * @User: xuyuqiang
 * @Version: 1.0.0
 * @ClassName: SPUtils
 * @CreateTime: 2016/11/20 下午5:10
 * @Description: android SharePreference 工具使用类
 */
public class SPUtils {

    private SharedPreferences spf;
    private SharedPreferences.Editor editor;

    private SPUtils(Context context, String fileName) {
        spf = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = spf.edit();
    }


    /**
     * 添加数据,类型自动区别
     * User: xuyuqiang
     * Date: 2016/11/20 下午10:00
     * Version:1.0.0
     **/
    public void put(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }

        editor.commit() ;
    }

    /**
     * ShareFreference 取数据
     * User: xuyuqiang
     * Date: 2016/11/20 下午10:04
     * Version:1.0.0
     **/
    public Object get(String key , Object defValue){

        if (defValue instanceof String)
        {
            return spf.getString(key, (String) defValue);
        } else if (defValue instanceof Integer)
        {
            return spf.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean)
        {
            return spf.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float)
        {
            return spf.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long)
        {
            return spf.getLong(key, (Long) defValue);
        }

        return defValue ;
    }

    /**
     * 根据Key删除值
     * User: xuyuqiang
     * Date: 2016/11/20 下午10:08
     * Version:1.0.0
     **/
    public void removeByKey(String key){
        editor.remove(key) ;
        editor.commit() ;
    }

    /**
     * 清除所有数据
     * User: xuyuqiang
     * Date: 2016/11/20 下午10:11
     * Version:1.0.0
     **/
    public void clearAll(){
        editor.clear() ;
        editor.commit() ;
    }

    /**
     * 查询Key是否存在
     * User: xuyuqiang
     * Date: 2016/11/20 下午10:16
     * Version:1.0.0
     **/
    public boolean containsByKey(String key){
        return spf.contains(key) ;
    }

    /**
     * 获取所有的键值对
     * User: xuyuqiang
     * Date: 2016/11/20 下午10:18
     * Version:1.0.0
     **/
    public Map<String , ?> getAll(){
        return spf.getAll() ;
    }
}
