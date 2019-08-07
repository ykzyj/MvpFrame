package com.yk.mvpframe.fragment.main.fragment;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.yk.mvpframe.R;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.base.BasePresenter;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.fragment.main.presenter.PlayPresenter;
import com.yk.mvpframe.fragment.main.view.PlayView;

/**
 * @FileName TabFragment
 * @Author alan
 * @Date 2019/7/25 17:03
 * @Describe TODO
 * @Mark
 **/
public class PlayFragment extends BaseFragment implements PlayView {

    public static PlayFragment newInstance(String title){
        Bundle bundle=new Bundle();
        bundle.putString(Consts.BUNDLE_KEY_TAB_TITLE,title);
        PlayFragment tabFragment=new PlayFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_play;
    }

    @Override
    protected PlayPresenter createPresenter() {
        return new PlayPresenter(this);
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarDarkFont(true).navigationBarDarkIcon(true).navigationBarColor(R.color.colorWhite).init();
    }
}
