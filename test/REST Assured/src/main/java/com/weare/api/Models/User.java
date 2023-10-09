package com.weare.api.Models;

import com.weare.api.Utils.Constants;
import com.weare.api.Utils.JSONRequests;

import com.github.javafaker.Faker;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String authority;
    private String categoryId;
    private String categoryName;

    private static final Faker faker = new Faker();

    public User(){
        this.username = faker.name().username();
        this.password = Constants.PASSWORD;
        this.email = faker.internet().emailAddress();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.authority = Constants.AUTHORITIES_USER;
        this.categoryId = Constants.CATEGORY_ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String toJsonRequest() {
        return String.format(JSONRequests.REGISTRATION,
                Constants.AUTHORITIES_USER, this.categoryId, this.categoryName,
                this.password, this.email, this.password, this.username);
    }
}
