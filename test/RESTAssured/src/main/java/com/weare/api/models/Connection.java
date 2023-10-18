package com.weare.api.models;

import lombok.Data;

    @Data
public class Connection extends BaseModel {
    private String requestId;

    public Connection(){
        this.requestId = getRequestId();
    }
}