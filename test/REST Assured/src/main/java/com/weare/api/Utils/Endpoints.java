package com.weare.api.Utils;

import static java.lang.String.format;

public class Endpoints {
    public static final String BASE_URL="http://localhost:8081";
    public static final String AUTH_ENDPOINT = "/authenticate";
    public static final String REGISTER_ENDPOINT = "/api/users/";
    public static final String CREATE_POST_ENDPOINT = "/api/post/auth/creator" ;
    public static final String UPDATE_PERSONAL_PROFILE_ENDPOINT = "/api/users/auth/%s/personal";
    public static final String USER_BY_ID_ENDPOINT = "/api/users/auth/%s";
    public static final String EDIT_POST="/api/post/auth/editor?postId=";
    public static final String SEARCH_USER_ENDPOINT = "/api/users";
    public static final String SEARCH_USER_POSTS_ENDPOINT = "/api/users/%s/posts";
    public static final String UPDATE_USER_EXPERTISE_ENDPOINT = "/api/users/auth/%s/expertise";
    public static final String GET_POST="/api/post/";
    public static final String LIKE_POST= "/api/post/auth/likesUp?postId=";
    public static final String DELETE_POST= "/api/post/auth/manager?postId=";
    public static final String CREATE_COMMENT_ENDPOINT= "/api/comment/auth/creator";
    public static final String CREATE_SKILL_ENDPOINT = "/api/skill/create";
    public static final String GET_SKILLS_ENPOINT = "/api/skill";
    public static final String EDIT_SKILL_ENDPOINT = "/api/skill/edit";
    public static final String GET_ONE_SKILL_ENDPOINT = "/api/skill/getOne";
    public static final String DELETE_SKILL_ENDPOINT = "/api/skill/delete";
}
