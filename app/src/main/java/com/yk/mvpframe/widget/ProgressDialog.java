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
import android.widget.TextView;

import com.yk.mvpframe.R;

/**
 * @FileName ProgressDialog
 * @Author alan
 * @Date 2019/7/22 10:43
 * @Describe TODO
 * @Mark
 **/
public class ProgressDialog extends Dialog {
    private HorizontalProgressBarWithNumber mProgressPb;
    private TextView mCancelTv;
    private TextView mTitleTV;
    private View mDividerView;
    private String mTitleStr;

    public ProgressDialog(@NonNull Context context) {
        super(context,R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_progress_dialog);

        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initEvent();

        Window window = getWindow();
        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = (int) (d.getWidth() * 0.8);
        window.setAttributes(params);
    }


    private void initView() {
        mTitleTV = findViewById(R.id.dg_title_tv);
        mDividerView = findViewById(R.id.divider_view);
        mProgressPb = findViewById(R.id.progress_pb);
        mCancelTv = findViewById(R.id.cancel_tv);
    }

    private void initData() {
        if (mTitleStr != null) {
            mTitleTV.setText(mTitleStr);
            mTitleTV.setVisibility(View.VISIBLE);
            mDividerView.setVisibility(View.VISIBLE);
        }
    }

    private void initEvent() {
        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setTitle(String title) {
        mTitleStr=title;
    }

    public void setCancelVisible(int visible) {
        mCancelTv.setVisibility(visible);
    }

    public void setProgress(int progress) {
        mProgressPb.setProgress(progress);
    }
}
