package com.bryan.common.constant.user;

/**
 * ClassName: UserConstant
 * Function: 用户常量
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/3/1
 */
public class UserConstant {

    /**
     * 用户类型: 01个人
     */
    public static final String UTYPE_PERSON = "01";

    /**
     * 用户类型: 02企业
     */
    public static final String UTYPE_CORP = "02";

    /**
     * 用户用途类别:01借款用户
     */
    public static final String LOAN_PROJECT = "01";

    /**
     * 用户用途类别:02投资用户
     */
    public static final String LOAN_INVEST = "02";

    /**
     * 用户用途类别:03担保用户
     */
    public static final String LOAN_GUARANT = "03";

    /**
     * 用户状态10正常
     */
    public static final String STATE_OK = "10";

    /**
     * 用户状态20注销
     */
    public static final String STATE_CANCEL = "20";

    /**
     * 用户状态30冻结
     */
    public static final String STATE_FROZEN = "30";

    /**
     * 婚姻状况 00未知
     */
    public static final String MARRIAGE_UNKNOW = "00";

    /**
     * 婚姻状况 01已婚
     */
    public static final String MARRIAGE_YES = "01";

    /**
     * 婚姻状况 02未婚
     */
    public static final String MARRIAGE_NO = "02";

    /**
     * 婚姻状况 03离异
     */
    public static final String MARRIAGE_DIVORCE = "03";

    /**
     * 婚姻状况 04丧偶
     */
    public static final String MARRIAGE_WIDOWED = "04";

    /**
     * 有无子女 00未知
     */
    public static final String CHILD_TAG_UNKNOW = "00";

    /**
     * 有无子女 01有
     */
    public static final String CHILD_TAG_YES = "01";

    /**
     * 有无子女 02无
     */
    public static final String CHILD_TAG_NO = "02";

    /**
     * 工作状况 00未知
     */
    public static final String WORK_TAG_UNKNOW = "00";

    /**
     * 工作状况 01全职
     */
    public static final String WORK_TAG_FULL_TIME = "01";

    /**
     * 工作状况 02兼职
     */
    public static final String WORK_TAG_PART_TIME = "02";

    /**
     * 工作状况 03无
     */
    public static final String WORK_TAG_NO = "03";

    /**
     * 有无房产 00未知
     */
    public static final String HOUSE_TAG_UNKNOW = "00";

    /**
     * 有无房产 01有
     */
    public static final String HOUSE_TAG_YES = "01";

    /**
     * 有无房产 02无
     */
    public static final String HOUSE_TAG_NO = "02";

    /**
     * 01普通注册
     */
    public static final String REG_SOURCE_NORMAL = "01";

    /**
     * 02邀请注册
     */
    public static final String REG_SOURCE_INVITE = "02";

    /**
     * 03渠道注册
     */
    public static final String REG_SOURCE_CHANNEL = "03";

    /**
     * 04后台录入
     */
    public static final String REG_SOURCE_INPUT = "04";

    /**
     * 前一天注册
     */
    public static final Integer REGISTER_BEFORE_ONE_DAY = -1;

}
