package com.weare.api.services;

import com.weare.api.utils.JSONRequests;

public class ConnectionService {
    public static String generateSendRequest(Integer targetUserId, String username) {
        return String.format(JSONRequests.SEND_REQUEST_BODY, targetUserId, username);
    }
}
