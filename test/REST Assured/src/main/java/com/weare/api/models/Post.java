package com.weare.api.models;


import com.github.javafaker.Faker;
import com.weare.api.utils.Constants;


public class Post {
    private String content;
    private String picture;
    private boolean isPublic;
    Faker faker = new Faker();

    public Post(){
        this.content=faker.lorem().sentence();
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