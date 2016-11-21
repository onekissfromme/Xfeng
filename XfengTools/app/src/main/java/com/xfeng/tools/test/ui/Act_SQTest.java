package com.xfeng.tools.test.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xfeng.tools.R;
import com.xfeng.tools.sqlit.DBManager;
import com.xfeng.tools.test.Constants;
import com.xfeng.tools.test.bean.Person;

import java.util.List;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   Act_SQTest
 * @CreateTime:  2016/11/18 下午1:48
 * @Description: 数据库测试UI类
 */
public class Act_SQTest extends AppCompatActivity {

    private Context mContext ;
    private DBManager mDBManager ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sqtest);
        this.mContext = this ;
        this.mDBManager = new DBManager(mContext , Constants.DB_NAME , Constants.DB_VERSION , Person.class) ;

    }

    /**
     * 点击的处理事件
     * User: xuyuqiang
     * Date: 2016/11/18 下午3:13
     * Version:
     **/
    public void cteateDbAndTable(View view){
        switch (view.getId()){
            case R.id.act_sqtest_bt_create:
                this.mDBManager = new DBManager(mContext , Constants.DB_NAME , Constants.DB_VERSION , Person.class) ;
                Toast.makeText(this , "数据库创建成功" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.act_sqtest_bt_insert:
                Person person = new Person() ;
                person.userName = "xuyuqiang" ;
                person.userId = 100001 ;
                person.sex = 1 ;
                person.userEge = 0 ;
                long isInsert = mDBManager.insert(person) ;
                if (isInsert == -1){
                    Toast.makeText(this , "插入数据失败" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this , "插入数据成功" , Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.act_sqtest_bt_findAll:
                List<Person> personList = mDBManager.findAll(Person.class) ;

//                Toast.makeText(this , personList.get(0).userName , Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
