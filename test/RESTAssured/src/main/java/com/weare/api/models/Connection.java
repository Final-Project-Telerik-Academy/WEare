package com.weare.api.models;

import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
public class Connection {
    private String requestId;

    public Connection(){
        this.requestId = getRequestId();
    }
}