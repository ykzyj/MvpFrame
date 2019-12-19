package com.yk.mvpframe.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @FileName CountdownButton
 * @Author alan
 * @Date 2019/12/19 10:33
 * @Describe TODO
 * @Mark
 **/
public class CountdownButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
    private long lenght = 60 * 1000;//默认倒计时时间；
    private long time;//倒计时时长
    private Timer timer;//开始执行倒计时
    private TimerTask timerTask;//每次倒计时执行的任务
    private String beforeText = "获取验证码";
    private String afterText = "秒后重新获取";
    private OnClickListener onClickListener;//按钮点击事件
    /**
     * 更新显示的文本
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int timeLength=String.valueOf(time / 1000).length();
            String showTxt=time / 1000 + afterText;
            SpannableString spannableString = new SpannableString(showTxt);

            ForegroundColorSpan foregroundRedSpan = new ForegroundColorSpan(Color.RED);
            spannableString.setSpan(foregroundRedSpan, 0, timeLength, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ForegroundColorSpan foregroundBlackSpan = new ForegroundColorSpan(Color.rgb(110, 110, 110));
            spannableString.setSpan(foregroundBlackSpan, timeLength, showTxt.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

            CountdownButton.this.setText(spannableString);
            time -= 1000;
            if (time < 0) {
                CountdownButton.this.setEnabled(true);
                CountdownButton.this.setText(beforeText);
                clearTimer();
            }
        }
    };


    public CountdownButton(Context context) {
        super(context);
        this.setText(beforeText);
        setOnClickListener(this);
    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public CountdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    /**
     * 清除倒计时
     */
    private void clearTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 设置倒计时时长
     *
     * @param lenght 默认毫秒
     */
    public void setLenght(long lenght) {
        this.lenght = lenght;
    }

    /**
     * 设置未点击时显示的文字
     *
     * @param beforeText
     */
    public void setBeforeText(String beforeText) {
        this.beforeText = beforeText;
    }

    /**
     * 设置未点击后显示的文字
     *
     * @param afterText
     */
    public void setAfterText(String afterText) {
        this.afterText = afterText;
    }

    /**
     * 点击按钮后的操作
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
        initTimer();
        this.setText(time / 1000 + afterText);
        this.setEnabled(false);
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * 初始化时间
     */
    private void initTimer() {
        time = lenght;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
    }

    /**
     * 设置监听按钮点击事件
     *
     * @param onclickListener
     */
    @Override
    public void setOnClickListener(OnClickListener onclickListener) {
        if (onclickListener instanceof CountdownButton) {
            super.setOnClickListener(onclickListener);
        } else {
            this.onClickListener = onclickListener;
        }

    }
}
