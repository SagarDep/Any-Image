package com.love_cookies.any_image.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.love_cookies.any_image.R;
import com.love_cookies.cookie_library.activity.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by xiekun on 2016/9/30 0030.
 *
 * 关于页
 */
@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity {

    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        toolbar.setTitle(R.string.about_title);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
    }

    /**
     * 控件点击
     * @param view
     */
    @Override
    public void widgetClick(View view) {

    }
}
