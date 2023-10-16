package com.weare.api.Utils;
import com.weare.api.Models.*;
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

    public static Response getUserById(Cookie cookie, String username) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("principal", username)
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

    public static Response createASkill(String createSkillBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(createSkillBody)
                .when()
                .post();
    }

    public static Response getSkill(Cookie cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue())
                .when()
                .get();
    }

    public static Response editASkill(Cookie cookie, String skillName, Integer skillId) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skill", skillName)
                .queryParam("skillId", skillId)
                .put();
    }

    public static Response getOneSKill(Cookie cookie, Integer skillId) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skillId)
                .get();
    }

    public static Response deleteSkill(Cookie cookie, Integer skillId) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skillId)
                .put();
    }
}
