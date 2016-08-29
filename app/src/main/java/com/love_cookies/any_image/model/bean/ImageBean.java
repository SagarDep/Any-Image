package com.love_cookies.any_image.model.bean;

import java.util.List;

/**
 * Created by xiekun on 2016/8/29.
 *
 * 图片资源实体类
 */
public class ImageBean {

    /**
     * format : jpeg
     * width : 5616
     * height : 3744
     * filename : 51492538696.jpg
     * id : 0
     * author : Alejandro Escamilla
     * author_url : https://alejandroescamilla.com/
     * post_url : https://unsplash.com/post/51492538696/download-by-alejandro-escamilla
     */

    private List<ImagesBean> images;

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        private String format;
        private int width;
        private int height;
        private String filename;
        private String id;
        private String author;
        private String author_url;
        private String post_url;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor_url() {
            return author_url;
        }

        public void setAuthor_url(String author_url) {
            this.author_url = author_url;
        }

        public String getPost_url() {
            return post_url;
        }

        public void setPost_url(String post_url) {
            this.post_url = post_url;
        }
    }

}
