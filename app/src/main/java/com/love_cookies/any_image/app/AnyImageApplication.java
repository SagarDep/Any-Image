package com.love_cookies.any_image.app;

import android.widget.ImageView;

import com.love_cookies.cookie_library.application.BaseApplication;
import com.love_cookies.cookie_library.utils.ScreenUtils;

import org.xutils.image.ImageOptions;

/**
 * Created by xiekun on 2016/8/15 0015.
 *
 * 应用的Application
 */
public class AnyImageApplication extends BaseApplication {

    //一般图片设置
    public static ImageOptions NormalImageOptions = null;
    //圆形图片设置
    public static ImageOptions RoundImageOptions = null;
    //方形圆角图片设置
    public static ImageOptions SquareRadiusImageOptions = null;

    @Override
    public void onCreate() {
        super.onCreate();
        initImageOptions();
    }

    /**
     * 初始化图片设置
     */
    public void initImageOptions() {
        NormalImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();

        RoundImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(true)
                .build();

        SquareRadiusImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setSquare(true)
                .setRadius(ScreenUtils.dp2px(this, 12))
                .build();
    }

}
