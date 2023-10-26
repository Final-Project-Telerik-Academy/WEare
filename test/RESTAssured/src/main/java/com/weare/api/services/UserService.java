package com.weare.api.services;

import com.google.gson.Gson;
import com.weare.api.models.User;
import com.weare.api.models.Skill;
import com.weare.api.models.requests.CityDetails;
import com.weare.api.models.requests.Location;
import com.weare.api.models.requests.RegistrationRequest;
import com.weare.api.models.requests.UpdateProfileRequest;
import com.weare.api.models.requests.enums.City;
import com.weare.api.models.requests.enums.Gender;
import com.weare.api.utils.Constants;
import com.weare.api.utils.JSONRequests;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class UserService {
    private UserService() {};

//    public static String registrationRequest(User user) {
//        return String.format(JSONRequests.REGISTRATION,
//                user.getAuthority(), user.getCategoryId(), user.getCategoryName(),
//                user.getPassword(), user.getEmail(), user.getPassword(), user.getUsername());
//    }

    public static String registrationRequest(User user) {
        RegistrationRequest request = new RegistrationRequest();

        request.setAuthority(user.getAuthority());
        request.setCategoryId(user.getCategoryId());
        request.setConfirmPassword(user.getPassword());
        request.setEmail(user.getEmail());
        request.setPassword(user.getPassword());
        request.setUsername(user.getUsername());

        Gson gson = new Gson();
        return gson.toJson(request);
    }

//    public static String updateProfileRequest(User user) {
//        return String.format(JSONRequests.UPDATE_PERSONAL_PROFILE,
//                Constants.birthYear, user.getFirstName(), user.getUserId(), user.getLastName(),
//                Constants.CITY, Constants.CITY_ID);
//    }

    public static String updateProfileRequest(User user) {
        UpdateProfileRequest request = new UpdateProfileRequest();

        request.setBirthYear(user.getBirthYear());
        request.setFirstName(user.getFirstName());
        request.setId(user.getUserId());
        request.setLastName(user.getLastName());

        CityDetails cityDetails = new CityDetails();
        cityDetails.setCity(City.PLOVDIV.getName());
        cityDetails.setId(City.PLOVDIV.getId());

        Location location = new Location();
        location.setCity(cityDetails);

        request.setLocation(location);
        request.setPersonalReview("");
        request.setPicture("");
        request.setPicturePrivacy(true);
        request.setSex(Gender.MALE.getName());

        Gson gson = new Gson();
        return gson.toJson(request);
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
