package com.yk.mvpframe.base;

import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * @FileName BaseObserver
 * @Author alan
 * @Date 2019/7/11 11:23
 * @Describe TODO
 * @Mark
 **/
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    protected BaseView view;

    private boolean isShowDialog;

    public BaseObserver(BaseView view) {
        this.view = view;
    }

    public BaseObserver(BaseView view, boolean isShowDialog) {
        this.view = view;
        this.isShowDialog = isShowDialog;
    }

    @Override
    protected void onStart() {
        if(view!=null&&isShowDialog){
            showDialog();
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if(view!=null&&isShowDialog){
            hideDialog();
        }
        BaseException exception=null;

        if(e!=null){
            if(e instanceof BaseException){
                exception= (BaseException) e;
                if(view!=null){
                    view.onErrorCode(new BaseModel(exception.getErrorCode(),exception.getErrorMsg()));
                }
                else {
                    onError(exception.getErrorMsg());
                }
            }
            else {
                if (e instanceof HttpException) {
                    /**
                     * HTTP错误
                     */
                    exception = new BaseException(BaseException.BAD_NETWORK_MSG, e, BaseException.BAD_NETWORK);
                } else if (e instanceof ConnectException
                        || e instanceof UnknownHostException) {
                    /**
                     * 连接错误
                     */
                    exception = new BaseException(BaseException.CONNECT_ERROR_MSG, e, BaseException.CONNECT_ERROR);
                } else if (e instanceof InterruptedIOException) {
                    /**
                     * 连接超时
                     */
                    exception = new BaseException(BaseException.CONNECT_TIMEOUT_MSG, e, BaseException.CONNECT_TIMEOUT);
                } else if (e instanceof JsonParseException
                        || e instanceof JSONException
                        || e instanceof ParseException) {
                    /**
                     * 解析错误
                     */
                    exception = new BaseException(BaseException.PARSE_ERROR_MSG, e, BaseException.PARSE_ERROR);
                } else {
                    exception = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
                }
            }
        }
        else {
            exception = new BaseException(BaseException.OTHER_MSG, e, BaseException.OTHER);
        }
        onError(exception.getErrorMsg());
    }

    @Override
    public void onComplete() {
        if(view!=null&&isShowDialog){
            hideDialog();
        }
    }

    public void hideDialog(){
        view.hideLoading();
    }

    public void showDialog(){
        view.showLoading();
    }

    public abstract void onSuccess(T t);
    public abstract void onError(String msg);
}
