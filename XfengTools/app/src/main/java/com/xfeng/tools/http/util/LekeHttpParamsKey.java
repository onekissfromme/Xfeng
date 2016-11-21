package com.xfeng.tools.http.util;

/**
 * Descripthion:    存放LekeHttp请求参数的KEY
 * User:            xuyuqiang
 * <p>
 * Date:            2016-06-12 16:35
 * <p>
 * Version:         V 2.7.0
 */

public class LekeHttpParamsKey {

    /************************** 通用字段 *******************************/
    /** 名字 */
    public static final String NAME = "name";
    /** 标题 */
    public static final String TITLE = "title";
    /** 月份 */
    public static final String MONTH = "month" ;
    /** 类型 */
    public static final String TYPE = "type" ;
    /** 开始记录数 */
    public static final String START = "start" ;
    /** 需要获取的记录数 */
    public static final String LIMIT = "limit" ;
    /** 开始时间 */
    public static final String STARTTIME = "startTime" ;
    /** 截止时间 */
    public static final String ENDTIME = "endTime" ;
    /** 查询时间 */
    public static final String CURRTIME = "currTime" ;

    /************************作业网络请求的KEY *************************/
    /** 作业ID **/
    public static final String HOMEWORKID = "homeworkId" ;
    /** 排序规则 {0 或 null :默认排序(订正时间 升序)， 1：成绩按倒序排} **/
    public static final String SORT = "sort" ;
    /** 学生作业ID */
    public static final String HOMEWORKDTLID = "homeworkDtlId" ;
    /** 作业信息 */
    public static final String QUESITIONRESULT = "questionResult" ;
    /** 题目ID */
    public static final String QUESTIONID = "questionId" ;
    /** 题目ID 集合 */
    public static final String QUESTIONIDS = "questionIds" ;
    /** 作业截止时间 */
    public static final String CURDATE = "curDate" ;
    /** 老师评语 */
    public static final String  COMMENTARY = "commentary" ;
    /** 复批试题信息 */
    public static final String CORRECTJSON = "correctJson" ;
    /** 试卷ID数组 */
    public static final String PAPERIDS = "paperIds" ;
    /** 学生ID */
    public static final String STUDENTID = "studentId" ;
    /** 作业点评语音文件路径，格式化文本 */
    public static final String SOUNDFILE = "soundFile" ;
    /** 批改结果信息列表 */
    public static final String QUESTIONRESULTLIST = "questionResultList" ;
    /** 作业类型 1：预习作业，2：随堂作业 3：课后作业 5：点播作业 */
    public static final String HOMEWORKTYPE = "homeworkType" ;
    /** 标记学生作业id ，null时，查询最新的 limit条数数据，翻页时，flagHomeworkId = 把上一页数据中的最后一条数据的homeworkDtlId */
    public static final String FLAGHOMEWORKID = "flagHomeworkId" ;

    /************************用户信息(登录,更新) *************************/
    /** 用户名 */
    public static final String LOGINNAME = "loginName" ;
    /** 登录密码 */
    public static final String PASSWORD = "password" ;
    /** 老师的真是姓名 */
    public static final String USERNAME = "teacherName" ;
    /** 老师性别 */
    public static final String USERSEX = "sex" ;
    /** 老师ID */
    public static final String TEACHERID = "teacherId" ;

    /************************ 课程网络请求的KEY *************************/
    /** 班级ID */
    public static final String COURSEID = "courseId" ;
    /************************ 备课资源请求 *************************/
    /** 用户Id */
    public static final String USERID = "userId";
    /** 学校Id */
    public static final String SCHOOLID = "schoolId";
    /** 分享范围 */
    public static final String SHARESCOPE = "shareScope";
    /** 当前页 */
    public static final String CURPAGE = "curPage";
    /** 每页长度 */
    public static final String PAGESIZE = "pageSize";
    /** 请求状态 */
    public static final String STATUS = "status";
    /** 课程标号 */
    public static final String COURSESINGLEID = "courseSingleId";
    /** 绑定类型 */
    public static final String BEIKEPHASE = "beikePhase";
    /** 资源类型 */
    public static final String RESOURCETYPE = "resourceType";
    /** 绑定数组 */
    public static final String ITEMLIST = "itemList";
    /** 搜索字段 */
    public static final String HOMEWORKNAME = "homeworkName";
    /** 角色ID */
    public static final String ROLEID = "roleId" ;

}
