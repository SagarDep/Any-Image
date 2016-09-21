package com.love_cookies.any_image.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.love_cookies.any_image.R;
import com.love_cookies.any_image.app.AnyImageApplication;
import com.love_cookies.any_image.config.AppConfig;
import com.love_cookies.any_image.view.widget.AutoSwipeRefreshLayout;
import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.utils.ScreenUtils;
import com.love_cookies.cookie_library.utils.ToastUtils;
import com.love_cookies.cookie_library.widget.PinchImageView;

import org.xutils.common.Callback;
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

    @ViewInject(R.id.close_btn)
    private ImageView closeBtn;
    @ViewInject(R.id.save_btn)
    private ImageView saveBtn;
    @ViewInject(R.id.home_btn)
    private ImageView homeBtn;
    @ViewInject(R.id.loading_layout)
    private AutoSwipeRefreshLayout loadingLayout;
    @ViewInject(R.id.image_iv)
    private PinchImageView imageView;

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        closeBtn.setOnClickListener(this);
        //定位到屏幕中央
        loadingLayout.setProgressViewOffset(false, ScreenUtils.getScreenHeight(this) / 2, ScreenUtils.getScreenHeight(this) / 2);
        loadingLayout.autoRefresh();
        x.image().loadDrawable(AppConfig.IMAGE_1920x1080 + getIntent().getExtras().getString("id"), AnyImageApplication.NormalImageOptions, new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {
                imageView.setImageDrawable(result);
                saveBtn.setOnClickListener(DetailActivity.this);
                homeBtn.setOnClickListener(DetailActivity.this);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtils.show(DetailActivity.this, ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                loadingLayout.setRefreshing(false);
                //设为不可用，以防下拉会调用刷新
                loadingLayout.setEnabled(false);
            }
        });
    }

    /**
     * 控件点击
     * @param view
     */
    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                finish();
                break;
            case R.id.save_btn:
                ToastUtils.show(this, "save");
                break;
            case R.id.home_btn:
                ToastUtils.show(this, "home");
                break;
            default:
                break;
        }
    }

}
