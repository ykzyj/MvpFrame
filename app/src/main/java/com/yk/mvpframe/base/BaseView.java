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
     * 显示dialog
     */
    void showLoading();

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
    void onProgress(long totalSize, long downSize);

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
