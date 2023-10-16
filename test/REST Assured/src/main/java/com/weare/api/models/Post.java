package com.weare.api.models;


import com.github.javafaker.Faker;
import com.weare.api.utils.Constants;
import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
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
}