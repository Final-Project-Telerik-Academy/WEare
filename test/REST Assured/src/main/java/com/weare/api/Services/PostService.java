package com.weare.api.Services;

import com.google.gson.Gson;
import com.weare.api.Models.Post;
import com.weare.api.Utils.JSONRequests;

public class PostService {
    private final Gson gson = new Gson();

    public String postToJson(Post post) {
        return gson.toJson(post);
    }

    public Post jsonToPost(String json) {
        return gson.fromJson(json, Post.class);
    }
    public String generatePostRequest(Post post) {
        return String.format(JSONRequests.POST,
                post.getContent(),post.getPicture(),post.isPublic());

    }
}
