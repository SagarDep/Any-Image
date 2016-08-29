package com.love_cookies.any_image.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.love_cookies.any_image.R;
import com.love_cookies.any_image.app.AnyImageApplication;
import com.love_cookies.any_image.config.AppConfig;
import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.widget.PinchImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by xiekun on 2016/8/29 0029.
 *
 * 图片详情页
 */
@ContentView(R.layout.activity_detail)
public class DetailActivity extends BaseActivity {

    @ViewInject(R.id.image_iv)
    private PinchImageView imageView;

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        x.image().bind(imageView, AppConfig.IMAGE_1920x1080 + getIntent().getExtras().getString("id"), AnyImageApplication.NormalImageOptions);
    }

    /**
     * 控件点击
     * @param view
     */
    @Override
    public void widgetClick(View view) {

    }

}
