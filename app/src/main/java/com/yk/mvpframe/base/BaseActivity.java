package com.yk.mvpframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.yk.mvpframe.tools.ActivityManager;
import com.yk.mvpframe.util.ToastUtils;
import com.yk.mvpframe.widget.LoadingDialog;
import com.yk.mvpframe.widget.ProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @FileName BaseActivity
 * @Author alan
 * @Date 2019/7/11 16:19
 * @Describe TODO
 * @Mark
 **/
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    public Context context;
    private ProgressDialog mDialog;
    private LoadingDialog mLoadingDialog;
    protected T presenter;
    protected Unbinder unbinder;

    protected abstract T createPresenter();
    protected abstract int getLayoutId();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().pushActivity(this);
        context=this;
        setContentView(getLayoutId());
        presenter=createPresenter();
        unbinder= ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initData(){}

    protected void initView(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().popActivity(this);
        if(presenter!=null){
            presenter.detachView();
        }
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    @Override
    public void showToast(String s) {
        ToastUtils.show(s);
    }

    @Override
    public void showToast(int resId) {
        ToastUtils.show(resId);
    }

    public void showFileDialog() {
        if(mDialog==null){
            mDialog = new ProgressDialog(context);
            mDialog.setCanceledOnTouchOutside(false);
        }
        else {
            mDialog.setProgress(0);
        }
        mDialog.show();
    }

    public void hideFileDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


    private void closeLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    private void showLoadingDialog(String loadingTxt) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(context);
        }
        if(!TextUtils.isEmpty(loadingTxt)){
            mLoadingDialog.setLoadingTxt(loadingTxt);
        }
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
    }

    @Override
    public void showLoading() {
        showLoadingDialog("");
    }

    @Override
    public void showLoading(String loadingTxt) {
        showLoadingDialog(loadingTxt);
    }

    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void onErrorCode(BaseModel model) {
    }

    @Override
    public void showLoadingFileDialog() {
        showFileDialog();
    }

    @Override
    public void hideLoadingFileDialog() {
        hideFileDialog();
    }

    @Override
    public void onProgress(int progress) {
        if (mDialog != null) {
            mDialog.setProgress(progress);
        }
    }
}
