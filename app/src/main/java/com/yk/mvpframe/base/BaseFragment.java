package com.yk.mvpframe.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gyf.immersionbar.ImmersionBar;
import com.jakewharton.rxbinding3.view.RxView;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yk.mvpframe.R;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.event.LoginEvent;
import com.yk.mvpframe.event.PermissionGrantedListener;
import com.yk.mvpframe.util.PermissionsUtils;
import com.yk.mvpframe.util.ToastUtils;
import com.yk.mvpframe.widget.CustomDialog;
import com.yk.mvpframe.widget.LoadingDialog;
import com.yk.mvpframe.widget.ProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * @FileName BaseFragment
 * @Author alan
 * @Date 2019/7/31 9:39
 * @Describe TODO
 * @Mark
 **/
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
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

    private String mTitle;
    private Unbinder unbinder;
    protected Activity mActivity;
    protected View mRootView;
    private ProgressDialog mDialog;
    private LoadingDialog mLoadingDialog;
    protected T presenter;

    protected abstract int getLayoutId();
    protected abstract T createPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(Consts.BUNDLE_KEY_TAB_TITLE, "");
        }
        initDataBeforeView(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        } else {
            ViewGroup viewGroup = (ViewGroup) mRootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        presenter=createPresenter();
        initData();
        initView();
        setListener();
        initHeader();
        initTopLeftBack();
    }

    protected void initHeader() {
        if (titleTv != null && !TextUtils.isEmpty(mTitle)) {
            titleTv.setText(mTitle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(presenter!=null){
            presenter.detachView();
        }
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
    }

    protected void initDataBeforeView(Bundle savedInstanceState) {
    }

    protected void initView(){}

    protected void initData() {
    }

    protected void setListener() {
    }

    private void initTopLeftBack(){
        if(backImg!=null){
            presenter.addDisposable(RxView.clicks(backImg)
                    .throttleFirst(500, TimeUnit.MILLISECONDS)
                    .subscribe(o -> mActivity.finish()));
        }
    }

    protected void setHeader(String title) {
        if (titleTv != null) {
            titleTv.setText(title);
        }
    }

    public void setLeftBackVisible(int visible) {
        if (backImg != null) {
            backImg.setVisibility(visible);
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
            mDialog = new ProgressDialog(mActivity);
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
            mLoadingDialog = new LoadingDialog(mActivity);
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

    public void initImmersionBar() {
        if(toolbar!=null){
            ImmersionBar.setTitleBar(this, toolbar);
        }
    }

    public void requestCommonPermissions(PermissionGrantedListener permissionGrantedListener, String... permissions) {
        PermissionsUtils.requestCommonPermissions(getActivity(),permissionGrantedListener,permissions);
    }
}
