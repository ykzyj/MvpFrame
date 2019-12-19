package com.yk.mvpframe.activity.mine.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.yk.mvpframe.activity.mine.view.AboutOurView;
import com.yk.mvpframe.base.BasePresenter;

/**
 * @FileName AboutOurPresenter
 * @Author alan
 * @Date 2019/12/17 16:42
 * @Describe TODO
 * @Mark
 **/
public class AboutOurPresenter extends BasePresenter<AboutOurView> {
    public AboutOurPresenter(AboutOurView baseView) {
        super(baseView);
    }

    public void getAppVersionName(){
        String currentVersionName= AppUtils.getAppVersionName();
        baseView.showAppCurrentVersionName("V"+currentVersionName);
    }
}
