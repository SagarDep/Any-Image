package com.love_cookies.any_image.model.biz;

import com.google.gson.Gson;
import com.love_cookies.any_image.config.AppConfig;
import com.love_cookies.any_image.model.bean.ImageBean;
import com.love_cookies.any_image.model.biz.interfaces.IImageBiz;
import com.love_cookies.cookie_library.interfaces.CallBack;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by xiekun on 2016/8/29.
 *
 * 图片逻辑
 */
public class ImageBiz implements IImageBiz {
    /**
     * 获取图片列表
     * @param callBack
     */
    @Override
    public void getImageList(final CallBack callBack) {
        RequestParams requestParams = new RequestParams(AppConfig.IMAGE_API);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    String json_result = "{\"images\":" + result + "}";
                    Gson gson = new Gson();
                    ImageBean imageBean = gson.fromJson(json_result, ImageBean.class);
                    callBack.onSuccess(imageBean);
                } else {
                    callBack.onFailed(null);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailed(null);
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
