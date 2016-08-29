package com.love_cookies.any_image.model.biz.interfaces;

import com.love_cookies.cookie_library.interfaces.CallBack;

/**
 * Created by xiekun on 2016/8/29.
 *
 * 图片逻辑接口
 */
public interface IImageBiz {
    /**
     * 获取图片列表数量
     * @param callBack
     */
    void getImageList(CallBack callBack);
}
