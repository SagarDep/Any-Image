package com.love_cookies.any_image.view.activity;

import android.os.Bundle;
import android.view.View;

import com.love_cookies.any_image.R;
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

    @Override
    public void initWidget(Bundle savedInstanceState) {
        getImageList();
    }

    @Override
    public void widgetClick(View view) {

    }

    @Override
    public void getImageList() {
        imagePresenter.getImageList();
    }
}
