package com.yk.mvpframe.base;

/**
 * @FileName BaseView
 * @Author alan
 * @Date 2019/7/11 9:36
 * @Describe TODO
 * @Mark
 **/
public interface BaseView {
    /**
     * 显示Toast
     */
    void showToast(String s);
    void showToast(int resId);
    /**
     * 显示dialog
     */
    void showLoading();
    void showLoading(String loadingTxt);

    /**
     * 显示下载文件dialog
     */

    void showLoadingFileDialog();

    /**
     * 隐藏下载文件dialog
     */

    void hideLoadingFileDialog();

    /**
     * 下载进度
     */
    void onProgress(int progress);

    /**
     * 隐藏 dialog
     */
    void hideLoading();

    /**
     * 显示错误信息
     */
    void showError(String msg);

    /**
     * 错误码
     */
    void onErrorCode(BaseModel model);
}
