package com.xfeng.tools.sqlit;

import android.text.TextUtils;

import java.util.Locale;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   DBUtils
 * @CreateTime:  2016/11/17 下午1:45
 * @Description: 数据操作的工具类
 */
public class DBUtils {


    /**
     * 数据字段类型的转换,根据实体类型转换为数据字段类型
     * User: xuyuqiang
     * Date: 2016/11/17 下午1:52
     * Version:1.0.0
     **/
    public static String getColumnType(String type){
        String value = null ;
        if ("java.lang.String".equals(type)){
            value = "text" ;
        }else if ("String".equals(type)){
            value = "text" ;
        }else if ("int".equals(type)){
            value = "integer" ;
        }else if ("boolean".equals(type)){
            value = "boolean" ;
        }else if ("float".equals(type)){
            value = "float" ;
        }else if ("double".equals(type)){
            value = "double" ;
        }else if ("char".equals(type)){
            value = "varchar" ;
        }else if ("long".equals(type)){
            value = "long" ;
        }else if ("short".equals(type)){
            value = "short" ;
        }

        return value;
    }

    /**
     * 返回数据库的表名
     * 获取实体类的名字,以实体类的名字为数据库的表名
     * User: xuyuqiang
     * Date: 2016/11/17 下午1:55
     * Version:1.0.0
     **/
    public static String getTableName(Class<?> clazz){
        return clazz.getSimpleName() ;
    }

    /**
     * 把字符串的首字母改为大写
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:05
     * Version:1.0.0
     **/
    public static String capitalize(String s){
        if (!TextUtils.isEmpty(s)){
            return s.substring(0 , 1).toUpperCase(Locale.US) + s.substring(1) ;
        }
        return s == null ? null : "" ;
    }
}
