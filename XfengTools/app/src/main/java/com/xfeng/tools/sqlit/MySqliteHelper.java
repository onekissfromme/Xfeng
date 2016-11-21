package com.xfeng.tools.sqlit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   MySqliteHelper
 * @CreateTime:  2016/11/16 下午2:15
 * @Description: SQLite 数据库的帮助类
 */
public class MySqliteHelper extends SQLiteOpenHelper {

    private Class mClazz ;

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 构造方法
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:22
     * Version:1.0.0
     *
     * @param context     Context
     * @param db_name     数据库的名字
     * @param db_version  数据库版本号
     * @param clazz       与表对应的实体对象
     */
    public MySqliteHelper(Context context , String db_name , int db_version , Class clazz) {
        this(context , db_name , null , db_version) ;
        this.mClazz = clazz ;
    }

    /**
     * 创建表
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:21
     * Version:1.0.0
     **/
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }


    /**
     * 更新数据库的表
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:21
     * Version:1.0.0
     **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + DBUtils.getTableName(mClazz));
        createTable(db);
    }

    /**
     * 建表
     * User: xfeng
     * Date: 2016/11/17 下午1:16
     * Version:1.0.0
     **/
    private void createTable(SQLiteDatabase db){
        db.execSQL(getCreateTableSql(mClazz));
    }

    /**
     * 组装建表的sql语句
     * User: xfeng
     * Date: 2016/11/17 上午11:56
     * Version:1.0.0
     **/
    private String getCreateTableSql(Class clazz){

        StringBuilder sb = new StringBuilder() ;
        String tableName = DBUtils.getTableName(clazz) ;
        sb.append("CREATE TABLE ").append(tableName).append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ") ;
        Field[] fields = clazz.getFields() ;
        for (Field field : fields){
            String fieldName = field.getName() ;
            String fieldType = field.getType().getName() ;
            if (fieldName.equalsIgnoreCase("_id") || fieldName.equalsIgnoreCase("id")){
                continue;
            }else if ("serialVersionUID".equals(fieldName)){
                continue;
            }else{
                String columnType = DBUtils.getColumnType(fieldType) ;
                if (!TextUtils.isEmpty(columnType)) {
                    sb.append(fieldName).append(", ");
                }
            }
        }

        int len = sb.length() ;
        sb.replace(len - 2 ,len , ")") ;
        Log.i("SqliteHelper" , sb.toString()) ;
        return sb.toString() ;
    }
}
