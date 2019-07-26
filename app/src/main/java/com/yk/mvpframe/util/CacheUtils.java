package com.yk.mvpframe.util;

import com.yk.mvpframe.model.UserInfoModel;

/**
 * @FileName CacheUtils
 * @Author alan
 * @Date 2019/7/24 17:17
 * @Describe TODO
 * @Mark
 **/
public class CacheUtils {
    private static UserInfoModel userInfoModel;
    private static String token;

    public static UserInfoModel getUserInfoModel() {
        return userInfoModel;
    }

    public static void setUserInfoModel(UserInfoModel userInfoModel) {
        CacheUtils.userInfoModel = userInfoModel;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        CacheUtils.token = token;
    }
}
