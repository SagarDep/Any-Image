package com.love_cookies.any_image.presenter;

import com.love_cookies.any_image.model.biz.DetailBiz;
import com.love_cookies.any_image.view.interfaces.IDetailView;
import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/8/3 0003.
 *
 * 图片详情页Presenter
 */
public class DetailPresenter {

    private DetailBiz detailBiz;
    private IDetailView iDetailView;

    public DetailPresenter(IDetailView iDetailView) {
        detailBiz = new DetailBiz();
        this.iDetailView = iDetailView;
    }

    /**
     * 下载文件
     * @param url
     * @param imagePath
     */
    public void downloadFile(String url, String imagePath) {
        detailBiz.downloadFile(url, imagePath, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iDetailView.downloadFileSuccess();
            }

            @Override
            public void onFailed(Object msg) {
                iDetailView.downloadFileFailed();
            }
        });
    }

    /**
     * 获取壁纸
     * @param url
     * @param imagePath
     */
    public void getWallpaper(String url, String imagePath) {
        detailBiz.downloadFile(url, imagePath, new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iDetailView.getWallpaperSuccess();
            }

            @Override
            public void onFailed(Object msg) {
                iDetailView.getWallpaperFailed();
            }
        });
    }

}
