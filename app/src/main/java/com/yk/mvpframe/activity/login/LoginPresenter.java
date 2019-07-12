package com.yk.mvpframe.activity.login;

import com.yk.mvpframe.Util.AsciiUtils;
import com.yk.mvpframe.base.BaseObserver;
import com.yk.mvpframe.base.BasePresenter;
import com.yk.mvpframe.model.UserInfoModel;


/**
 * @FileName LoginPresenter
 * @Author alan
 * @Date 2019/7/11 17:08
 * @Describe TODO
 * @Mark
 **/
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView baseView) {
        super(baseView);
    }

    public void login(String name,String pwd) {
        addDisposable(apiServer.login(AsciiUtils.stringEncryption(name) , AsciiUtils.stringEncryption(pwd))
                ,new BaseObserver(baseView,true) {
                    @Override
                    public void onSuccess(Object o) {
                        if(o!=null&&o instanceof UserInfoModel){
                            baseView.onLoginSucc();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        baseView.showError(msg);
                    }
        });
    }
}
