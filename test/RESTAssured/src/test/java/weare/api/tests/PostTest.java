package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.models.Comment;
import com.weare.api.models.Post;
import com.weare.api.models.User;
import com.weare.api.services.CommentService;
import com.weare.api.services.PostService;
import com.weare.api.utils.AssertHelper;
import com.weare.api.utils.DatabaseOperations;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.weare.api.services.PostService.disLikePost;
import static com.weare.api.utils.Constants.COMMENT_ID;
import static com.weare.api.utils.Constants.POST_ID;
import static com.weare.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class PostTest extends BaseTestSetup {

    protected static Post post;
    protected static Comment comment;
    protected Integer userId;

    @Override
    @BeforeEach
    protected void beforeEach() {
        user = new User();
        register(user);
        login(user);
    }

    @AfterEach
    public void tearDownAfterTest() {
        if (user != null) {
            DatabaseOperations.deleteCommentsForUserPosts(user.getUserId());
            DatabaseOperations.deleteLikesForUserPosts(user.getUserId());
            DatabaseOperations.deletePostsByUserId(user.getUserId());
        }
        logout();
        DatabaseOperations.removeUserById("user_id", user.getUserId());
    }

    @Feature("Posts")
    @Story("Create private post")
    @Description("Test to verify that a private post can be created successfully.")
    @Test
    public void createPrivatePost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        post = new Post();
        post.setPublic(true);

        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPrivatePost(postJsonBody, cookie);

        POST_ID = response.getBody().jsonPath().get("postId");
        String contentPost = response.getBody().jsonPath().get("content");
        String responseBody = response.getBody().asString();
        Boolean privatePost = response.getBody().jsonPath().get("public");
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();

        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertContentEquals(contentPost, post.getContent());

        boolean postExists = DatabaseOperations.checkPostExists(POST_ID);
        AssertHelper.assertUserPostExist(postExists, POST_ID);
    }

    @Feature("Posts")
    @Story("Create public post")
    @Description("Test to verify that a public post can be created successfully.")
    @Test
    public void createPublicPost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);

        post = new Post();
        post.setPublic(true);
        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPublicPost(postJsonBody, cookie);

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        int statusCode = response.getStatusCode();
        String contentPost = response.getBody().jsonPath().get("content");
        Boolean privatePost = response.getBody().jsonPath().get("public");

        System.out.println(responseBody);
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertPostIsPrivate(privatePost);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertContentEquals(contentPost, post.getContent());

        boolean postExists = DatabaseOperations.checkPostExists(POST_ID);
        AssertHelper.assertUserPostExist(postExists, POST_ID);
    }

    @Feature("Posts")
    @Story("Edit post")
    @Description("Test to verify that a post can be edited successfully.")
    @Test
    public void editPost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);

        post = new Post();
        post.setPublic(true);
        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPublicPost(postJsonBody, cookie);

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        System.out.println(responseBody);

        baseURI = format("%s%s", BASE_URL, EDIT_POST);

        String editJsonBody = PostService.editPostRequest(post);
        response = PostService.editPost(editJsonBody, cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Feature("Posts")
    @Story("Retrieve post")
    @Description("Test to verify that a post can be retrieved successfully.")
    @Test
    public void getPost() {
        baseURI = format("%s%s", BASE_URL, GET_POST);

        Response response = PostService.getPost(cookie);
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Feature("Posts")
    @Story("Like a post")
    @Description("Test to verify that a post can be liked successfully.")
    @Test
    public void likePost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);

        post = new Post();
        post.setPublic(true);
        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPublicPost(postJsonBody, cookie);

        POST_ID = response.getBody().jsonPath().get("postId");

        baseURI = format("%s%s", BASE_URL, LIKE_POST);

        response = PostService.likePost(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);

        boolean isPostLiked = DatabaseOperations.isPostLikedByUser(POST_ID, user.getUserId());
        AssertHelper.assertPostIsLikedByUser(isPostLiked);
    }

    @Feature("Posts")
    @Story("Dislike a post")
    @Description("Test to verify that a post can be disliked successfully.")
    @Test
    public void dislikePost() {

        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);

        post = new Post();
        post.setPublic(true);
        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPublicPost(postJsonBody, cookie);

        // String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");

        baseURI = format("%s%s", BASE_URL, LIKE_POST);
        response = PostService.likePost(cookie, POST_ID);

        baseURI = format("%s%s", BASE_URL, LIKE_POST);
        response = disLikePost(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Feature("Comments")
    @Story("Create comment on a Post")
    @Description("Test to verify that a comment can be added to a post successfully.")
    @Test
    public void createComment() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        post = new Post();
        post.setPublic(true);

        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPublicPost(postJsonBody, cookie);

        POST_ID = response.getBody().jsonPath().get("postId");

        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        comment = new Comment();
        userId = user.getUserId();
        comment.setUserId(userId);
        comment.setPostId(POST_ID);

        String commentJsonBody = CommentService.commentRequest(comment);
        Response commentResponse = CommentService.createComment(commentJsonBody, cookie);
        COMMENT_ID = response.getBody().jsonPath().get("commentId");
        String contentComment = commentResponse.getBody().jsonPath().get("content");

        String responseBody = response.getBody().asString();
        System.out.print(responseBody);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertPositiveUserId(userId);
        AssertHelper.assertContentEquals(contentComment, comment.getContent());
    }

    @Feature("Comments")
    @Story("Show comment on a post")
    @Description("Test to verify that a comment is displayed correctly on a post.\"")
    @Test
    public void showComment() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        post = new Post();
        post.setPublic(true);

        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPublicPost(postJsonBody, cookie);

        POST_ID = response.getBody().jsonPath().get("postId");

        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        comment = new Comment();
        userId = user.getUserId();
        comment.setUserId(userId);
        comment.setPostId(POST_ID);


        String commentJsonBody = CommentService.commentRequest(comment);
        Response commentResponse = CommentService.createComment(commentJsonBody, cookie);
        COMMENT_ID = response.getBody().jsonPath().get("commentId");
        baseURI = format("%s%s", BASE_URL, SHOW_COMMENT);

        response = CommentService.showComment(cookie, POST_ID);

        String responseBody = response.getBody().asString();
        System.out.print(responseBody);
        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Feature("Comments")
    @Story("Retrieve comments of a post")
    @Description("Test to verify that comments of a post can be retrieved successfully.")
    @Test
    public void deletePost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        post = new Post();
        post.setPublic(true);

        String postJsonBody = PostService.postRequest(post);
        Response response = PostService.createPublicPost(postJsonBody, cookie);

        POST_ID = response.getBody().jsonPath().get("postId");
        baseURI = format("%s%s", BASE_URL, DELETE_POST);

        response = PostService.deletePost(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
}

