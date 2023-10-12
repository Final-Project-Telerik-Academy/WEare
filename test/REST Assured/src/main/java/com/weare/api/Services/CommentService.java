package com.weare.api.Services;

import com.google.gson.Gson;
import com.weare.api.Models.Comment;
import com.weare.api.Models.Post;
import com.weare.api.Utils.JSONRequests;

public class CommentService {
    private final Gson gson = new Gson();

    public String postToJson(Post post) {
        return gson.toJson(post);
    }

    public Post jsonToPost(String json) {
        return gson.fromJson(json, Post.class);
    }
    public String generateCommentRequest(Comment comment) {
        return String.format(JSONRequests.COMMENT,
                comment.getContent(),comment.getPostId(),comment.getUserId());

    }
}
