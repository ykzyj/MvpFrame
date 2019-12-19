package com.yk.mvpframe.activity.login.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.login.presenter.LoginPresenter;
import com.yk.mvpframe.activity.login.view.LoginView;
import com.yk.mvpframe.base.BaseActivity;
import com.yk.mvpframe.widget.SuperEditText;

import butterknife.BindView;

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
    @BindView(R.id.login_tab)
    TabLayout loginTab;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.login_wx_img)
    ImageView loginWxImg;
    @BindView(R.id.login_zfb_img)
    ImageView loginZfbImg;
    @BindView(R.id.login_phone_set)
    SuperEditText loginPhoneSet;
    @BindView(R.id.login_code_set)
    SuperEditText loginCodeSet;
    @BindView(R.id.get_phone_code_btn)
    Button getPhoneCodeBtn;

    private String mSelectedTab;

    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

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
        finish();
    }

    @Override
    protected void initView() {
        setHeader(getString(R.string.login_btn_txt));
        addTabToTabLayout();
    }

    @Override
    protected void setListener() {
        presenter.addViewClick(loginBtn, o -> presenter.login(loginNameSet.getText(), loginPwSet.getText()));
    }

    private void addTabToTabLayout() {
        String[] mTitles = {"账户登录", "短信登录"};
        for (int i = 0; i < mTitles.length; i++) {
            loginTab.addTab(loginTab.newTab().setText(mTitles[i]));
        }
        loginTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选择时触发
                mSelectedTab = tab.getText().toString();
                if ("账户登录".equals(mSelectedTab)) {
                    loginNameSet.setVisibility(View.VISIBLE);
                    loginPwSet.setVisibility(View.VISIBLE);
                    loginPhoneSet.setVisibility(View.GONE);
                    loginCodeSet.setVisibility(View.GONE);
                    getPhoneCodeBtn.setVisibility(View.GONE);
                } else {
                    loginNameSet.setVisibility(View.GONE);
                    loginPwSet.setVisibility(View.GONE);
                    loginPhoneSet.setVisibility(View.VISIBLE);
                    loginCodeSet.setVisibility(View.VISIBLE);
                    getPhoneCodeBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选择是触发
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //选中之后再次点击即复选时触发
            }
        });
    }
}
