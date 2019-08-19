package com.yk.mvpframe.activity.mine.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.yk.mvpframe.activity.mine.view.SystemSettingView;
import com.yk.mvpframe.base.BasePresenter;
import com.yk.mvpframe.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * @FileName SystemSettingPresenter
 * @Author alan
 * @Date 2019/8/13 16:37
 * @Describe TODO
 * @Mark
 **/
public class SystemSettingPresenter extends BasePresenter<SystemSettingView> {

    public SystemSettingPresenter(SystemSettingView baseView) {
        super(baseView);
    }

    public void getAppUpdate(){
        String currentVersionName=AppUtils.getAppVersionName();
        int currentVersionCode=AppUtils.getAppVersionCode();
        baseView.showAppCurrentVersionName("V"+currentVersionName);
        baseView.showAppNewVersionName("V1.0.1");
    }

    public void loginOut(){
        EventBus.getDefault().post(new LoginEvent(LoginEvent.LOGINOUT));
    }

}
