package com.yk.mvpframe.consts;

public class Consts {

    /**
     * 设置当前app是否打印log信息
     */
    public static final boolean DEBUG = true;

    /**
     * 静态变量
     */
    public static final String BUNDLE_KEY_TAB_TITLE="key_tab_title";
    public static final String BUNDLE_KEY_TAB_POSITION="key_tab_position";

    /**
     * 服务器地址
     */
    public static final String AGREEMENT_HTTPS = "https://";
    public static final String AUTHENTICATION_ONLINE = "robot.andedu.net:9003/";
    public static final String LOGIN_AUTHENTICATION = "robsso/";
    public static final String ROB_AUTHENTICATION = "robmanager/";

    /**
     * 登录接口
     */
    public static final String Login = "app/user/phonePasswordLogin";
    /**
     * token登录接口
     */
    public static final String TokenLogin = "app/user/tokenLogin";
    /**
     * 短信登录接口
     */
    public static final String MsgLogin = "app/user/vCodeLogin";
    /**
     * 获取用户头像
     */
    public static final String UserHead="app/appUser/downLoadAppUserPhoto";
}
