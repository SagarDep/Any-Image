package com.love_cookies.any_image.view.activity;

import android.os.Bundle;
import android.view.View;

import com.love_cookies.any_image.R;
import com.love_cookies.any_image.model.bean.ImageBean;
import com.love_cookies.any_image.presenter.ImagePresenter;
import com.love_cookies.any_image.view.interfaces.IImage;
import com.love_cookies.cookie_library.activity.BaseActivity;

import org.xutils.view.annotation.ContentView;

/**
 * Created by xiekun on 2016/8/15 0015.
 *
 * 主页
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements IImage {

    private ImagePresenter imagePresenter = new ImagePresenter(this);

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        getImageList();
    }

    /**
     * 控件点击
     * @param view
     */
    @Override
    public void widgetClick(View view) {

    }

    /**
     * 获取图片列表
     */
    @Override
    public void getImageList() {
        imagePresenter.getImageList();
    }

    /**
     * 获取图片列表成功
     * @param imageBean
     */
    @Override
    public void getImageListSuccess(ImageBean imageBean) {

    }

    /**
     * 获取图片列表失败
     */
    @Override
    public void getImageListFailed() {

    }
}
