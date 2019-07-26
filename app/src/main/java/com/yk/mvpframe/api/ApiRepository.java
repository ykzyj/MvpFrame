package com.yk.mvpframe.api;

import com.yk.mvpframe.base.BaseApplication;
import com.yk.mvpframe.model.UserInfoModel;
import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * @FileName ApiRepository
 * @Author alan
 * @Date 2019/7/23 15:18
 * @Describe TODO
 * @Mark
 **/
public class ApiRepository {
    private ApiProviders mProviders;
    private ApiServer mApiServer;

    public ApiRepository(ApiServer apiServer) {
        mProviders = new RxCache.Builder()
                .persistence(BaseApplication.getApplication().getCacheDir(), new GsonSpeaker())
                .using(ApiProviders.class);
        mApiServer=apiServer;
    }

    public Observable<UserInfoModel> login(String phone,String password,final boolean update) {
        return mProviders.getUserInfoEvictProvider(mApiServer.login(phone,password), new EvictProvider(update));
    }
}
