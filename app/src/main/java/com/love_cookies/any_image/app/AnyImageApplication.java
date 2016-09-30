package com.love_cookies.any_image.app;

import android.widget.ImageView;

import com.love_cookies.cookie_library.application.BaseApplication;
import com.love_cookies.cookie_library.utils.SDCardUtils;
import com.love_cookies.cookie_library.utils.ScreenUtils;

import org.xutils.image.ImageOptions;

import java.io.File;

/**
 * Created by xiekun on 2016/8/15 0015.
 *
 * 应用的Application
 */
public class AnyImageApplication extends BaseApplication {

    //一般图片设置
    public static ImageOptions NormalImageOptions = null;
    //一般图片设置-淡入
    public static ImageOptions NormalFadeInImageOptions = null;
    //圆形图片设置
    public static ImageOptions RoundImageOptions = null;
    //方形圆角图片设置
    public static ImageOptions SquareRadiusImageOptions = null;

    //图片文件夹路径
    public static final String FILE_PATH = SDCardUtils.getSDCardPath() + "Any_Image/Images/";

    @Override
    public void onCreate() {
        super.onCreate();
        initImageOptions();
        initFolder();
    }

    /**
     * 初始化图片设置
     */
    public void initImageOptions() {
        NormalImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();

        NormalFadeInImageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFadeIn(true)
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

    /**
     * 初始化文件夹
     */
    public void initFolder() {
        File dir = new File(FILE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
