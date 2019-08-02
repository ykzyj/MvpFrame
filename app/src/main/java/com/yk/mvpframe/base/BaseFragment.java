package com.yk.mvpframe.base;

import android.app.Activity;
import android.content.Context;
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
import com.yk.mvpframe.R;
import com.yk.mvpframe.consts.Consts;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @FileName BaseFragment
 * @Author alan
 * @Date 2019/7/31 9:39
 * @Describe TODO
 * @Mark
 **/
public abstract class BaseFragment extends Fragment {
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
        initData();
        initView();
        setListener();
        initHeader();
    }

    protected void initHeader() {
        if (titleTv != null && !TextUtils.isEmpty(mTitle)) {
            titleTv.setText(mTitle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected abstract int getLayoutId();

    protected void initDataBeforeView(Bundle savedInstanceState) {
    }

    protected void initData() {
    }

    protected void initView() {
        if (backImg != null) {
            backImg.setVisibility(View.INVISIBLE);
        }
    }

    protected void setListener() {
    }

    private void initTopLeftBack(View.OnClickListener onClickListener) {
        if (backImg != null) {
            backImg.setOnClickListener(onClickListener);
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

    public void setRightTv(String title, View.OnClickListener onClickListener) {
        if (rightTv != null) {
            rightTv.setVisibility(View.VISIBLE);
            rightTv.setText(title);
            rightTv.setOnClickListener(onClickListener);
        }
    }

    public void setRightImg(int resId, View.OnClickListener onClickListener) {
        if (rightImg != null) {
            rightImg.setVisibility(View.VISIBLE);
            rightImg.setImageResource(resId);
            rightImg.setOnClickListener(onClickListener);
        }
    }

    public void initImmersionBar() {
        if(toolbar!=null){
            ImmersionBar.setTitleBar(this, toolbar);
        }
    }
}
