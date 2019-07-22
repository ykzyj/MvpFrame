package com.yk.mvpframe.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.yk.mvpframe.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import app.dinus.com.loadingdrawable.LoadingView;
import app.dinus.com.loadingdrawable.render.LoadingRenderer;
import app.dinus.com.loadingdrawable.render.scenery.ElectricFanLoadingRenderer;

/**
 * @FileName LoadingDialog
 * @Author alan
 * @Date 2019/7/22 10:43
 * @Describe TODO
 * @Mark
 **/
public class LoadingDialog extends Dialog {
    private TextView mLoadingTv;
    private String mLoadingStr;

    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_loading_dialog);

        setCanceledOnTouchOutside(false);
        initView();
        initData();

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    private void initData() {
        //如果用户自定了title和message
        if (mLoadingStr != null) {
            mLoadingTv.setText(mLoadingStr);
            mLoadingTv.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        mLoadingTv = findViewById(R.id.loading_txt);
    }

    public void setLoadingTxt(String loadingTxt) {
        mLoadingStr=loadingTxt;
    }
}
