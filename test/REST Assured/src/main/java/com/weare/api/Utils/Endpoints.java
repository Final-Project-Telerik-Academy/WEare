package com.weare.api.Utils;

import static java.lang.String.format;

public class Endpoints {
    public static final String BASE_URL="localhost:8081";
    public static final String AUTH_ENDPOINT ="/authenticate";
    public static final String CREATE_USER ="/api/users/";

    public static final String REGISTRATION_ENDPOINT = format("%s", CREATE_USER);
}
