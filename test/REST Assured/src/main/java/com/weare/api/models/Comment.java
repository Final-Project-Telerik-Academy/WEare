package com.weare.api.models;

import com.github.javafaker.Faker;
import com.weare.api.utils.Constants;

public class Comment {
    private String content;
    private Integer postId;
    private Integer userId;
    Faker faker = new Faker();
    public Comment(){
        this.content= faker.lorem().sentence();
      //  this.userId=Constants.USER_ID;
        this.postId=Constants.POST_ID;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}
