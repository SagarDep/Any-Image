package com.love_cookies.any_image.model.biz;

import com.love_cookies.any_image.model.biz.interfaces.IDetailBiz;
import com.love_cookies.cookie_library.interfaces.CallBack;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by xiekun on 2016/09/21 0021.
 *
 * 图片详情页事件逻辑
 */
public class DetailBiz implements IDetailBiz {

    /**
     * 下载文件
     * @param url
     * @param path
     * @param callBack
     */
    @Override
    public void downloadFile(String url, String path, final CallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        requestParams.setSaveFilePath(path);
        x.http().get(requestParams, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailed(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
