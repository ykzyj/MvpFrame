package com.yk.mvpframe.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;

/**
 * @FileName BitmapObserver
 * @Author alan
 * @Date 2019/8/15 17:34
 * @Describe TODO
 * @Mark
 **/
public abstract class BitmapObserver<T> extends BaseObserver<ResponseBody> {

    public BitmapObserver(BaseView view) {
        super(view);
    }

    public BitmapObserver(BaseView view, boolean isShowDialog) {
        super(view, isShowDialog);
    }

    @Override
    public void onNext(ResponseBody responseBody)  {
        InputStream inputStream = null;
        try {
            inputStream= responseBody.byteStream();
            Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
            onSuccess(bitmap);
        }
        catch (Exception e){
            onError("文件转换失败");
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

    public abstract void onSuccess(Bitmap bitmap);

    @Override
    public void onSuccess(ResponseBody responseBody) {

    }
}
