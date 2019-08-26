package com.yk.mvpframe.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yk.mvpframe.R;
import com.yk.mvpframe.event.PermissionGrantedListener;
import com.yk.mvpframe.widget.CustomDialog;

import io.reactivex.functions.Consumer;

/**
 * @FileName PermissionsUtils
 * @Author alan
 * @Date 2019/8/26 9:53
 * @Describe 权限申请静态类
 * @Mark
 **/
public class PermissionsUtils {

    /**
     * 权限申请
     * @param context
     * @param permissionGrantedListener
     * @param permissions
     */
    public static void requestCommonPermissions(Context context,
                                                PermissionGrantedListener permissionGrantedListener,
                                                String... permissions) {
        String strNames="";
        for(String str:permissions){
            str=str.replace(".","_");
            int stringId=context.getResources().getIdentifier(str,"string","com.yk.mvpframe");
            String name=context.getResources().getString(stringId);
            if(!TextUtils.isEmpty(name)&&!strNames.contains(name)){
                strNames=strNames+name+",";
            }
        }
        strNames=strNames.substring(0,strNames.length()-1);
        String finalStrNames = strNames;
        rxPermissionRequest(context, permissionGrantedListener, finalStrNames, permissions);
    }

    /**
     * rxPermission权限申请
     * @param context
     * @param permissionGrantedListener
     * @param finalStrNames
     * @param permissions
     */
    private static void rxPermissionRequest(Context context,
                                            PermissionGrantedListener permissionGrantedListener,
                                            String finalStrNames, String... permissions) {
        RxPermissions rxPermission = new RxPermissions((FragmentActivity) context);
        rxPermission.requestEachCombined(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            Logger.d(permission.name + " is granted.");
                            permissionGrantedListener.onPermissionGranted();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            Logger.d( permission.name + " is denied. More info should be provided.");
                            Toast.makeText(context,
                                    "当前功能需要被授权"+ finalStrNames +"权限才能正常使用。", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Logger.d( permission.name + " is denied.");
                            showToSettingDialog(context, finalStrNames);
                        }
                    }
                });
    }

    /**
     * 去往设置页面登录
     * @param context
     * @param finalStrNames
     */
    private static void showToSettingDialog(Context context, String finalStrNames) {
        CustomDialog setPermissionDialog=new CustomDialog(context);
        setPermissionDialog.setTitle("权限设置");
        setPermissionDialog.setMessage("当前功能需要被授权"+ finalStrNames +"权限才能正常使用，现在前往设置中授权所需权限嘛？");
        setPermissionDialog.setOnOkClickListener(context.getString(R.string.app_ok), new CustomDialog.onOkClickListener() {
            @Override
            public void onOkClick() {
                if(setPermissionDialog!=null){
                    setPermissionDialog.dismiss();
                }
                Intent mIntent = new Intent();
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    mIntent.setAction(Intent.ACTION_VIEW);
                    mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                    mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
                }
                context.startActivity(mIntent);
            }
        });
        setPermissionDialog.setOnCancelClickListener(context.getString(R.string.app_cancel), new CustomDialog.onCancelClickListener() {
            @Override
            public void onCancelClick() {
                if(setPermissionDialog!=null){
                    setPermissionDialog.dismiss();
                }
            }
        });
        setPermissionDialog.show();
    }
}
