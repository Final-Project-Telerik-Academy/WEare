package com.weare.api.Services;

import com.weare.api.Utils.JSONRequests;

public class ConnectionService {

    public static String generateSendRequest(Integer targetUserId, String username) {
        return String.format(JSONRequests.SEND_REQUEST_BODY, targetUserId, username);
    }
}
