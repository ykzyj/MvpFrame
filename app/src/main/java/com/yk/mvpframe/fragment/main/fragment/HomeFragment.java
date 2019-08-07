package com.yk.mvpframe.fragment.main.fragment;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.yk.mvpframe.R;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.base.BasePresenter;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.fragment.main.presenter.EatPresenter;
import com.yk.mvpframe.fragment.main.presenter.HomePresenter;
import com.yk.mvpframe.fragment.main.view.EatView;
import com.yk.mvpframe.fragment.main.view.HomeView;

/**
 * @FileName TabFragment
 * @Author alan
 * @Date 2019/7/25 17:03
 * @Describe TODO
 * @Mark
 **/
public class HomeFragment extends BaseFragment implements HomeView {

    public static HomeFragment newInstance(String title){
        Bundle bundle=new Bundle();
        bundle.putString(Consts.BUNDLE_KEY_TAB_TITLE,title);
        HomeFragment tabFragment=new HomeFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarDarkFont(true).navigationBarDarkIcon(true).navigationBarColor(R.color.colorWhite).init();
    }
}
