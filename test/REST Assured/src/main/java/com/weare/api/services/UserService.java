package com.weare.api.services;

import com.google.gson.Gson;
import com.weare.api.models.User;
import com.weare.api.models.Skill;
import com.weare.api.utils.Constants;
import com.weare.api.utils.JSONRequests;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserService {
    private UserService() {};

    public static String generateRegistrationRequest(User user) {
        return String.format(JSONRequests.REGISTRATION,
                user.getAuthority(), user.getCategoryId(), user.getCategoryName(),
                user.getPassword(), user.getEmail(), user.getPassword(), user.getUsername());
    }

    public static String generateUpdatePersonalProfile(User user) {
        return String.format(JSONRequests.UPDATE_PERSONAL_PROFILE,
                Constants.birthYear, user.getFirstName(), user.getUserId(), user.getLastName(),
                Constants.CITY, Constants.CITY_ID);
    }

    public static String generateSearchUserRequest(User user) {
        return String.format(JSONRequests.SEARCH_USER, user.getFullName());
    }

    public static String generateUpdateExpertiseProfile(User user, Skill skill) {
        return String.format(JSONRequests.UPDATE_USER_EXPERTISE,
                Constants.AVAILABILITY, user.getCategoryId(), user.getUserId(), skill.getName());
    }

    public static Response searchByUserApi(String searchUserBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(searchUserBody)
                .when()
                .post();
    }

    public static Response getUserByIdApi(Cookie cookie, String username) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("principal", username)
                .when()
                .get();
    }

    public static Response createPostApi(String postJsonBody, Cookie cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();
    }

    public static Response searchUserPostsApi(String postJsonBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(postJsonBody)
                .when()
                .get();
    }

    public static Response updateUserExpertiseApi(String updateUserExpertiseBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateUserExpertiseBody)
                .when()
                .post();
    }
}
