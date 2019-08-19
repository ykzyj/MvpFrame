package com.yk.mvpframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.gyf.immersionbar.ImmersionBar;
import com.jakewharton.rxbinding3.view.RxView;
import com.yk.mvpframe.R;
import com.yk.mvpframe.event.LoginEvent;
import com.yk.mvpframe.tools.ActivityManager;
import com.yk.mvpframe.util.CacheUtils;
import com.yk.mvpframe.util.ToastUtils;
import com.yk.mvpframe.widget.LoadingDialog;
import com.yk.mvpframe.widget.ProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;
import butterknife.BindView;
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
    @Nullable
    @BindView(R.id.back_img)
    ImageView backImg;
    @Nullable
    @BindView(R.id.title_tv)
    TextView titleTv;
    @Nullable
    @BindView(R.id.right_tv)
    TextView rightTv;
    @Nullable
    @BindView(R.id.right_img)
    ImageView rightImg;
    @Nullable
    @BindView(R.id.top_bar)
    Toolbar toolbar;

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
        initDataBeforeView(savedInstanceState);
        context=this;
        setContentView(getLayoutId());
        presenter=createPresenter();
        unbinder= ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initImmersionBar();
        initView();
        initData();
        setListener();
        initTopLeftBack();
    }

    protected void initDataBeforeView(Bundle savedInstanceState){}

    protected void setListener(){}

    protected void initData(){}

    protected void initView(){}

    private void initTopLeftBack(){
        if(backImg!=null){
            presenter.addDisposable(RxView.clicks(backImg)
                    .throttleFirst(500, TimeUnit.MILLISECONDS)
                    .subscribe(o -> finish()));
        }
    }

    protected void setHeader(String title){
        if(titleTv!=null){
            titleTv.setText(title);
        }
    }

    public void setLeftBackVisible(int visible){
        if(backImg!=null){
            backImg.setVisibility(visible);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        ImmersionBar.with(this).titleBar(toolbar).navigationBarDarkIcon(true).navigationBarColor(R.color.colorWhite).init();
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
    }
}
