package com.weare.api.services;

import com.weare.api.models.Skill;
import com.weare.api.utils.Constants;
import com.weare.api.utils.JSONRequests;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SkillService {
    private SkillService() {}
    public static String createASkillRequest(Skill skill) {
        return String.format(JSONRequests.CREATE_SKILL,
                Constants.CATEGORY_ID, Constants.CATEGORY_NAME, skill.getName());
    }

    public static Response createASkill(String createSkillBody, Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(createSkillBody)
                .when()
                .post();
    }

    public static Response getSkill(Cookie cookie) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue())
                .when()
                .get();
    }

    public static Response editASkill(Cookie cookie, String skillName, Integer skillId) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skill", skillName)
                .queryParam("skillId", skillId)
                .put();
    }

    public static Response getOneSKill(Cookie cookie, Integer skillId) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skillId)
                .get();
    }

    public static Response deleteSkill(Cookie cookie, Integer skillId) {
        return given()
                .filter(new AllureRestAssured())
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skillId)
                .put();
    }
}
