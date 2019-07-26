package com.yk.mvpframe.activity.login.activity;

import android.widget.Button;
import com.jakewharton.rxbinding3.view.RxView;
import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.login.presenter.LoginPresenter;
import com.yk.mvpframe.activity.login.view.LoginView;
import com.yk.mvpframe.activity.main.activity.MainActivity;
import com.yk.mvpframe.base.BaseActivity;
import com.yk.mvpframe.widget.SuperEditText;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

/**
 * @FileName LoginActivity
 * @Author alan
 * @Date 2019/7/11 17:06
 * @Describe TODO
 * @Mark
 **/
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.login_name_set)
    SuperEditText loginNameSet;
    @BindView(R.id.login_pw_set)
    SuperEditText loginPwSet;
    @BindView(R.id.login_btn)
    Button loginBtn;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginSucc() {
        MainActivity.startMainActivity(LoginActivity.this);
    }

    @Override
    protected void initView() {
        presenter.addDisposable(RxView.clicks(loginBtn)
                .throttleFirst(500,TimeUnit.MILLISECONDS)
                .subscribe(o -> presenter.login(loginNameSet.getText(),loginPwSet.getText())));
    }
}