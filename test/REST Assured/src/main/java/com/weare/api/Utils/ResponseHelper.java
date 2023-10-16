package com.weare.api.Utils;
import com.weare.api.Models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ResponseHelper {

    public static Response updatePersonalProfile(String updateUserBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateUserBody)
                .when()
                .post();
    }

    public static Response getUserById(Cookie cookie, User user) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("principal", user.getUsername())
                .when()
                .get();
    }

    public static Response searchByUser(String searchUserBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(searchUserBody)
                .when()
                .post();
    }

    public static Response createPost(String postJsonBody, Cookie cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();
    }

    public static Response searchUserPosts(String postJsonBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(postJsonBody)
                .when()
                .get();
    }

    public static Response updateUserExpertise(String updateUserExpertiseBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateUserExpertiseBody)
                .when()
                .post();
    }
}
