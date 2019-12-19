package com.yk.mvpframe.activity.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.mine.presenter.AboutOurPresenter;
import com.yk.mvpframe.activity.mine.view.AboutOurView;
import com.yk.mvpframe.base.BaseActivity;

import butterknife.BindView;

/**
 * @FileName AboutOurActivity
 * @Author alan
 * @Date 2019/12/17 16:40
 * @Describe TODO
 * @Mark
 **/
public class AboutOurActivity extends BaseActivity<AboutOurPresenter> implements AboutOurView {

    @BindView(R.id.app_version_code_tv)
    TextView appVersionCodeTv;
    @BindView(R.id.app_function_tv)
    TextView appFunctionTv;
    @BindView(R.id.our_contact_tv)
    TextView ourContactTv;

    public static void startAboutOurActivity(Context context) {
        Intent intent = new Intent(context, AboutOurActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected AboutOurPresenter createPresenter() {
        return new AboutOurPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_our;
    }

    @Override
    protected void initView() {
        super.initView();
        setHeader(getString(R.string.app_mine_about));

        presenter.getAppVersionName();
    }

    @Override
    public void showAppCurrentVersionName(String version) {
        appVersionCodeTv.setText(version);
    }
}
