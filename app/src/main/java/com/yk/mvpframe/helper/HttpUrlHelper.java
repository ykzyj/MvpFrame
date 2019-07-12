package com.yk.mvpframe.helper;

import java.lang.reflect.Field;

import okhttp3.HttpUrl;

/**
 * @FileName HttpUrlHelper
 * @Author alan
 * @Date 2019/7/11 11:01
 * @Describe TODO
 * @Mark
 **/
public class HttpUrlHelper {
    private static final Field hostField;

    static {
        Field field = null;
        try {
            field = HttpUrl.class.getDeclaredField("host");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        hostField = field;
    }

    private final HttpUrl httpUrl;

    public HttpUrlHelper(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }

    public HttpUrl getHttpUrl() {
        return httpUrl;
    }

    public void setHost(String host){
        try {
            hostField.set(httpUrl, host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
