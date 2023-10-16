package com.weare.api.models;

import com.github.javafaker.Faker;
import com.weare.api.utils.Constants;
import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
public class Comment {
    private String content;
    private Integer postId;
    private Integer userId;

    Faker faker = new Faker();

    public Comment(){
        this.content= faker.lorem().sentence();
        this.postId=Constants.POST_ID;
    }
}
