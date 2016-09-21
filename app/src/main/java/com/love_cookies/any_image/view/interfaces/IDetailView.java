package com.love_cookies.any_image.view.interfaces;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页 View接口
 */
public interface IDetailView {

    /**
     * 下载文件成功
     */
    void downloadFileSuccess();

    /**
     * 下载文件失败
     */
    void downloadFileFailed();

    /**
     * 获取壁纸成功
     */
    void getWallpaperSuccess();

    /**
     * 获取壁纸失败
     */
    void getWallpaperFailed();

}
