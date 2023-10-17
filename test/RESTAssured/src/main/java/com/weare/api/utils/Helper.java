package com.weare.api.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Helper {
    public static boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
