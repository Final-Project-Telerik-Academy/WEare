package com.weare.api.models;

import com.weare.api.utils.Constants;
import lombok.Data;

    @Data
public class Comment extends BaseModel {
    private String content;
    private Integer postId;
    private Integer userId;
    private boolean like;

    public Comment(){
        this.content= faker.lorem().sentence();
        this.postId=Constants.POST_ID;
    }
}
