package com.weare.api.services;

import com.google.gson.Gson;
import com.weare.api.models.Post;
import com.weare.api.utils.JSONRequests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.http.Cookie;

import static com.weare.api.utils.Constants.POST_ID;
import static io.restassured.RestAssured.given;

public class PostService {
    private PostService() {};
    public static String generatePostRequest(Post post) {
        return String.format(JSONRequests.POST,
                post.getContent(),post.getPicture(),post.isPublic());

    }
    public static String editPostRequest(Post post) {
        return String.format(JSONRequests.EDIT_POST,
                post.getContent(),post.getPicture(),post.isPublic());
    }

    public static Response createPrivatePostApi(String postJsonBody, Cookie cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();
    }

    public static Response createPublicPostApi(String postJsonBody, Cookie cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();
    }
    public static Response editPostApi(String editJsonBody, Cookie cookie, Integer postId) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .body(editJsonBody)
                .when()
                .put();
    }


    public static Response getPostAPi(Cookie cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .when()
                .get();
    }

    public static Response likePostApi( Cookie cookie, Integer postId) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .post();
    }

    public static Response disLikePost( Cookie cookie, Integer postId) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .post();
    }

    public static Response deletePostApi(Cookie cookie, Integer postId) {
        return given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", postId)
                .when()
                .delete();
    }
}

