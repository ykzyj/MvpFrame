package com.yk.mvpframe.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * @FileName ToastUtils
 * @Author alan
 * @Date 2019/7/11 16:44
 * @Describe TODO
 * @Mark
 **/
public class ToastUtils {
    private static Toast mToast;

    public static void init(Context context) {
        mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
    }

    public static void show(int resId) {
        mToast.setText(resId);
        mToast.show();
    }

    public static void show(CharSequence charSequence) {
        mToast.setText(charSequence);
        mToast.show();
    }
}
