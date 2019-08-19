package com.yk.mvpframe.activity.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.mine.presenter.SystemSettingPresenter;
import com.yk.mvpframe.activity.mine.view.SystemSettingView;
import com.yk.mvpframe.base.BaseActivity;
import com.yk.mvpframe.widget.CustomDialog;

import butterknife.BindView;

/**
 * @FileName SystemSettingActivity
 * @Author alan
 * @Date 2019/8/13 16:50
 * @Describe TODO
 * @Mark
 **/
public class SystemSettingActivity extends BaseActivity<SystemSettingPresenter> implements SystemSettingView {

    @BindView(R.id.app_version_name_tv)
    TextView appVersionNameTv;
    @BindView(R.id.update_app_tv)
    TextView updateAppTv;
    @BindView(R.id.update_version_tv)
    TextView updateVersionTv;
    @BindView(R.id.clear_app_tv)
    TextView clearAppTv;
    @BindView(R.id.clear_app_value_tv)
    TextView clearAppValueTv;
    @BindView(R.id.night_mode_switch)
    Switch nightModeSwitch;
    @BindView(R.id.tv_out_login)
    TextView tvOutLogin;
    @BindView(R.id.user_habit_cv)
    CardView userHabitCv;

    CustomDialog outDialog;

    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, SystemSettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected SystemSettingPresenter createPresenter() {
        return new SystemSettingPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_setting;
    }

    @Override
    protected void initView() {
        super.initView();
        setHeader(getString(R.string.app_mine_setting));
        presenter.getAppUpdate();
    }

    @Override
    protected void setListener() {
        presenter.addViewClick(tvOutLogin,o->{
            if(outDialog==null){
                outDialog=new CustomDialog(SystemSettingActivity.this);
                outDialog.setTitle(getString(R.string.login_out_btn_txt));
                outDialog.setMessage(getString(R.string.system_setting_out_login_msg));
                outDialog.setOnOkClickListener(getString(R.string.app_ok), new CustomDialog.onOkClickListener() {
                    @Override
                    public void onOkClick() {
                        if(outDialog!=null){
                            outDialog.dismiss();
                            presenter.loginOut();
                            finish();
                        }
                    }
                });
                outDialog.setOnCancelClickListener(getString(R.string.app_cancel), new CustomDialog.onCancelClickListener() {
                    @Override
                    public void onCancelClick() {
                        if(outDialog!=null){
                            outDialog.dismiss();
                        }
                    }
                });
            }
            outDialog.show();
        });
    }

    @Override
    public void showAppCurrentVersionName(String version) {
        appVersionNameTv.setText(version);
    }

    @Override
    public void showAppNewVersionName(String version) {
        updateVersionTv.setVisibility(View.VISIBLE);
        userHabitCv.setVisibility(View.VISIBLE);
        updateVersionTv.setText(version);
    }
}
