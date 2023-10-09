package com.weare.api.Services;

import com.weare.api.Models.User;
import com.weare.api.Utils.JSONRequests;

public class UserService {
    public String generateRegistrationRequest(User user) {
        return String.format(JSONRequests.REGISTRATION,
                user.getAuthority(), user.getCategoryId(), user.getCategoryName(),
                user.getPassword(), user.getEmail(), user.getPassword(), user.getUsername());
    }
}
