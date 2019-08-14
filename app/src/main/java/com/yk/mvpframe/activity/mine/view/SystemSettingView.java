package com.yk.mvpframe.activity.mine.view;

import com.yk.mvpframe.base.BaseView;

/**
 * @FileName SystemSettingView
 * @Author alan
 * @Date 2019/8/13 16:36
 * @Describe TODO
 * @Mark
 **/
public interface SystemSettingView extends BaseView {
    void showAppCurrentVersionName(String version);
    void showAppNewVersionName(String version);
}
