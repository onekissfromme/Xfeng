package com.xfeng.tools.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xfeng.tools.R;
import com.xfeng.tools.test.ui.Act_SQTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext ;

    private Button bt_testSql ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this ;
        initView();
    }

    private void initView(){
        bt_testSql = (Button) findViewById(R.id.activity_main_bt_testSql) ;
        bt_testSql.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_bt_testSql:
                Intent intent = new Intent(mContext , Act_SQTest.class) ;
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
