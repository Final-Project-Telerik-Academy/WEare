package com.weare.api.services;

import com.weare.api.models.Comment;
import com.weare.api.utils.JSONRequests;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommentService {
    private CommentService() {}

    public static String generateCommentRequest(Comment comment) {
        return String.format(JSONRequests.COMMENT,
                comment.getContent(),comment.getPostId(),comment.getUserId());
    }

    public static Response createCommentApi(String commentJsonBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .body(commentJsonBody)
                .when()
                .post();
    }

    public static Response showCommentApi(Cookie cookie, Integer postId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .get();
    }

    public static Response getCommentApi(Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie)
                .when()
                .get();
    }

    public static Response likeCommentApi(Cookie cookie, Integer commentId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("commentId", commentId)
                .when()
                .post();
    }

    public static Response diskCommentApi(Cookie cookie, Integer commentId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("commentId", commentId)
                .when()
                .post();
    }

    public static Response getAllCommentApi(Cookie cookie, Integer postId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .get();
    }

    public static Response getOneCommentApi(Cookie cookie, Integer postId) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("commentId", postId)
                .when()
                .get();
    }

    public static Response deleteCommentApi(Cookie cookie, Integer commentId) {
        return  given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("commentId", commentId)
                .when()
                .delete();
    }
}
