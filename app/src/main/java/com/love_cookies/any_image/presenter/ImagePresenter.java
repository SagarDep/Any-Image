package com.love_cookies.any_image.presenter;

import com.love_cookies.any_image.model.biz.ImageBiz;
import com.love_cookies.any_image.view.interfaces.IImage;
import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/8/29.
 *
 * 图片Presenter
 */
public class ImagePresenter {

    private ImageBiz imageBiz;
    private IImage iImage;

    public ImagePresenter(IImage iImage) {
        imageBiz = new ImageBiz();
        this.iImage = iImage;
    }

    public void getImageList() {
        imageBiz.getImageList(new CallBack() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailed(Object msg) {

            }
        });
    }
}
