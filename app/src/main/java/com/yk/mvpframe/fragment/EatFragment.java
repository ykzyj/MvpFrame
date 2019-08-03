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
public class EatFragment extends BaseFragment {

    public static EatFragment newInstance(String title){
        Bundle bundle=new Bundle();
        bundle.putString(Consts.BUNDLE_KEY_TAB_TITLE,title);
        EatFragment tabFragment=new EatFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_eat;
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarDarkFont(true).navigationBarDarkIcon(true).navigationBarColor(R.color.colorWhite).init();
    }
}
