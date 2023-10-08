package com.weare.api;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONValidator {
    public static boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}