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

    /**
     * 服务器地址
     */
    public static final String AUTHENTICATION_ONLINE_HTTPS = "https://robot.andedu.net:9003/";
    public static String LOGIN_AUTHENTICATION = AUTHENTICATION_ONLINE_HTTPS+"robsso/";
    public static String ROB_AUTHENTICATION = AUTHENTICATION_ONLINE_HTTPS+"robmanager/";

    /**
     * 登录接口
     */
    public static final String Login = "app/user/phonePasswordLogin";
    /**
     * token登录接口
     */
    public static final String tokenLogin = "/app/user/tokenLogin";
    /**
     * 短信登录接口
     */
    public static final String MsgLogin = "/app/user/vCodeLogin";
}
