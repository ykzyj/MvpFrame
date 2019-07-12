package com.yk.mvpframe.base;

import com.yk.mvpframe.api.ApiRetrofit;
import com.yk.mvpframe.api.ApiServer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @FileName BasePresenter
 * @Author alan
 * @Date 2019/7/11 10:15
 * @Describe TODO
 * @Mark
 **/
public class BasePresenter <V extends BaseView> {
    public CompositeDisposable compositeDisposable;

    public V baseView;

    protected ApiServer apiServer= ApiRetrofit.getInstance().getApiService();

    public BasePresenter(V baseView){
        this.baseView=baseView;
    }

    public void addDisposable(Observable<?> observable,BaseObserver observer){
        if(compositeDisposable==null){
            compositeDisposable=new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer));
    }

    public void removeDisposable(){
        if(compositeDisposable!=null){
            compositeDisposable.dispose();
        }
    }

    /**
     * 解除绑定关系
     */
    public void detachView(){
        baseView=null;
        removeDisposable();
    }
}
