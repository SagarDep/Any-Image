package com.love_cookies.any_image.model.biz.interfaces;

import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/09/21 0021.
 *
 * 图片详情页事件逻辑接口
 */
public interface IDetailBiz {
    /**
     * 下载文件
     * @param url
     * @param path
     * @param callBack
     */
    void downloadFile(String url, String path, CallBack callBack);
}
