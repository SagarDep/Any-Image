package com.love_cookies.any_image.view.activity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.love_cookies.any_image.R;
import com.love_cookies.any_image.app.AnyImageApplication;
import com.love_cookies.any_image.config.AppConfig;
import com.love_cookies.any_image.presenter.DetailPresenter;
import com.love_cookies.any_image.view.interfaces.IDetailView;
import com.love_cookies.any_image.view.widget.AutoSwipeRefreshLayout;
import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.utils.ScreenUtils;
import com.love_cookies.cookie_library.utils.ToastUtils;
import com.love_cookies.cookie_library.widget.PinchImageView;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * Created by xiekun on 2016/8/29 0029.
 *
 * 图片详情页
 */
@ContentView(R.layout.activity_detail)
public class DetailActivity extends BaseActivity implements IDetailView {

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

    private DetailPresenter detailPresenter = new DetailPresenter(this);

    private String imageUrl = "";
    private String imagePath = "";

    private static final int WALLPAPER_SUCCESS = 0x01;
    private static final int WALLPAPER_FAILED = 0x02;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WALLPAPER_SUCCESS:
                    ToastUtils.show(DetailActivity.this, R.string.wallpaper_success);
                    break;
                case WALLPAPER_FAILED:
                    ToastUtils.show(DetailActivity.this, R.string.wallpaper_failed);
                    break;
                default:
                    break;
            }
            homeBtn.setEnabled(true);
            loadingLayout.setRefreshing(false);
        }
    };

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageUrl = AppConfig.IMAGE_1920x1080 + getIntent().getExtras().getString("id");
        imagePath = AnyImageApplication.FILE_PATH + getIntent().getExtras().getString("id") + ".jpg";
        closeBtn.setOnClickListener(this);
        //定位到屏幕中央
        loadingLayout.setProgressViewOffset(false, ScreenUtils.getScreenHeight(this) / 2, ScreenUtils.getScreenHeight(this) / 2);
        loadingLayout.autoRefresh();
        x.image().loadDrawable(imageUrl, AnyImageApplication.NormalImageOptions, new Callback.CommonCallback<Drawable>() {
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
                saveBtn.setEnabled(false);
                loadingLayout.setRefreshing(true);
                downloadFile(imageUrl);
                break;
            case R.id.home_btn:
                homeBtn.setEnabled(false);
                loadingLayout.setRefreshing(true);
                getAndSetWallpaper(imageUrl);
                break;
            default:
                break;
        }
    }

    /**
     * 下载文件
     * @param url
     */
    public void downloadFile(String url) {
        File file = new File(imagePath);
        if (!file.exists()) {
            detailPresenter.downloadFile(url, imagePath);
        } else {
            saveBtn.setEnabled(true);
            loadingLayout.setRefreshing(false);
            ToastUtils.show(DetailActivity.this, String.format(getString(R.string.image_exists), imagePath));
        }
    }

    /**
     * 获取设置壁纸的资源
     */
    public void getAndSetWallpaper(String url) {
        File file = new File(imagePath);
        if (!file.exists()) {
            detailPresenter.getWallpaper(url, imagePath);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            setPhoneWallpaper(bitmap);
        }
    }

    /**
     * 设置为壁纸
     * @param bitmap
     */
    public void setPhoneWallpaper(final Bitmap bitmap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(DetailActivity.this);
                    wallpaperManager.setBitmap(bitmap);
                    message.what = WALLPAPER_SUCCESS;
                } catch (Exception e) {
                    message.what = WALLPAPER_FAILED;
                } finally {
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    /**
     * 下载文件成功
     */
    @Override
    public void downloadFileSuccess() {
        saveBtn.setEnabled(true);
        loadingLayout.setRefreshing(false);
        ToastUtils.show(DetailActivity.this, String.format(getString(R.string.image_save_success), imagePath));
    }

    /**
     * 下载文件失败
     */
    @Override
    public void downloadFileFailed() {
        saveBtn.setEnabled(true);
        loadingLayout.setRefreshing(false);
        ToastUtils.show(DetailActivity.this, R.string.image_save_failed);
    }

    /**
     * 获取壁纸成功
     */
    @Override
    public void getWallpaperSuccess() {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        setPhoneWallpaper(bitmap);
    }

    /**
     * 获取壁纸失败
     */
    @Override
    public void getWallpaperFailed() {
        homeBtn.setEnabled(true);
        loadingLayout.setRefreshing(false);
        ToastUtils.show(DetailActivity.this, R.string.wallpaper_failed);
    }

}
