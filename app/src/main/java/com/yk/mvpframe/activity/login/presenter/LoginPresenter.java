package com.yk.mvpframe.activity.login.presenter;

import android.text.TextUtils;
import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.login.view.LoginView;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.event.LoginEvent;
import com.yk.mvpframe.util.AsciiUtils;
import com.yk.mvpframe.base.BaseObserver;
import com.yk.mvpframe.base.BasePresenter;
import com.yk.mvpframe.model.UserInfoModel;
import com.yk.mvpframe.util.CacheUtils;

import org.greenrobot.eventbus.EventBus;

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
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            baseView.showToast(R.string.login_name_or_pw_input);
            return;
        }

        addDisposable(apiRepository.login(AsciiUtils.stringEncryption(name) , AsciiUtils.stringEncryption(pwd),true)
                ,new BaseObserver(baseView,true) {
                    @Override
                    public void onSuccess(Object o) {
                        if(o!=null&&o instanceof UserInfoModel){
                            CacheUtils.setUserInfoModel((UserInfoModel)o);
                            EventBus.getDefault().post(new LoginEvent(LoginEvent.LOGIN));
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
