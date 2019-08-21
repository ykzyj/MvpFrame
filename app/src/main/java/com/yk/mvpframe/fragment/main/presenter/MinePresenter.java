package com.yk.mvpframe.fragment.main.presenter;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.yk.mvpframe.R;
import com.yk.mvpframe.base.BaseObserver;
import com.yk.mvpframe.base.BasePresenter;
import com.yk.mvpframe.base.BitmapObserver;
import com.yk.mvpframe.consts.Consts;
import com.yk.mvpframe.event.LoginEvent;
import com.yk.mvpframe.fragment.main.view.MineView;
import com.yk.mvpframe.model.UserInfoModel;
import com.yk.mvpframe.util.AsciiUtils;
import com.yk.mvpframe.util.CacheUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @FileName MinePresenter
 * @Author alan
 * @Date 2019/8/6 9:58
 * @Describe TODO
 * @Mark
 **/
public class MinePresenter extends BasePresenter<MineView> {
    public MinePresenter(MineView baseView) {
        super(baseView);
    }

    public void getUserImg() {
        addDisposable(apiServer.getUserImg(CacheUtils.getUserInfoModel().getAppUser().getId()+"")
                ,new BaseObserver(baseView,false) {
                    @Override
                    public void onSuccess(Object o) {
                        if(o!=null){
                            baseView.showUserImg((Bitmap) o);
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });
    }
}
