package com.yk.mvpframe.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yk.mvpframe.R;

/**
 * @FileName TabView
 * @Author alan
 * @Date 2019/7/26 15:39
 * @Describe TODO
 * @Mark
 **/
public class TabView extends FrameLayout {

    private ImageView mTabSelectImg;
    private ImageView mTabNormalImg;
    private TextView mTabTextTv;

    private ArgbEvaluator mArgbEvaluator;

    private int mTabSelectSrc;
    private int mTabNormalSrc;
    private String mTabStr;

    public TabView(@NonNull @android.support.annotation.NonNull Context context, @Nullable @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.TabView);
        mTabSelectSrc=ta.getResourceId(R.styleable.TabView_tab_select_image,R.mipmap.ic_launcher);
        mTabNormalSrc=ta.getResourceId(R.styleable.TabView_tab_normal_image,R.mipmap.ic_launcher);
        mTabStr=ta.getString(R.styleable.TabView_tab_text);
        ta.recycle();

        inflate(context, R.layout.tab_view,this);

        mTabSelectImg=findViewById(R.id.tab_select_img);
        mTabNormalImg=findViewById(R.id.tab_normal_img);
        mTabTextTv=findViewById(R.id.tab_name_tv);

        mArgbEvaluator = new ArgbEvaluator();

        initView();
        setProgress(0);
    }

    private void initView() {
        mTabNormalImg.setImageResource(mTabNormalSrc);
        mTabSelectImg.setImageResource(mTabSelectSrc);
        if(!TextUtils.isEmpty(mTabStr)){
            mTabTextTv.setText(mTabStr);
        }
    }

    public void setIconAndText(int icon,int iconSelect,String text){
        mTabNormalImg.setImageResource(icon);
        mTabSelectImg.setImageResource(iconSelect);
        mTabTextTv.setText(text);
    }

    public void setProgress(float progress){
        mTabSelectImg.setAlpha(progress);
        mTabNormalImg.setAlpha(1-progress);
        mTabTextTv.setTextColor((int)(mArgbEvaluator
                .evaluate(progress,getResources().getColor(R.color.tab_normal_grey),
                        getResources().getColor(R.color.colorPrimary))));
    }
}
