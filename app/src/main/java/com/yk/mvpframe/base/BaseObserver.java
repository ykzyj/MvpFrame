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
    public void onError(Throwable throwable) {
        if(view!=null&&isShowDialog){
            hideDialog();
        }
        BaseException exception=null;
        if(throwable!=null){
            exception=getExceptionType(throwable);
            if(exception==null){
                try {
                    exception=getExceptionType(throwable.getCause().getCause());
                }
                catch (Exception e){
                }
            }
        }
        if(exception==null){
            exception = new BaseException(BaseException.OTHER_MSG, null, BaseException.OTHER);
        }
        onError(exception.getErrorMsg());
    }

    protected BaseException getExceptionType(Throwable ex){
        BaseException exception=null;
        if(ex instanceof BaseException){
            exception= (BaseException) ex;
            if(view!=null){
                view.onErrorCode(new BaseModel(exception.getErrorCode(),exception.getErrorMsg()));
            }
            else {
                onError(exception.getErrorMsg());
            }
        }
        else {
            if (ex instanceof HttpException) {
                /**
                 * HTTP错误
                 */
                exception = new BaseException(BaseException.BAD_NETWORK_MSG, ex, BaseException.BAD_NETWORK);
            } else if (ex instanceof ConnectException
                    || ex instanceof UnknownHostException) {
                /**
                 * 连接错误
                 */
                exception = new BaseException(BaseException.CONNECT_ERROR_MSG, ex, BaseException.CONNECT_ERROR);
            } else if (ex instanceof InterruptedIOException) {
                /**
                 * 连接超时
                 */
                exception = new BaseException(BaseException.CONNECT_TIMEOUT_MSG, ex, BaseException.CONNECT_TIMEOUT);
            } else if (ex instanceof JsonParseException
                    || ex instanceof JSONException
                    || ex instanceof ParseException) {
                /**
                 * 解析错误
                 */
                exception = new BaseException(BaseException.PARSE_ERROR_MSG, ex, BaseException.PARSE_ERROR);
            }
        }
        return exception;
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
