package com.weare.api.Services;

import com.google.gson.Gson;
import com.weare.api.Models.User;
import com.weare.api.Utils.JSONRequests;

public class UserService {
    private final Gson gson = new Gson();

    public String userToJson(User user) {
        return gson.toJson(user);
    }

    public User jsonToUser(String json) {
        return gson.fromJson(json, User.class);
    }
    public String generateRegistrationRequest(User user) {
        return String.format(JSONRequests.REGISTRATION,
                user.getAuthority(), user.getCategoryId(), user.getCategoryName(),
                user.getPassword(), user.getEmail(), user.getPassword(), user.getUsername());
    }
}
