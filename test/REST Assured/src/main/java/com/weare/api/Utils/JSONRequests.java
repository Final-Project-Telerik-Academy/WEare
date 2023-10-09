package com.weare.api.Utils;

public class JSONRequests {
    public static final String REGISTRATION = "{\n" +
            "  \"authorities\": [\n" +
            "    \"%s\"\n" +
            "  ],\n" +
            "  \"category\": {\n" +
            "    \"id\": %s,\n" +
            "    \"name\": \"%s\"\n" +
            "  },\n" +
            "  \"confirmPassword\": \"%s\",\n" +
            "  \"email\": \"%s\",\n" +
            "  \"password\": \"%s\",\n" +
            "  \"username\": \"%s\"\n" +
            "}";
}
