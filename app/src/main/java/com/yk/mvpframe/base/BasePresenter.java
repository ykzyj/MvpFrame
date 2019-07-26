package com.yk.mvpframe.base;

import com.yk.mvpframe.api.ApiRepository;
import com.yk.mvpframe.api.ApiRetrofit;
import com.yk.mvpframe.api.ApiServer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @FileName BasePresenter
 * @Author alan
 * @Date 2019/7/11 10:15
 * @Describe TODO
 * @Mark
 **/
public class BasePresenter <V extends BaseView> {
    public CompositeDisposable mNetCompositeDisposable;
    public CompositeDisposable mViewCompositeDisposable;

    public V baseView;

    protected ApiServer apiServer;
    protected ApiRepository apiRepository;

    public BasePresenter(V baseView){
        this.baseView=baseView;
        apiServer= ApiRetrofit.getInstance().getApiService();
        apiRepository=new ApiRepository(apiServer);
    }

    /**
     * 添加订阅
     */
    public void addDisposable(Observable<?> observable,BaseObserver observer){
        if(mNetCompositeDisposable==null){
            mNetCompositeDisposable=new CompositeDisposable();
        }
        mNetCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer));
    }

    /**
     * 添加订阅
     */
    public void addDisposable(Disposable mDisposable) {
        if (mViewCompositeDisposable == null) {
            mViewCompositeDisposable = new CompositeDisposable();
        }
        mViewCompositeDisposable.add(mDisposable);
    }
    /**
     * 取消所有订阅
     */
    public void clearDisposable() {
        if (mViewCompositeDisposable != null) {
            mViewCompositeDisposable.clear();
        }
        if(mNetCompositeDisposable!=null){
            mNetCompositeDisposable.dispose();
        }
    }

    /**
     * 解除绑定关系
     */
    public void detachView(){
        baseView=null;
        clearDisposable();
    }
}
