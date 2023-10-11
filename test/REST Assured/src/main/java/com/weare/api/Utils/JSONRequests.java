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
    public static final String POST="{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"picture\": \"%s\",\n" +
            "    \"public\": %s\n" +
            "}";

    public static final String UPDATE_PERSONAL_PROFILE = "{\n" +
            "  \"birthYear\": \"%s\",\n" +
            "  \"firstName\": \"%s\",\n" +
            "  \"id\": %s,\n" +
            "  \"lastName\": \"%s\",\n" +
            "  \"location\": {\n" +
            "    \"city\": {\n" +
            "      \"city\": \"%s\",\n" +
            "      \"country\": {},\n" +
            "      \"id\": %s\n" +
            "    }\n" +
            "  },\n" +
            "  \"personalReview\": \"\",\n" +
            "  \"picture\": \"\",\n" +
            "  \"picturePrivacy\": true,\n" +
            "  \"sex\": \"MALE\"\n" +
            "}\n";
}
