package com.strong.appstore.http.util;

/**
 * Descripthion:    存放网络请求的常量
 * User:            xuyuqiang
 * <p>
 * Date:            2016-06-07 14:21
 * <p>
 * Version:         V 2.7.0
 */

public class LekeHttpContants {

    public static final String LOGINTICKET = "VDBSVlBRPT07Wm5kbVlIYz07ODU4NQ==" ;

    /*********************************************登录接口 ***************************************/
    /** 登录服务接口标识 (_s) */
    public static final String LOGIN_S = "cas" ;
    /** 用户登录*/
    public static final String GetLoginInfo_M = "getLoginInfo" ;
    /******************************************* 获取课堂信息接口 **********************************/
    /** 获取课堂信息的服务接口标识 */
    public static final String LESSON_S = "lesson" ;
    /** 老师是否能上课的校验,检查老师上课点是否足够,不足返回false , 否则返回true */
    public static final String CHECKLESSON_M = "checkLesson" ;
    /** 获取课程列表信息 */
    public static final String GetCourseForTeacher = "getCourseForTeacher" ;
    /** 获取某个班级的课程信息 */
    public static final String GetCourseDetailForTeacher_M = "getCourseDetailForTeacher" ;
    /** 获取某月的课程 */
    public static final String GetCourseSingleByMonthForTeacher_M = "getCourseSingleByMonthForTeacher" ;
    /** 课程数量 */
    public static final String FINDSCHEDULECOUNTORTEACHER_M = "findScheduleCountForTeacher" ;
    /** 老师某日但课表查询 */
    public static final String FINDSCHEDULELISTFORTEACHER_M = "findScheduleListForTeacher" ;

    /******************************************* 操作老师信息接口 **********************************/
    public static final String TUTOR_S = "tutor" ;
    /** 更新老师信息 */
    public static final String UpdateUserInfoForTeacher_M = "updateUserInfoForTeacher" ;
    /** 获取用户信息 */
    public static final String GETUSERBASEINFO_M  = "getUserBaseInfo" ;
    /** 更新用户信息 */
    public static final String UPDATEUSERBASEINFO_M = "updateUserBaseInfo" ;


    /*********************************************作业接口 ***************************************/
    /**
     * 服务接口标识
     * 作业业务逻辑
     * (_S)
     */
    public static final String HOMEWORK_S = "homework" ;
    /**获取订正信息 , 学生答案*/
    public static final String GetHwDtlInfo_M = "getHwDtlInfo" ;
    /** 作业上交情况 */
    public static final String GetHwDtl_M = "getHwDtl" ;
    /**复批作业全部通过*/
    public static final String SaveReviewSubmitWithBatch_M = "saveReviewSubmitWithBatch" ;
    /** 获取复批学生列表 */
    public static final String GetReviewHwStuInfo_M = "getReviewHwStuInfo" ;
    /** 批量批改保存接口 */
    public static final String SaveBatchQuestion_M = "saveBatchQuestion" ;
    /** 获取批量批改下一个的数据 */
    public static final String GetNextStuCorrectQuestion_M = "getNextStuCorrectQuestion" ;
    /** 获取需要批改的作业（按题）*/
    public static final String GetBatchQuestions_M = "getBatchQuestions" ;
    /** 按题批改获取当前学生集合*/
    public static final String GetBatchSubmitHwDtls_M = "getBatchSubmitHwDtls";
    /** 新的按题批改推送答案，根据homeworkDtlId*/
    public static final String GetBatchSingleQuestion_M = "getBatchSingleQuestion";
    /** 获取需要批改的作业（按人）*/
    public static final String GetHomeworkStuInfo_M = "getHomeworkStuInfo" ;
    /** 作业复批提交 */
    public static final String SaveReviewWork_M = "saveReviewWork" ;
    /** 获取每月作业数量 */
    public static final String GetHwByMonth_M = "getHwByMonth" ;
    /** 批改结果回传 */
    public static final String UPCORRECTRESULT_M = "upCorrectResult" ;
    /** 获取试卷列表信息 */
    public static final String GETPAPERS_M = "getPapers" ;
    /** 获取试卷详情 */
    public static final String GetPaperInfo_M = "getPaperInfo" ;
    /** 查询作业信息 */
    public static final String GetStuHomework_M = "getStuHomework" ;
    /** 获取题目数据 */
    public static final String GETQUESTIONS_M = "getQuestions" ;
    /** 作业作废 */
    public static final String SAVEHOMEWORKINVALID_M = "saveHomeworkInvalid" ;
    /** 老师日作业数量 */
    public static final String FINDHOMEWORKCOUNTFORTEACHER_M = "findHomeworkCountForTeacher" ;
    /** 老师日作业列表 */
    public static final String FINDHOMEWORKLISTFORTEACHER_M = "findHomeworkListForTeacher" ;
    /** 老师作业以及查询列表(指定类型时间倒叙) */
    public static final String FINDHOMEWORKLISTFORTEACHER_V1_M = "findHomeworkListForTeacher_v1" ;
    /** 作业自行校对 */
    public static final String SAVESELFCHECK_M = "saveSelfCheck" ;
    /** 作业公布答案 */
    public static final String SAVEOPENANWSER_M = "saveOpenAnwser" ;

    /******************************************** 备课资源 ***************************************/
    /** 获取备课资源的服务接口标识 */
    public static final String Beike_S = "beike";
    /** 获取试卷资源的服务接口标识 */
    public static final String Paper_S = "paper";
    /** 课件资源数据接口 */
    public static final String FindTeacherCourseware_M = "findTeachCourseware";
    /** 试卷资源数据接口 */
    public static final String FindTeacherPaper_M = "findTeachPaper";
    /** 作业资源数据接口 */
    public static final String GetHwByLessonId_M = "getHwByLessonId";
    /** 绑定课件接口 */
    public static final String SaveBeike_M = "saveBeike";

    /****************************************** 用户角色信息 **************************************/
    /** 获取角色信息的服务接口 */
    public static final String INCENTIVE_S = "incentive" ;
    /** 用户签到 */
    public static final String EXECUTEUSERSIGN_M = "executeUserSign" ;
    /** 获取乐币详情 */
    public static final String FINDLEKELIST_M = "findLekeList" ;
    /** 获取用户扩展信息 */
    public static final String GETUSEREXTRAINFO_M = "getUserExtraInfo" ;


}
