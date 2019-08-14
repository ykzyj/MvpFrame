package com.yk.mvpframe.fragment.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;
import com.gyf.immersionbar.ImmersionBar;
import com.yk.mvpframe.R;
import com.yk.mvpframe.activity.login.activity.LoginActivity;
import com.yk.mvpframe.activity.mine.activity.SystemSettingActivity;
import com.yk.mvpframe.base.BaseFragment;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.fragment.main.presenter.MinePresenter;
import com.yk.mvpframe.fragment.main.view.MineView;
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
}
