package com.yk.mvpframe.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.yk.mvpframe.R;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.consts.Consts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @FileName TabFragment
 * @Author alan
 * @Date 2019/7/25 17:03
 * @Describe TODO
 * @Mark
 **/
public class HomeFragment extends BaseFragment {

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
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarDarkFont(true).init();
    }
}
