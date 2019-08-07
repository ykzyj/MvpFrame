package com.yk.mvpframe.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.blankj.utilcode.util.ConvertUtils;
import com.yk.mvpframe.R;

/**
 * @FileName SuperEditText
 * @Author alan
 * @Date 2019/7/16 10:56
 * @Describe TODO
 * @Mark
 **/
public class SuperEditText extends RelativeLayout implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {
    private Context mContext;

    private TextView mTitleTv;
    private EditText mContentEt;
    private ToggleButton mPwVisibleTb;
    private ImageView mClearImg;
    private ImageView mTitleImg;
    private View mBottomView;

    private InputMethodManager imm;
    private float imageW=28.0f;
    private int marginCount=10;

    private boolean mAnimateFlag=false;
    private boolean mHasFocusFlag=true;

    private int mDefaultColor;
    private int mFocusColor;
    private int mForbidColor;
    private int mInputMaxLength;
    private boolean mIsPw;
    private String mTitleName;
    private String mHintStr;
    private int mTitleImgRes;

    public SuperEditText(Context context) {
        this(context,null);
    }

    public SuperEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.SuperEdit);
        mDefaultColor=ta.getColor(R.styleable.SuperEdit_default_color,getResources().getColor(R.color.edit_default_color));
        mFocusColor=ta.getColor(R.styleable.SuperEdit_focus_color,getResources().getColor(R.color.colorPrimary));
        mForbidColor=ta.getColor(R.styleable.SuperEdit_forbid_color,getResources().getColor(R.color.edit_forbid_color));
        mIsPw=ta.getBoolean(R.styleable.SuperEdit_is_pw_input,false);
        mInputMaxLength=ta.getInt(R.styleable.SuperEdit_input_max_length,-1);
        mTitleName=ta.getString(R.styleable.SuperEdit_title_name);
        mHintStr=ta.getString(R.styleable.SuperEdit_hint_txt);
        mTitleImgRes=ta.getResourceId(R.styleable.SuperEdit_title_img,R.mipmap.frame_account);
        ta.recycle();

        initView(context);
        initListener();
    }

    private void initView(Context context) {
        mTitleTv=new TextView(context);
        mContentEt=new EditText(context);
        mPwVisibleTb=new ToggleButton(context);
        mClearImg=new ImageView(context);
        mBottomView=new View(context);
        mTitleImg=new ImageView(context);

        mTitleTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTitleTv.setText("标题");
        mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        mTitleTv.setId(View.generateViewId());
        if(!TextUtils.isEmpty(mTitleName)){
            mTitleTv.setText(mTitleName);
        }
        LayoutParams titleTvParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleTvParams.addRule(RelativeLayout.CENTER_VERTICAL);
        titleTvParams.setMargins(ConvertUtils.dp2px(marginCount),
                0 ,0,0);
        this.addView(mTitleTv, titleTvParams);

        mTitleImg.setId(View.generateViewId());
        mTitleImg.setVisibility(View.INVISIBLE);
        mTitleImg.setBackgroundResource(mTitleImgRes);
        LayoutParams TitleImgParams=new LayoutParams(ConvertUtils.dp2px(imageW),
                ConvertUtils.dp2px(imageW));
        TitleImgParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        TitleImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        TitleImgParams.setMargins(ConvertUtils.dp2px(marginCount),
                0 ,0,ConvertUtils.dp2px(10));
        this.addView(mTitleImg, TitleImgParams);

        mContentEt.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        mContentEt.setBackground(null);
        mContentEt.setId(View.generateViewId());
        mContentEt.setVisibility(INVISIBLE);
        mContentEt.setSingleLine(true);
        if(!TextUtils.isEmpty(mHintStr)){
            mContentEt.setHint(mHintStr);
        }
        LayoutParams contentEtParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        contentEtParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        contentEtParams.addRule(RelativeLayout.RIGHT_OF,mTitleImg.getId());
        contentEtParams.setMargins(ConvertUtils.dp2px(5),
                0 ,ConvertUtils.dp2px(60),ConvertUtils.dp2px(getHeight()/4));
        this.addView(mContentEt, contentEtParams);

        mClearImg.setId(View.generateViewId());
        mClearImg.setVisibility(View.INVISIBLE);
        mClearImg.setBackgroundResource(R.mipmap.frame_clear);
        LayoutParams clearImgParams=new LayoutParams(ConvertUtils.dp2px(imageW),
                ConvertUtils.dp2px(imageW));
        clearImgParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        clearImgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        clearImgParams.setMargins(0,
                0 ,ConvertUtils.dp2px(0),ConvertUtils.dp2px(10));
        this.addView(mClearImg, clearImgParams);

        mPwVisibleTb.setId(View.generateViewId());
        mPwVisibleTb.setBackgroundResource(R.mipmap.frame_pw_hide);
        mPwVisibleTb.setText("");
        mPwVisibleTb.setTextOn("");
        mPwVisibleTb.setTextOff("");
        mPwVisibleTb.setVisibility(View.INVISIBLE);
        LayoutParams pwVisibleParams=new LayoutParams(ConvertUtils.dp2px(imageW),
                ConvertUtils.dp2px(imageW));
        pwVisibleParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pwVisibleParams.addRule(RelativeLayout.LEFT_OF,mClearImg.getId());
        pwVisibleParams.setMargins(0,
                0 ,ConvertUtils.dp2px(10),ConvertUtils.dp2px(10));
        this.addView(mPwVisibleTb, pwVisibleParams);

        mBottomView.setId(View.generateViewId());
        LayoutParams bottomViewParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ConvertUtils.dp2px(1.5f));
        bottomViewParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomViewParams.setMargins(0, 0 ,0,0);
        this.addView(mBottomView, bottomViewParams);

        setViewColor(mDefaultColor);
    }

    private void initListener() {
        mContentEt.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                mHasFocusFlag=hasFocus;
                if (hasFocus) {
                    imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mContentEt,InputMethodManager.SHOW_FORCED);
                    setViewColor(mFocusColor);
                } else {
                    resetView();
                }
            }
        });
       mContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > mInputMaxLength) {
                    mContentEt.setText(charSequence.toString().substring(0, mInputMaxLength));
                    mContentEt.setSelection(mInputMaxLength);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(mHasFocusFlag){
                    if(mInputMaxLength!=-1){
                        if (mContentEt.getText().toString().length()==mInputMaxLength){
                            setViewColor(mForbidColor);
                        }
                        else {
                            setViewColor(mFocusColor);
                        }
                    }
                }
                else {
                    if(!TextUtils.isEmpty(mContentEt.getText().toString())){
                        resetView();
                    }
                }
            }
        });
        mTitleTv.setOnClickListener(this);
        mClearImg.setOnClickListener(this);
        mPwVisibleTb.setOnCheckedChangeListener(this);
    }

    private void resetView() {
        if(mAnimateFlag&& TextUtils.isEmpty(mContentEt.getText().toString())){
            mTitleTv.animate().translationYBy(getHeight()/4)
                    .translationX(ConvertUtils.dp2px(imageW/4))
                    .scaleX(1.0f).scaleY(1.0f)
                    .setDuration(200);
            mContentEt.setVisibility(INVISIBLE);
            mAnimateFlag=!mAnimateFlag;
            imm.hideSoftInputFromWindow(mContentEt.getWindowToken(), 0);
            mClearImg.setVisibility(View.INVISIBLE);
            mPwVisibleTb.setVisibility(View.INVISIBLE);
            mTitleImg.setVisibility(View.GONE);
            setViewColor(mDefaultColor);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        mHasFocusFlag=isChecked;
        if(isChecked){
            mPwVisibleTb.setBackgroundResource(R.mipmap.frame_pw_show);
            mContentEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mContentEt.setSelection(mContentEt.getText().toString().length());
        }
        else {
            mPwVisibleTb.setBackgroundResource(R.mipmap.frame_pw_hide);
            mContentEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mContentEt.setSelection(mContentEt.getText().toString().length());
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==mTitleTv.getId()){
            if(!mAnimateFlag){
                mTitleTv.animate().translationYBy(-getHeight()/4)
                        .translationX(-ConvertUtils.dp2px(imageW))
                        .scaleX(0.85f).scaleY(0.85f)
                        .setDuration(200);
                mContentEt.setVisibility(VISIBLE);
                mContentEt.requestFocus();
                mAnimateFlag=!mAnimateFlag;
                mClearImg.setVisibility(View.VISIBLE);
                mTitleImg.setVisibility(View.VISIBLE);
                if(mIsPw){
                    mPwVisibleTb.setVisibility(View.VISIBLE);
                    mContentEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        }
        else if(view.getId()==mClearImg.getId()){
            mContentEt.setText("");
        }
    }

    private void setViewColor(int color) {
        mTitleTv.setTextColor(color);
        mBottomView.setBackgroundColor(color);
    }

    public String getText(){
        return mContentEt.getText().toString();
    }
}
