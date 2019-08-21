package com.yk.mvpframe.base.gson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yk.mvpframe.base.BaseException;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @FileName BitmapResponseBodyConverter
 * @Author alan
 * @Date 2019/8/19 16:53
 * @Describe TODO
 * @Mark
 **/
public class BitmapResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    BitmapResponseBodyConverter() {
    }
    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream= responseBody.byteStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return (T) bitmap;
        }
        catch (Exception e){
            throw new BaseException(BaseException.PARSE_ERROR_MSG, BaseException.PARSE_ERROR);
        }
        finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}