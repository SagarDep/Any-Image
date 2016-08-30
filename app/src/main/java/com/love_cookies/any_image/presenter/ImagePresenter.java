package com.love_cookies.any_image.presenter;

import com.love_cookies.any_image.model.bean.ImageBean;
import com.love_cookies.any_image.model.biz.ImageBiz;
import com.love_cookies.any_image.view.interfaces.IImageView;
import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/8/29.
 *
 * 图片Presenter
 */
public class ImagePresenter {

    private ImageBiz imageBiz;
    private IImageView iImageView;

    public ImagePresenter(IImageView iImageView) {
        imageBiz = new ImageBiz();
        this.iImageView = iImageView;
    }

    /**
     * 获取图片列表
     */
    public void getImageList() {
        imageBiz.getImageList(new CallBack() {
            @Override
            public void onSuccess(Object result) {
                iImageView.getImageListSuccess((ImageBean)result);
            }

            @Override
            public void onFailed(Object msg) {
                iImageView.getImageListFailed();
            }
        });
    }
}
