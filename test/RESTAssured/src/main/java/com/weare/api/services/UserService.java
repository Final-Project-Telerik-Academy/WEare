package com.weare.api.services;

import com.weare.api.models.Skill;
import com.weare.api.models.User;
import com.weare.api.utils.Constants;
import com.weare.api.utils.JSONRequests;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService {
    private UserService() {};

    public static String registrationRequest(User user) {
        return String.format(JSONRequests.REGISTRATION,
                user.getAuthority(), user.getCategoryId(), user.getCategoryName(),
                user.getPassword(), user.getEmail(), user.getPassword(), user.getUsername());
    }

    public static String updateProfileRequest(User user) {
        return String.format(JSONRequests.UPDATE_PERSONAL_PROFILE,
                user.getBirthYear(), user.getFirstName(), user.getUserId(), user.getLastName(),
                Constants.CITY, Constants.CITY_ID);
    }

    public static String searchUserRequest(User user) {
        return String.format(JSONRequests.SEARCH_USER, "Ivan Ivanov");
    }

    public static String updateExpertiseProfileRequest(User user, Skill skill) {
        return String.format(JSONRequests.UPDATE_USER_EXPERTISE,
                Constants.AVAILABILITY, user.getCategoryId(), user.getUserId(), skill.getName());
    }

    public static Response searchByUser(String searchUserBody) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(searchUserBody)
                .when()
                .post();
    }

    public static Response getUserById(Cookie cookie, String username) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("principal", username)
                .when()
                .get();
    }

    public static Response createPost(String postJsonBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();
    }

    public static Response searchUserPosts(String postJsonBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(postJsonBody)
                .when()
                .get();
    }

    public static Response updateUserExpertise(String updateExpertiseBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateExpertiseBody)
                .when()
                .post();
    }

    public static Response updatePersonalProfile(String updateExpertiseBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateExpertiseBody)
                .when()
                .post();
    }
}
