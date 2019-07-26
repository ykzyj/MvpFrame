package com.yk.mvpframe.api;

import com.yk.mvpframe.model.UserInfoModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;

/**
 * @FileName ApiProviders
 * @Author alan
 * @Date 2019/7/23 16:02
 * @Describe TODO
 * @Mark
 **/
public interface ApiProviders {
    @LifeCache(duration = 10,timeUnit = TimeUnit.MINUTES)
    @ProviderKey("user-evict-provider")
    Observable<UserInfoModel> getUserInfoEvictProvider(Observable<UserInfoModel> userInfo, EvictProvider evictProvider);
}
