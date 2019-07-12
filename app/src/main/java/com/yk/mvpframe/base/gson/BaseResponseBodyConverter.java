package com.yk.mvpframe.base.gson;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.yk.mvpframe.base.BaseException;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @FileName BaseResponseBodyConverter
 * @Author alan
 * @Date 2019/7/11 9:36
 * @Describe TODO
 * @Mark
 **/
public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    BaseResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String jsonString = value.string();
        try {
            JSONObject object = new JSONObject(jsonString);
            int status = object.getInt("code");
            if (status != 0) {
                String msg = object.getString("desc");
                if (TextUtils.isEmpty(msg)) {
                    msg = object.getString("error");
                }
                //异常处理
                throw new BaseException(msg, status);
            }
            return adapter.fromJson(object.getString("content"));

        } catch (JSONException e) {
            e.printStackTrace();
            //数据解析异常
            throw new BaseException(BaseException.PARSE_ERROR_MSG, BaseException.PARSE_ERROR);
        } finally {
            value.close();
        }
    }
}
