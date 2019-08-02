package com.yk.mvpframe.fragment;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.yk.mvpframe.R;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.consts.Consts;

/**
 * @FileName TabFragment
 * @Author alan
 * @Date 2019/7/25 17:03
 * @Describe TODO
 * @Mark
 **/
public class PlayFragment extends BaseFragment {

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
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarDarkFont(true).init();
    }
}
