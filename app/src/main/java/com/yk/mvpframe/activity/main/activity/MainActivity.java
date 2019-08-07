package com.yk.mvpframe.activity.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.jakewharton.rxbinding3.view.RxView;
import com.orhanobut.logger.Logger;
import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.main.presenter.MainPresenter;
import com.yk.mvpframe.activity.main.view.MainView;
import com.yk.mvpframe.base.BaseActivity;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.fragment.main.fragment.EatFragment;
import com.yk.mvpframe.fragment.main.fragment.HomeFragment;
import com.yk.mvpframe.fragment.main.fragment.MineFragment;
import com.yk.mvpframe.fragment.main.fragment.PlayFragment;
import com.yk.mvpframe.widget.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @FileName MainActivity
 * @Author alan
 * @Date 2019/7/20 11:03
 * @Describe TODO
 * @Mark
 **/
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.home_tab)
    TabView homeTab;
    @BindView(R.id.home_eat)
    TabView homeEat;
    @BindView(R.id.home_play)
    TabView homePlay;
    @BindView(R.id.home_mine)
    TabView homeMine;


    private List<String> title_ls = new ArrayList<>(Arrays.asList("主页", "吃喝", "玩乐", "我的"));

    private SparseArray<BaseFragment> mFragments = new SparseArray<>();

    private List<TabView> mTabButs = new ArrayList<>();

    private int mCurrentPosition=0;

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDataBeforeView(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            mCurrentPosition=savedInstanceState.getInt(Consts.BUNDLE_KEY_TAB_POSITION,0);
        }
    }

    @Override
    protected void initView() {
        mTabButs.add(homeTab);
        mTabButs.add(homeEat);
        mTabButs.add(homePlay);
        mTabButs.add(homeMine);

        setCurrentTab(mCurrentPosition);

        mainVp.setOffscreenPageLimit(title_ls.size());

        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(0==i){
                    return HomeFragment.newInstance(title_ls.get(i));
                }
                else if(1==i){
                    return EatFragment.newInstance(title_ls.get(i));
                }
                else if(2==i){
                    return PlayFragment.newInstance(title_ls.get(i));
                }
                else{
                    return MineFragment.newInstance(title_ls.get(i));
                }
            }

            @Override
            public int getCount() {
                return title_ls.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
                mFragments.put(position, fragment);
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });
    }

    @Override
    protected void setListener() {
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int i1) {
                Logger.i("position:" + position + "***positionOffset:" + positionOffset);
                if (positionOffset > 0) {
                    TabView leftBut = mTabButs.get(position);
                    TabView rightBut = mTabButs.get(position + 1);

                    leftBut.setProgress((1 - positionOffset));
                    rightBut.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition=position;
                if(mFragments!=null&&mFragments.get(position)!=null){
                    mFragments.get(position).initImmersionBar();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for(int i=0;i<mTabButs.size();i++){
            TabView tabView=mTabButs.get(i);
            int finalI = i;
            presenter.addDisposable(RxView.clicks(tabView)
                    .throttleFirst(500, TimeUnit.MILLISECONDS)
                    .subscribe(o -> {
                        mainVp.setCurrentItem(finalI,false);
                        setCurrentTab(finalI);
                        mFragments.get(finalI).initImmersionBar();
                    }));
        }
    }

    private void setCurrentTab(int position) {
        for(int i=0;i<mTabButs.size();i++){
            TabView tabView=mTabButs.get(i);
            if(i==position){
                tabView.setProgress(1);
            }
            else {
                tabView.setProgress(0);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Consts.BUNDLE_KEY_TAB_POSITION,mainVp.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mCurrentPosition==0){
            ImmersionBar.with(this).statusBarDarkFont(true).navigationBarDarkIcon(true).navigationBarColor(R.color.colorWhite).init();
        }
        else if(mCurrentPosition==3){
            ImmersionBar.with(this).statusBarDarkFont(false).navigationBarDarkIcon(true)
                    .navigationBarColor(R.color.colorWhite)
                    .titleBar(getToolbar())
                    .init();
        }
    }
}
