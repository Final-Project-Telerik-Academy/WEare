package com.weare.api.services;

import com.weare.api.models.Post;
import com.weare.api.utils.JSONRequests;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostService {
    private PostService() {};


    public static String postRequest(Post post) {
        return String.format(JSONRequests.POST,
                post.getContent(), post.getPicture(), post.isPublic());

    }

    public static String editPostRequest(Post post) {
        return String.format(JSONRequests.EDIT_POST,
                post.getContent(), post.getPicture(), post.isPublic());
    }

    public static Response createPrivatePost(String postJsonBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();
    }

    public static Response createPublicPost(String postJsonBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();
    }

    public static Response editPost(String editJsonBody, Cookie cookie, Integer postId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .body(editJsonBody)
                .when()
                .put();
    }


    public static Response getPost(Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .when()
                .get();
    }

    public static Response likePost(Cookie cookie, Integer postId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .post();
    }

    public static Response disLikePost(Cookie cookie, Integer postId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .post();
    }

    public static Response deletePost(Cookie cookie, Integer postId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .delete();
    }
}

