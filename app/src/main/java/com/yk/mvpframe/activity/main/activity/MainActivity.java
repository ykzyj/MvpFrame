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
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.main.presenter.MainPresenter;
import com.yk.mvpframe.activity.main.view.MainView;
import com.yk.mvpframe.base.BaseActivity;
import com.yk.mvpframe.fragment.TabFragment;
import com.yk.mvpframe.widget.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.home_tab)
    TabView homeBut;
    @BindView(R.id.home_eat)
    TabView homeEat;
    @BindView(R.id.home_play)
    TabView homePlay;
    @BindView(R.id.home_mine)
    TabView homeMine;

    private List<String> title_ls = new ArrayList<>(Arrays.asList("主页", "吃喝", "玩乐", "我的"));

    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    private List<TabView> mTabButs = new ArrayList<>();

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
    protected void initView() {
        mTabButs.add(homeBut);
        mTabButs.add(homeEat);
        mTabButs.add(homePlay);
        mTabButs.add(homeMine);

        homeBut.setProgress(1);

        mainVp.setOffscreenPageLimit(title_ls.size());
        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return TabFragment.newInstance(title_ls.get(i));
            }

            @Override
            public int getCount() {
                return title_ls.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TabFragment fragment = (TabFragment) super.instantiateItem(container, position);
                mFragments.put(position, fragment);
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });

        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int i1) {
                Logger.e("position:" + position + "***positionOffset:" + positionOffset);
                if (positionOffset > 0) {
                    TabView leftBut = mTabButs.get(position);
                    TabView rightBut = mTabButs.get(position + 1);

                    leftBut.setProgress((1 - positionOffset));
                    rightBut.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /* presenter.addDisposable(RxView.clicks(homeBut)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(o -> {
                    TabFragment fragment=mFragments.get(0);
                    if(fragment!=null){
                        fragment.changeTitle("主页改变了");
                    }
        }));*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
