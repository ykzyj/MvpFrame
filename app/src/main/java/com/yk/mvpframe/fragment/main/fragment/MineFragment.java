package com.yk.mvpframe.fragment.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;
import com.gyf.immersionbar.ImmersionBar;
import com.jakewharton.rxbinding3.view.RxView;
import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.login.activity.LoginActivity;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.fragment.main.presenter.MinePresenter;
import com.yk.mvpframe.fragment.main.view.MineView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @FileName TabFragment
 * @Author alan
 * @Date 2019/7/25 17:03
 * @Describe TODO
 * @Mark
 **/
public class MineFragment extends BaseFragment implements MineView {

    @BindView(R.id.user_logo_cv)
    CardView userLogoCv;
    @BindView(R.id.user_title_tv)
    TextView userTitleTv;
    @BindView(R.id.user_edit_img)
    ImageView userEditImg;

    public static MineFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Consts.BUNDLE_KEY_TAB_TITLE, title);
        MineFragment tabFragment = new MineFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected void setListener() {
        presenter.addDisposable(RxView.clicks(userTitleTv)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(o -> LoginActivity.startLoginActivity(getActivity())));
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarDarkFont(false).navigationBarDarkIcon(true)
                .navigationBarColor(R.color.colorWhite)
                .init();
    }
}
