package com.example.hoangtu.tintuc24h.model;

public class News {
    int imgResource;
    String title;
    String url;

    public News(int imgResource, String title, String url) {
        this.imgResource = imgResource;
        this.title = title;
        this.url = url;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
