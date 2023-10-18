package com.weare.api.models;

import com.github.javafaker.Faker;

public class BaseModel {
    protected Faker faker;

    public BaseModel() {
        faker = new Faker();
    }
}
