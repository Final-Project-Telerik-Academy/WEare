package com.weare.api.services;

import com.google.gson.Gson;
import com.weare.api.models.User;
import com.weare.api.models.UserC;
import com.weare.api.utils.Constants;
import com.weare.api.utils.JSONRequests;

public class UserServiceC {
    private final Gson gson = new Gson();
    private UserServiceC() {};

    public String userToJson(UserC user) {
        return gson.toJson(user);
    }
    public User jsonToUser(String json) {
        return gson.fromJson(json, User.class);
    }
    public static String generateRegistrationRequest(UserC user) {
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

    public static String generateUpdateExpertiseProfile(User user) {
        return String.format(JSONRequests.UPDATE_USER_EXPERTISE,
                Constants.AVAILABILITY, user.getCategoryId(), user.getUserId(), Constants.SKILL_NAME);
    }
}
