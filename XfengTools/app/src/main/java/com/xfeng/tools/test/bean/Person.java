package com.xfeng.tools.test.bean;

/**
 * @User:        xuyuqiang
 * @Version:     1.0.0
 * @ClassName:   Person
 * @CreateTime:  2016/11/18 下午1:45
 * @Description: 测试实体类
 */
public class Person {

    public long userId ;
    public String userName ;
    public short userEge ;
    public short sex ;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEge(short userEge) {
        this.userEge = userEge;
    }

    public void setSex(short sex) {
        this.sex = sex;
    }
}
