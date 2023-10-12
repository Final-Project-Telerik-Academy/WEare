package com.weare.api.Models;

import com.weare.api.Utils.Constants;

public class Comment {
    private String content;
    private Integer postId;
    private Integer userId;
    public Comment(){
        this.content= Constants.CONTENT_COMMENT;
        this.userId=Constants.USER_ID;
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
