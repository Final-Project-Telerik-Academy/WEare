package com.weare.api.services;

import com.weare.api.utils.JSONRequests;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ConnectionService {
    private ConnectionService() {};

    public static String generateSendRequest(Integer targetUserId, String username) {
        return String.format(JSONRequests.SEND_REQUEST_BODY, targetUserId, username);
    }

    public static Response sendFriendRequest(String sendRequestJsonBody, Cookie cookie) {
        return given()
                .cookie("JSESSIONID", cookie.getValue())
                .contentType("application/json")
                .body(sendRequestJsonBody.toString())
                .when()
                .post();
    }

    public static Response getUsersRequest(Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .when()
                .get();
    }

    public static Response approveRequest(Cookie cookie, int requestId) {
        return given()
                .cookie("JSESSIONID", cookie.getValue())
                .formParam("requestId", requestId)
                .when()
                .post();
    }
}