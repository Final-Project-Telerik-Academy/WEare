package com.weare.api.services;

import com.weare.api.models.Skill;
import com.weare.api.utils.Constants;
import com.weare.api.utils.JSONRequests;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SkillService {
    private SkillService() {}
    public static String generateCreateSkill(Skill skill) {
        return String.format(JSONRequests.CREATE_SKILL,
                Constants.CATEGORY_ID, Constants.CATEGORY_NAME, skill.getName());
    }

    public static Response createASkillApi(String createSkillBody, Cookie cookie) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(createSkillBody)
                .when()
                .post();
    }

    public static Response getSkillApi(Cookie cookie) {
        return given()
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue())
                .when()
                .get();
    }

    public static Response editASkillApi(Cookie cookie, String skillName, Integer skillId) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skill", skillName)
                .queryParam("skillId", skillId)
                .put();
    }

    public static Response getOneSKillApi(Cookie cookie, Integer skillId) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skillId)
                .get();
    }

    public static Response deleteSkillApi(Cookie cookie, Integer skillId) {
        return given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skillId)
                .put();
    }
}
