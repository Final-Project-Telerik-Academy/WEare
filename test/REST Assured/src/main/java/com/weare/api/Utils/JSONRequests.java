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

    public static final String EDIT_POST="{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"picture\": \"%s\",\n" +
            "    \"public\": %s\n" +
            "}";

    public static final String SEARCH_USER = "{\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"%s\",\n" +
            "  \"size\": 10\n" +
            "}";

    public static final String SHOW_PROFILE_POSTS = "{\n" +
            "  \"index\": 0,\n" +
            "  \"next\": true,\n" +
            "  \"searchParam1\": \"\",\n" +
            "  \"searchParam2\": \"\",\n" +
            "  \"size\": 5\n" +
            "}";

    public static final String UPDATE_USER_EXPERTISE = "{\n" +
            "  \"availability\": %s,\n" +
            "  \"category\": {\n" +
            "    \"id\": %s,\n" +
            "    \"name\": \"Cleaner\"\n" +
            "  },\n" +
            "  \"id\": %s,\n" +
            "    \"skill1\": \"%s\",\n" +
            "    \"skill2\": \"\",\n" +
            "    \"skill3\": \"\",\n" +
            "    \"skill4\": \"\",\n" +
            "    \"skills\": [\n" +
            "    ]\n" +
            "}";
    public static final String COMMENT="{\n" +
            "    \"content\": \"%s\",\n" +
            "    \"deletedConfirmed\": true,\n" +
            "     \"postId\": %d,\n" +
            "  \"userId\": \"%d\"\n" +
            "}";
}
