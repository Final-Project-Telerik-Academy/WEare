package com.weare.api.models;


import com.github.javafaker.Faker;
import com.weare.api.utils.Constants;
import lombok.Data;

    @Data
public class Post extends  BaseModel {
    private String content;
    private String picture;
    private boolean isPublic;
    Faker faker = new Faker();

    public Post(){
        this.content=faker.lorem().sentence();
        this.picture=Constants.PICTURE_POST;
        this.isPublic=isPublic();
    }
}