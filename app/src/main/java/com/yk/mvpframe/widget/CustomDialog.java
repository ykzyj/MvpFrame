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

/**
 * @FileName CustomDialog
 * @Author alan
 * @Date 2019/7/18 10:55
 * @Describe TODO
 * @Mark
 **/
public class CustomDialog extends Dialog {
    private Button mOkBtn;
    private Button mCancelBtn;
    private TextView mTitleTV;
    private View mDividerView;
    private TextView mMessageTv;
    private onCancelClickListener onCancelClickListener;
    private onOkClickListener onOkClickListener;
    private ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();

    private String mTitleStr;
    private String mMessageStr;
    private String mOkStr, mCancelStr;

    public CustomDialog(@NonNull Context context) {
        super(context,R.style.CustomDialog);
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param cancelClickListener
     */
    public void setOnCancelClickListener(String str, onCancelClickListener cancelClickListener) {
        if (str != null) {
            mCancelStr = str;
        }
        this.onCancelClickListener = cancelClickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param okClickListener
     */
    public void setOnOkClickListener(String str, onOkClickListener okClickListener) {
        if (str != null) {
            mOkStr = str;
        }
        this.onOkClickListener = okClickListener;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_dialog);

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

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (mTitleStr != null) {
            mTitleTV.setText(mTitleStr);
            mTitleTV.setVisibility(View.VISIBLE);
            mDividerView.setVisibility(View.VISIBLE);
        }
        if (mMessageStr != null) {
            mMessageTv.setText(mMessageStr);
        }
        if (mOkStr != null) {
            mOkBtn.setText(mOkStr);
            mOkBtn.setVisibility(View.VISIBLE);
        }
        if (mCancelStr != null) {
            mCancelBtn.setText(mCancelStr);
            mCancelBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        mOkBtn = findViewById(R.id.ok_btn);
        mCancelBtn = findViewById(R.id.cancel_btn);
        mTitleTV = findViewById(R.id.dg_title_tv);
        mDividerView = findViewById(R.id.divider_view);
        mMessageTv = findViewById(R.id.dg_content_tv);
    }

    /**
     * 初始化界面的确定和取消监听
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkClickListener != null) {
                    onOkClickListener.onOkClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelClickListener != null) {
                    onCancelClickListener.onCancelClick();
                }
            }
        });
    }

    public void setTitle(String title) {
        mTitleStr=title;
    }

    public void setMessage(String message) {
        mMessageStr=message;
    }

    public interface onCancelClickListener {
        public void onCancelClick();
    }

    public interface onOkClickListener {
        public void onOkClick();
    }

    public void show(long duration){
        Runnable runner = new Runnable() {
            public void run() {
                dismiss();
            }
        };
        mExecutor.schedule(runner, duration, TimeUnit.MILLISECONDS);
        show();
    }
}
