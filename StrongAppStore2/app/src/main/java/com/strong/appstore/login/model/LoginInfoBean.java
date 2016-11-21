package com.strong.appstore.login.model;

import com.strong.appstore.http.BaseResponseBean;

import java.util.List;

/**
 * User:        xuyuqiang
 * Date:        2016/11/21 16:59
 * Viewsion:    1.0.9
 * ClassName:   LoginInfoBean
 * Description: 用户登录的实体bean
 **/
public class LoginInfoBean extends BaseResponseBean {
    //用户ID
    public long id ;
    //用户昵称
    public String nick ;
    //登录名
    public String loginName ;
    //登录密码
    public String password ;
    //是否启用
    public boolean isEnabled ;
    //学生当前角色的判定信息
    public CurrentRole currentRole ;
    //学生当前的学校信息
    public CurrentSchool currentSchool ;

    /**
     * User:        xuyuqiang
     * Date:        2016/11/21 17:06
     * Viewsion:    1.0.9
     * ClassName:   CurrentSchool
     * Description: 当前学校信息
     **/
    public class CurrentSchool {
        //学校id
        long id;
        //学校名称
        String name;

        List<Long> schoolStageIds;
    }

    //用户角色判定
    public class CurrentRole{
        //判定ID：100：学生 其他：非100
        public int id;
        public String name ;
    }

}
