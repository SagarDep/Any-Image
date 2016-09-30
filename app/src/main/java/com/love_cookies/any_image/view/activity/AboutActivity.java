package com.love_cookies.any_image.view.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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
    @ViewInject(R.id.version_tv)
    private TextView versionTV;

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        toolbar.setTitle(R.string.about_title);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        versionTV.setText(getVersion());
    }

    /**
     * 获取版本信息
     * @return
     */
    public String getVersion() {
        String version = "";
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_CONFIGURATIONS);
            version = packageInfo.versionName;
        }catch (Exception ex) {
            version = "";
        }
        return version;
    }

    /**
     * 控件点击
     * @param view
     */
    @Override
    public void widgetClick(View view) {

    }
}
