package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.models.Comment;
import com.weare.api.models.Post;
import com.weare.api.models.User;
import com.weare.api.services.CommentService;
import com.weare.api.services.PostService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import com.weare.api.utils.AssertHelper;

import static com.weare.api.services.CommentService.*;
import static com.weare.api.services.PostService.*;
import static com.weare.api.utils.Constants.*;
import static com.weare.api.utils.Endpoints.*;
import static com.weare.api.utils.Endpoints.SHOW_COMMENT;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PostTest extends BaseTestSetup {

    protected static Post post;
    protected static Comment comment;
    protected static PostService postService;
    protected static CommentService commentService;
    protected Integer userId;

    @BeforeEach
    public void setupTest() {
        User user = new User();
        register(user);
        login(user);
    }

    @AfterEach
    public void tearDownAfterTest() {
        logout();
    }

    @Feature("Posts")
    @Story("Create private post")
    @Description("Test to verify that a private post can be created successfully.")
    @Test
    public void createPrivatePost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        post = new Post();

        String postJsonBody = PostService.generatePostRequest(post);
        Response response = createPrivatePostApi(postJsonBody, cookie);

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        String contentPost = response.getBody().jsonPath().get("content");
        Boolean privatePost = response.getBody().jsonPath().get("public");
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();

        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertContentEquals(contentPost, post.getContent());
    }
    @Feature("Posts")
    @Story("Create public post")
    @Description("Test to verify that a public post can be created successfully.")
    @Test
    public void createPublicPost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);

        post = new Post();
        post.setPublic(true);
        String postJsonBody = PostService.generatePostRequest(post);
        Response response = createPublicPostApi(postJsonBody, cookie);

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        int statusCode = response.getStatusCode();
        String contentPost=response.getBody().jsonPath().get("content");
        Boolean privatePost=response.getBody().jsonPath().get("public");

        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertPostIsPrivate(privatePost);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertContentEquals(contentPost, post.getContent());
    }

    @Feature("Posts")
    @Story("Edit post")
    @Description("Test to verify that a post can be edited successfully.")
    @Test
    public void editPost() {
        createPublicPost();

        baseURI = format("%s%s", BASE_URL, EDIT_POST);

        String editJsonBody = PostService.editPostRequest(post);

        Response response = editPostApi(editJsonBody, cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode,SC_OK);
    }

    @Feature("Posts")
    @Story("Retrieve post")
    @Description("Test to verify that a post can be retrieved successfully.")
    @Test
    public void getPost() {
        baseURI = format("%s%s", BASE_URL, GET_POST);

        Response response = getPostAPi(cookie);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode,SC_OK);
    }

//    @Test(priority = 4,dependsOnMethods = "createPublicPost")
    @Feature("Posts")
    @Story("Like a post")
    @Description("Test to verify that a post can be liked successfully.")
    @Test
    public void likePost() {
        baseURI = format("%s%s", BASE_URL, LIKE_POST);

        Response response = likePostApi(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode,SC_OK);
    }

    @Feature("Posts")
    @Story("Dislike a post")
    @Description("Test to verify that a post can be disliked successfully.")
    @Test
    public void dislikePost() {
        baseURI = format("%s%s", BASE_URL, LIKE_POST);

        Response response = disLikePost(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        AssertHelper.assertStatusCode(statusCode,SC_OK);
    }

//    @Test(priority = 6 ,dependsOnMethods = "createPublicPost")
    @Feature("Comments")
    @Story("Create comment on a Post")
    @Description("Test to verify that a comment can be added to a post successfully.")
    @Test
    public void createComment() {
        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        comment = new Comment();

        userId = user.getUserId();
        comment.setUserId(userId);

        String commentJsonBody = CommentService.generateCommentRequest(comment);
        Response response = createCommentApi(commentJsonBody, cookie);

        String contentComment=response.getBody().jsonPath().get("content");
        int statusCode = response.getStatusCode();

        AssertHelper.assertStatusCode(statusCode,SC_OK);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertPositiveUserId(userId);
        AssertHelper.assertContentEquals(contentComment,comment.getContent());
    }

    @Test
    public void showComment() {
        baseURI = format("%s%s", BASE_URL, SHOW_COMMENT);

        Response response = showCommentApi(cookie, POST_ID);

        int statusCode = response.getStatusCode();

        AssertHelper.assertStatusCode(statusCode,SC_OK);
    }

    @Feature("Comments")
    @Story("Retrieve comments of a post")
    @Description("Test to verify that comments of a post can be retrieved successfully.")
    @Test
    public void deletePost() {
        createPublicPost();
        baseURI = format("%s%s", BASE_URL, DELETE_POST);

        Response response = deletePostApi(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode,SC_OK);
    }
}

