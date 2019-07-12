package com.yk.mvpframe.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.yk.mvpframe.R;
import com.yk.mvpframe.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @FileName LoginActivity
 * @Author alan
 * @Date 2019/7/11 17:06
 * @Describe TODO
 * @Mark
 **/
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.login_name_et)
    EditText loginNameEt;
    @BindView(R.id.login_pwd_et)
    EditText loginPwdEt;
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
        showToast("成功");
    }

    @OnClick(R.id.login_btn)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                presenter.login(loginNameEt.getText().toString(),loginPwdEt.getText().toString());
                break;
        }
    }
}
