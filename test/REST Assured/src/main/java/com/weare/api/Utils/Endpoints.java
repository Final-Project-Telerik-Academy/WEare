package com.weare.api.Utils;

import static java.lang.String.format;

public class Endpoints {
    public static final String BASE_URL="http://localhost:8081";
    public static final String AUTH_ENDPOINT = "/authenticate";
    public static final String REGISTER_ENDPOINT = "/api/users/";
    public static final String CREATE_POST_ENDPOINT = "/api/post/auth/creator" ;
    public static final String UPDATE_PERSONAL_PROFILE_ENDPOINT = "/api/users/auth/%s/personal";
    public static final String GET_USER_BY_ID_ENDPOINT = "/api/users/auth/%s";
}
