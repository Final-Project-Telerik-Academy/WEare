package com.weare.api.Models;

import com.weare.api.Utils.Constants;

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
        this.username = randomUsername();
        this.password = Constants.PASSWORD;
        this.email = getRandomEmail();
        this.firstName = getRandomFirstName();
        this.lastName = getRandomLastName();
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

    private String getRandomFirstName() {
        return faker.name().firstName();
    }

    private String getRandomLastName() {
        return faker.name().lastName();
    }

    private String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    private String randomUsername() {
        String generatedUsername = faker.name().username().replace(".", "");
        if (!isValidUsername(generatedUsername)) {
            throw new IllegalArgumentException("Invalid username. Only alphabetic characters are allowed with no whitespaces or dots.");
        }
        return generatedUsername;
    }
    private boolean isValidUsername(String username) {
        for (char c : username.toCharArray()) {
            if (Character.isWhitespace(c) || c == '.') {
                return false;
            }
        }

        return username.matches("^[a-zA-Z]+$");
    }
}
