package com.yk.mvpframe.api;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.orhanobut.logger.Logger;
import com.yk.mvpframe.base.gson.BaseConverterFactory;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.helper.HttpUrlHelper;
import com.yk.mvpframe.util.CacheUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @FileName ApiRetrofit
 * @Author alan
 * @Date 2019/7/10 17:48
 * @Describe 初始化Retrofit
 * @Mark
 **/
public class ApiRetrofit {
    private static ApiRetrofit apiRetrofit;
    private Retrofit retrofit;
    private OkHttpClient client;
    private ApiServer apiServer;
    /**
     * 用于动态切换base地址
     */
    private HttpUrlHelper httpUrlHelper;

    /**
     * 请求访问quest
     * response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original  = chain.request();
            Request.Builder requestBuilder = original .newBuilder()
                    .header("token", TextUtils.isEmpty(CacheUtils.getToken())?"":CacheUtils.getToken());
            Request request = requestBuilder.build();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(request);
            String token=response.header("token");
            if(!TextUtils.isEmpty(token)){
                CacheUtils.setToken(token);
                SPUtils spUtils=SPUtils.getInstance();
                spUtils.put("token",token);
            }
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Logger.e("----------Request Start----------------");
            Logger.e("| " + request.toString() + request.headers().toString());
            Logger.e("| Response:" + content);
            Logger.e("----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };


    public ApiRetrofit() {
        client = new OkHttpClient.Builder()
                //添加log拦截器
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        httpUrlHelper=new HttpUrlHelper(HttpUrl.get(Consts.LOGIN_AUTHENTICATION));
        retrofit = new Retrofit.Builder()
                .baseUrl(httpUrlHelper.getHttpUrl())
                .addConverterFactory(BaseConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    /**
     *
     * @param host 不能包括协议头（http://或https://），只替换base地址
     */
    public void setHttpBaseUrl(String host) {
        this.httpUrlHelper.setHost(host);
    }

    /**
     * 单例模式，获取Retrofit对象
     * @return
     */
    public static ApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }
}
