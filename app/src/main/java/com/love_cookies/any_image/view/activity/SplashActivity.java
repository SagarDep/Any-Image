package com.love_cookies.any_image.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.love_cookies.any_image.R;
import com.love_cookies.any_image.app.AnyImageApplication;
import com.love_cookies.cookie_library.activity.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Side;
import su.levenetc.android.textsurface.contants.TYPE;

/**
 * Created by xiekun on 2016/09/28 0028.
 *
 * App启动页
 */
@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @ViewInject(R.id.splash_image)
    private ImageView splashImage;
    @ViewInject(R.id.text_surface)
    private TextSurface textSurface;

    private final int SPLASH_DISPLAY_DURATION = 3000;//启动页显示时长
    private Looper looper = Looper.myLooper();
    private Handler handler = new Handler(looper);
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            turnThenFinish(ImageActivity.class);
        }
    };

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        x.image().bind(splashImage, "assets://splash_bg.jpg", AnyImageApplication.NormalFadeInImageOptions);
        setTitleAnimation();
        handler.postDelayed(runnable, SPLASH_DISPLAY_DURATION);
    }

    /**
     * 控件点击事件
     * @param v
     */
    @Override
    public void widgetClick(View v) {

    }

    /**
     * 设置标题动画
     */
    public void setTitleAnimation() {
        Text textOne = TextBuilder
                .create(getString(R.string.app_name))
                .setSize(65)
                .setAlpha(0)
                .setColor(Color.WHITE)
                .setPosition(Align.SURFACE_CENTER)
                .build();

        Text textTwo = TextBuilder
                .create(getString(R.string.splash_from_text))
                .setSize(15)
                .setAlpha(100)
                .setColor(Color.WHITE)
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textOne)
                .build();

        textSurface.play(
                TYPE.SEQUENTIAL,
                Alpha.show(textOne, 800),
                Slide.showFrom(Side.TOP, textTwo, 1200)
        );
    }

    /**
     * 重写物理按键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            handler.removeCallbacks(runnable);
        }
        return super.onKeyDown(keyCode, event);
    }

}
