package com.yk.mvpframe.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.yk.mvpframe.R;
import com.yk.mvpframe.util.ToastUtils;
import com.yk.mvpframe.consts.Consts;

/**
 * @FileName BaseApplication
 * @Author alan
 * @Date 2019/7/11 10:38
 * @Describe TODO
 * @Mark
 **/
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(Consts.DEBUG){
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
        ToastUtils.init(this);
        /**
         * 初始化全局log打印配置
         */
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(2)
                .methodOffset(0)
                .tag(getString(R.string.log_name))
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override public boolean isLoggable(int priority, String tag) {
                return Consts.DEBUG;
            }
        });
    }
}
