package com.weare.api.models;
import com.weare.api.utils.Constants;

import lombok.Data;

    @Data
public class User extends BaseModel {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String authority;
    private Integer categoryId;
    private String categoryName;
    private String birthYear;

    public User(){
        this.userId = getUserId();
        this.username = randomUsername();
        this.password = Constants.PASSWORD;
        this.email = getRandomEmail();
        this.firstName = getRandomFirstName();
        this.lastName = getRandomLastName();
        this.authority = Constants.AUTHORITIES_USER;
        this.categoryId = Constants.CATEGORY_ID;
        this.birthYear = Constants.birthYear;
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
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
