package com.yk.mvpframe.base.gson;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @FileName BitmapConverterFactory
 * @Author alan
 * @Date 2019/8/19 16:14
 * @Describe TODO
 * @Mark
 **/
public class BitmapConverterFactory extends Converter.Factory {

    public static BitmapConverterFactory create() {
        return new BitmapConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == Bitmap.class) {
            return new BitmapResponseBodyConverter<Type>();
        }
        return null;
    }
}