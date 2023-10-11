package com.weare.api.Models;


import com.weare.api.Utils.Constants;


public class Post {
    private String content;
    private String picture;
    private boolean isPublic=true;

    public Post(){
        this.content=Constants.CONTENT_POST;
        this.picture=Constants.PICTURE_POST;
        this.isPublic=isPublic();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}