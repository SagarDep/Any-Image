package com.love_cookies.any_image.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.love_cookies.any_image.R;
import com.love_cookies.any_image.app.AnyImageApplication;
import com.love_cookies.any_image.config.AppConfig;
import com.love_cookies.any_image.model.bean.ImageBean;
import com.love_cookies.any_image.presenter.ImagePresenter;
import com.love_cookies.any_image.view.interfaces.IImageView;
import com.love_cookies.any_image.view.widget.AutoSwipeRefreshLayout;
import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.adapter.CommonRecyclerAdapter;
import com.love_cookies.cookie_library.adapter.CommonRecyclerViewHolder;
import com.love_cookies.cookie_library.adapter.OnRecyclerItemClickListener;
import com.love_cookies.cookie_library.application.ActivityCollections;
import com.love_cookies.cookie_library.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xiekun on 2016/8/15 0015.
 *
 * 主页
 */
@ContentView(R.layout.activity_main)
public class ImageActivity extends BaseActivity implements IImageView {

    private long exitTime;

    @ViewInject(R.id.tool_bar)
    private Toolbar toolbar;
    @ViewInject(R.id.refresh_layout_content)
    private AutoSwipeRefreshLayout refreshLayoutContent;
    @ViewInject(R.id.refresh_layout_empty)
    private SwipeRefreshLayout refreshLayoutEmpty;
    @ViewInject(R.id.recycler_view)
    private RecyclerView recyclerView;
    private CommonRecyclerAdapter recyclerAdapter;

    private List<ImageBean.ImagesBean> images = new ArrayList<>();

    private ImagePresenter imagePresenter = new ImagePresenter(this);

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        recyclerAdapter = new CommonRecyclerAdapter<ImageBean.ImagesBean>(this, R.layout.item_image_recycler_veiw, images) {
            @Override
            public void setData(CommonRecyclerViewHolder holder, ImageBean.ImagesBean imagesBean) {
                x.image().bind((ImageView) holder.getView(R.id.image_view), AppConfig.IMAGE_960x540 + imagesBean.getId(), AnyImageApplication.NormalImageOptions);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", images.get(position).getId());
                turn(DetailActivity.class, bundle);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return true;
            }
        });
        refreshLayoutContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayoutContent.setRefreshing(true);
                getImageList();
            }
        });
        refreshLayoutContent.autoRefresh();
        refreshLayoutEmpty.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayoutEmpty.setRefreshing(true);
                getImageList();
            }
        });
        getImageList();
    }

    /**
     * 控件点击
     *
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
     *
     * @param imageBean
     */
    @Override
    public void getImageListSuccess(ImageBean imageBean) {
        refreshLayoutContent.setRefreshing(false);
        refreshLayoutEmpty.setRefreshing(false);
        refreshLayoutContent.setVisibility(View.VISIBLE);
        refreshLayoutEmpty.setVisibility(View.GONE);
        setImageList(imageBean);
    }

    /**
     * 获取图片列表失败
     */
    @Override
    public void getImageListFailed() {
        refreshLayoutContent.setRefreshing(false);
        refreshLayoutEmpty.setRefreshing(false);
        refreshLayoutContent.setVisibility(View.GONE);
        refreshLayoutEmpty.setVisibility(View.VISIBLE);
    }

    /**
     * 设置图片列表
     *
     * @param imageBean
     */
    @Override
    public void setImageList(ImageBean imageBean) {
        images.clear();
        images.addAll(imageBean.getImages());
        Collections.reverse(images);
        recyclerAdapter.notifyDataSetChanged();
    }

    /**
     * 点两次返回退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {//点击两次退出逻辑
                ToastUtils.show(this, R.string.exit_tip);
                exitTime = System.currentTimeMillis();
            } else {
                ActivityCollections.getInstance().finishAllActivity();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * 菜单选中
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                ToastUtils.show(this, R.string.app_name);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
