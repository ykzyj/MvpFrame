package com.yk.mvpframe.api;

import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.model.UserInfoModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * @FileName ApiServer
 * @Author alan
 * @Date 2019/7/11 9:06
 * @Describe TODO
 * @Mark
 **/
public interface ApiServer {
    @POST(Consts.LOGIN_AUTHENTICATION+Consts.Login)
    @FormUrlEncoded
    Observable<UserInfoModel> login(@Field("phone") String phone, @Field("password") String password);

    @Streaming
    @POST(Consts.ROB_AUTHENTICATION+Consts.UserHead)
    @FormUrlEncoded
    Observable<ResponseBody> getUserImg(@Field("appUserId") String appUserId);
}
