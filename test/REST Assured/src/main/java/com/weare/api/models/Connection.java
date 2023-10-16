package com.weare.api.models;

public class Connection {

    private String requestId;


    public Connection(){
        this.requestId = getRequestId();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}