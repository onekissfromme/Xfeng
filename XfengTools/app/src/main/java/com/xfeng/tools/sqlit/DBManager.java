package com.xfeng.tools.sqlit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   DBManager
 * @CreateTime:  2016/11/17 下午2:27
 * @Description: 数据库操作封装类
 */
public class DBManager {
    private static final String TAG = "DBManager" ;

    private SQLiteDatabase db;
    private MySqliteHelper mHelper ;
    private Context mContext ;
    private String db_name ;

    /**
     * 构造方法
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:32
     * Version:
     *
     * @param context       Context
     * @param db_name       数据库的名字
     * @param db_version    数据库版本
     * @param clazz         表对应的实体类
     */
    public DBManager(Context context , String db_name , int db_version , Class<?> clazz){
        this.mContext = context ;
        this.db_name = db_name ;
        mHelper = new MySqliteHelper(mContext , db_name , db_version , clazz) ;
        db = mHelper.getWritableDatabase() ;
    }

    /**
     * 关闭数据库
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:54
     * Version:1.0.0
     **/
    public void onCloseDataBase(){
        db.close();
        mHelper = null ;
        db = null ;
    }

    /**
     * 删除数据库
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:55
     * Version:1.0.0
     **/
    public boolean delDataBase(){
        return mContext.deleteDatabase(db_name) ;
    }

    /**
     * 插入一条数据
     * User: xuyuqiang
     * Date: 2016/11/17 下午2:57
     * Version:1.0.0
     *
     * @param obj    需要插入的数据
     * @return      返回-1 插入失败 , 否则插入成功
     */
    public long insert(Object obj){
        Class<?> clazz = obj.getClass() ;
        Field [] fields = clazz.getFields() ;
        ContentValues values = new ContentValues() ;

        for (Field fd : fields){
            fd.setAccessible(true); //强制反射
            String fieldName = fd.getName() ;
            if (fieldName.equalsIgnoreCase("id") || fieldName.equalsIgnoreCase("_id")){
                continue;
            }else if ("serialVersionUID".equals(fieldName)){
                continue;
            }
            putValues(values , fd , obj);
        }
        return db.insert(DBUtils.getTableName(clazz) , null , values) ;
    }

    /**
     * 查询数据库中的所有数据
     * User: xuyuqiang
     * Date: 2016/11/17 下午3:51
     * Version:1.0.0
     *
     * @param clazz   返回的对象类
     * @return        所有数据对象的集合
     */
    public <T> List<T> findAll(Class<T> clazz){
        Cursor cursor = db.query(clazz.getSimpleName() , null , null , null, null ,null ,null) ;
        return getEntitys(cursor , clazz) ;
    }

    /**
     * 根据指定条件查询相关数据
     * User: xuyuqiang
     * Date: 2016/11/17 下午7:33
     * Version:1.0.0
     *
     *
     * @param clazz         查询的数据类型
     * @param select        查询条件
     * @param selectArgs    条件参数
     * @return              数据集合
     */
    public <T> List<T> findByArgs(Class<T> clazz , String select , String [] selectArgs){
        Cursor cursor = db.query(clazz.getSimpleName() , null , select , selectArgs , null ,null ,null) ;
        return getEntitys(cursor , clazz) ;
    }

    /**
     * 根据 id 查询相关数据
     * User: xuyuqiang
     * Date: 2016/11/18 上午10:23
     * Version:1.0.0
     *
     *
     * @param clazz    返回数据类
     * @param id        查询id
     * @return
     *
     */
    public <T> List<T> findById(Class<T> clazz , int id){
        Cursor cursor = db.query(clazz.getSimpleName() , null , "id=" + id , null ,null ,null ,null) ;
        return  getEntitys(cursor , clazz) ;
    }

    /**
     * 根据ID删除某一条记录
     * User: xuyuqiang
     * Date: 2016/11/18 上午10:27
     * Version:1.0.0
     *
     *
     * @param clazz    表名类
     * @param id       条件id
     */
    public void deleteByid(Class<?> clazz , long id){
        db.delete(clazz.getSimpleName() , "id=" + id , null) ;
    }

    /**
     * 根据条件删除数据
     * User: xuyuqiang
     * Date: 2016/11/18 上午11:47
     * Version:1.0.0
     *
     *
     * @param clazz             表名类
     * @param whereClause       删除条件
     * @param whereArgs         条件值
     */
    public void deleteByArgs(Class<?> clazz , String whereClause , String [] whereArgs){
        db.delete(clazz.getSimpleName() , whereClause , whereArgs) ;
    }

    /**
     * 删除某一个表
     * User: xuyuqiang
     * Date: 2016/11/18 上午10:31
     * Version:1.0.0
     *
     * @param clazz   表名类
     */
    public void deleteTable(Class<?> clazz){
        db.execSQL("DROP TABLE IF EXISTS " + clazz.getSimpleName());
    }


    /**
     * 根据ID跟新某一条数据
     * User: xuyuqiang
     * Date: 2016/11/18 上午11:50
     * Version:1.0.0
     **/
    public void updateByid(Class<?> clazz , ContentValues values ,  long id){
        db.update(clazz.getSimpleName() , values , "id=" + id , null) ;
    }

    /**
     * 跟定条件进行数据更新
     * User: xuyuqiang
     * Date: 2016/11/18 上午11:52
     * Version:1.0.0
     **/
    public void updateByArgs(Class<?> clazz , ContentValues values , String whereClause , String [] whereArgs){
        db.update(clazz.getSimpleName() , values , whereClause , whereArgs) ;
    }

    /**
     * 反射参数到数据库操作容器中:ContentValues
     * User: xuyuqiang
     * Date: 2016/11/17 下午3:46
     * Version:1.0.0
     **/
    private void putValues(ContentValues values , Field fd , Object obj){
        Class<?> clazz = values.getClass() ;
        try {
            Object[] parameters = new Object[]{fd.getName(), fd.get(obj)};
            Class<?> [] parameterTypes = getParameterTypes(fd , fd.get(obj) , parameters) ;
            Method method = clazz.getDeclaredMethod("put" , parameterTypes) ;
            method.setAccessible(true);
            method.invoke(values , parameters) ;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 得到反射方法中的参数类型
     * User: xuyuqiang
     * Date: 2016/11/17 下午3:33
     * Version:1.0.0
     *
     *
     * @param fd            Field
     * @param fieldValue    参数值
     * @param parameters
     * @return
     */
    private Class<?> [] getParameterTypes(Field fd , Object fieldValue , Object[] parameters){
        Class<?>[] parametyperTypes ;
        if (isCharType(fd)){
            parameters[1] = String.valueOf(fieldValue) ;
            parametyperTypes = new Class<?>[]{String.class , String.class} ;
        }else{
            if (fd.getType().isPrimitive()){
                parametyperTypes = new Class[]{String.class , getObjectType(fd.getType())} ;
            }else if ("java.util.Date".equals(fd.getType().getName())){
                parametyperTypes = new Class[]{String.class , Long.class} ;
            }else{
                parametyperTypes = new Class[]{String.class , fd.getType()} ;
            }
        }

        return parametyperTypes ;
    }

    /**
     * 判断是否是字符类型
     * User: xuyuqiang
     * Date: 2016/11/17 下午3:16
     * Version:1.0.0
     *
     * @param fd    Field
     * @return
     */
    private boolean isCharType(Field fd){
        String type = fd.getType().getName() ;
        return type.equals("char") || type.endsWith("Character") ;
    }

    /**
     * 获取对象的类型
     * User: xuyuqiang
     * Date: 2016/11/17 下午3:29
     * Version:1.0.0
     *
     * @param primitiveType  参数对象
     * @return   类型的对象
     */
    private Class<?> getObjectType(Class<?> primitiveType){
        if (primitiveType != null){
            String basicTypeName = primitiveType.getName() ;
            if ("int".equals(basicTypeName)){
                return Integer.class ;
            }else if ("short".equals(basicTypeName)){
                return Short.class ;
            }else if ("long".equals(basicTypeName)){
                return Long.class ;
            }else if ("float".equals(basicTypeName)){
                return Float.class ;
            }else if ("double".equals(basicTypeName)){
                return Double.class ;
            }else if ("boolean".equals(basicTypeName)){
                return Boolean.class ;
            }else if ("char".equals(basicTypeName)){
                return Character.class ;
            }
        }

        return null ;
    }

    /**
     * 组装数据,拿到cursor后对数据进行解析组装
     * User: xuyuqiang
     * Date: 2016/11/17 下午7:29
     * Version:1.0.0
     *
     *
     * @param cursor    数据库游标
     * @param clazz     组装的数据类
     * @return          数据集合
     */
    private <T> List<T> getEntitys(Cursor cursor , Class<T> clazz){
        List<T> dataList = new ArrayList<T>() ;
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    //获取对象的所有参数
                    Field [] fields = clazz.getFields() ;
                    //构造对象
                    T modeClass = clazz.newInstance() ;
                    for (Field fd : fields){
                        //如果是序列化自动生成的,自动过滤掉
                        if ("serialVersionUID".equals(fd.getName())){
                            continue;
                        }
                        Class<?> cursorClass = cursor.getClass() ;
                        String columnMethodName = getColumnMethodName(fd.getType()) ;
                        //如果不是基本类型,过滤掉
                        if (TextUtils.isEmpty(columnMethodName)){
                            continue;
                        }
                        Method cursorMethod = cursorClass.getMethod(columnMethodName , int.class) ;
                        Object value = cursorMethod.invoke(cursor , cursor.getColumnIndex(fd.getName())) ;

                        if (fd.getType() == boolean.class || fd.getType() == Boolean.class){
                            if ("0".equals(value)){
                                value = false ;
                            }else if ("1".equals(value)){
                                value = true ;
                            }
                        }else if (fd.getType() == char.class || fd.getType() == Character.class){
                            value = ((String)value).charAt(0) ;
                        }else if(fd.getType() == Date.class){
                            long date = (long) value;
                            if (date <= 0){
                                value = null ;
                            }else{
                                value = new Date(date) ;
                            }
                        }
                        fd.setAccessible(true);
                        fd.set(modeClass , value);

//                        String methodName = makeSetterMethodName(fd) ;
//                        Method method = clazz.getDeclaredMethod(methodName , fd.getType()) ;
//                        method.invoke(modeClass , value) ;
                    }
                    dataList.add(modeClass);
                }while (cursor.moveToNext()) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
        return dataList ;
    }

    /**
     * 获取数据中的字段类型
     * User: xuyuqiang
     * Date: 2016/11/17 下午4:51
     * Version:1.0.0
     *
     *
     * @param fieldType  需要验证的参数类型
     * @return 返回cursor获取参数的方法
     */
    private String getColumnMethodName(Class<?> fieldType) {
        String typeName = null;
        //如果是基本类型
        if (fieldType.isPrimitive()) {
            typeName = DBUtils.capitalize(fieldType.getName());
            String methodName = "get" + typeName;
            if ("getBoolean".equals(methodName)) {
                methodName = "getInt";
            } else if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
                methodName = "getString";
            } else if ("getDate".equals(methodName)) {
                methodName = "getLong";
            } else if ("getInteger".equals(methodName)) {
                methodName = "getInt";
            }
            return methodName;
        }

        // TODO: 2016/11/19  如果不是基本类型暂不处理
//        else {
//            typeName = fieldType.getSimpleName();
//        }

        return "" ;

    }


    /**
     * 正则匹配方法名字
     * User: xuyuqiang
     * Date: 2016/11/17 下午7:26
     * Version:1.0.0
     **/
    private String makeSetterMethodName(Field field) {
        String setterMethodName;
        String setterMethodPrefix = "set";
        if (isPrimitiveBooleanType(field) && field.getName().matches("^is[A-Z]{1}.*$")) {
            setterMethodName = setterMethodPrefix + field.getName().substring(2);
        } else if (field.getName().matches("^[a-z]{1}[A-Z]{1}.*")) {
            setterMethodName = setterMethodPrefix + field.getName();
        } else {
            setterMethodName = setterMethodPrefix + DBUtils.capitalize(field.getName());
        }
        return setterMethodName;
    }

    /**
     * 判断类型是否是boolean类型
     * User: xuyuqiang
     * Date: 2016/11/17 下午7:25
     * Version:1.0.0
     *
     * @param field     检测属性
     * @return          Boolean
     */
    private boolean isPrimitiveBooleanType(Field field){
        Class<?> fieldType = field.getType() ;
        if ("boolean".equals(fieldType.getName())){
            return true ;
        }
        return false ;
    }
}
