package com.yk.mvpframe.fragment.main.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.login.activity.LoginActivity;
import com.yk.mvpframe.activity.mine.activity.SystemSettingActivity;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.event.LoginEvent;
import com.yk.mvpframe.fragment.main.presenter.MinePresenter;
import com.yk.mvpframe.fragment.main.view.MineView;
import com.yk.mvpframe.util.CacheUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @FileName MineFragment
 * @Author alan
 * @Date 2019/7/25 17:03
 * @Describe TODO
 * @Mark
 **/
public class MineFragment extends BaseFragment<MinePresenter> implements MineView {

    @BindView(R.id.user_logo_cv)
    CardView userLogoCv;
    @BindView(R.id.user_title_tv)
    TextView userTitleTv;
    @BindView(R.id.user_edit_img)
    ImageView userEditImg;
    @BindView(R.id.user_title_des_tv)
    TextView userTitleDesTv;
    @BindView(R.id.user_level_img)
    ImageView userLevelImg;
    @BindView(R.id.user_level_tv)
    TextView userLevelTv;
    @BindView(R.id.user_vip_img)
    ImageView userVipImg;
    @BindView(R.id.user_vip_tv)
    TextView userVipTv;
    @BindView(R.id.user_alert_img)
    ImageView userAlertImg;
    @BindView(R.id.user_alert_tv)
    TextView userAlertTv;
    @BindView(R.id.user_message_tv)
    TextView userMessageTv;
    @BindView(R.id.user_collection_tv)
    TextView userCollectionTv;
    @BindView(R.id.user_group_tv)
    TextView userGroupTv;
    @BindView(R.id.user_trip_tv)
    TextView userTripTv;
    @BindView(R.id.user_setting_tv)
    TextView userSettingTv;
    @BindView(R.id.user_opinion_tv)
    TextView userOpinionTv;
    @BindView(R.id.user_about_tv)
    TextView userAboutTv;
    @BindView(R.id.user_logo_img)
    ImageView userLogoImg;
    @BindView(R.id.user_sign_in_tv)
    TextView userSignInTv;
    @BindView(R.id.user_sign_in_right_img)
    ImageView userSignInRightImg;
    @BindView(R.id.user_qr_code_img)
    ImageView userQrCodeImg;

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
        presenter.addViewClick(userTitleTv, o -> LoginActivity.startLoginActivity(getActivity()));
        presenter.addViewClick(userSettingTv, o -> SystemSettingActivity.startLoginActivity(getActivity()));
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarDarkFont(false).navigationBarDarkIcon(true)
                .navigationBarColor(R.color.colorWhite)
                .init();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        if (event.getLoginFlag() == LoginEvent.LOGIN) {
            userTitleTv.setText(CacheUtils.getUserInfoModel().getAppUser().getNickName());
            userTitleDesTv.setText(CacheUtils.getUserInfoModel().getAppUser().getPhone());
            presenter.getUserImg();
            userEditImg.setVisibility(View.VISIBLE);
            userQrCodeImg.setVisibility(View.VISIBLE);
        } else {
            userTitleTv.setText(getString(R.string.app_mine_login_click));
            userTitleDesTv.setText(getString(R.string.app_mine_login_des));
            userLogoImg.setImageResource(R.mipmap.user_head_default);
            CacheUtils.setToken("");
            CacheUtils.setUserInfoModel(null);
            userEditImg.setVisibility(View.INVISIBLE);
            userQrCodeImg.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showUserImg(Bitmap bitmap) {
        userLogoImg.setImageBitmap(bitmap);
    }

}
