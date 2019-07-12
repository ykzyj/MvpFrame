package com.yk.mvpframe.api;

import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.model.UserInfoModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @FileName ApiServer
 * @Author alan
 * @Date 2019/7/11 9:06
 * @Describe TODO
 * @Mark
 **/
public interface ApiServer {
     /*@GET(Consts.Login)
    Call<UserInfoModel> login(@Query("phone") String phone, @Query("password") String password);*/

    @POST(Consts.Login)
    @FormUrlEncoded
    Observable<UserInfoModel> login(@Field("phone") String phone, @Field("password") String password);
}
