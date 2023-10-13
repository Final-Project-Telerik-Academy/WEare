package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.Comment;
import com.weare.api.Models.Post;
import com.weare.api.Services.CommentService;
import com.weare.api.Services.PostService;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Constants.COMMENT_ID;
import static com.weare.api.Utils.Constants.POST_ID;
import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;


public class CommentTest extends BaseTestSetup {
    protected static Post post;
    protected static Comment comment;
    protected static PostService postService;
    protected static CommentService commentService;
    private Cookie authCookie;
    protected Integer userId;

    @BeforeMethod
    @Test
    public void authenticationTest() {
        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);

        Response response = getApplicationAuthentication()
                .when()
                .post();

        authCookie = response.getDetailedCookie("JSESSIONID");
        System.out.println("JSESSIONID cookie: " + response.getCookie("JSESSIONID"));
        System.out.println("Response code: " + response.getStatusCode());

        System.out.println("Response body: " + response.getBody().asString());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_MOVED_TEMPORARILY, "Cookie status code is correct");
    }
    @BeforeMethod
    @Test(priority = 1)
    public void createPost() {


        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        postService = new PostService();
        post = new Post();


        String postJsonBody = postService.generatePostRequest(post);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .body(postJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        String contentPost=response.getBody().jsonPath().get("content");
        Boolean privatePost=response.getBody().jsonPath().get("public");
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assert.assertFalse(privatePost,"This post is not a public");
        Assert.assertNotNull(ContentType.JSON);
        Assert.assertEquals(contentPost, post.getContent());

    }
    @Test(priority = 2)
    public void createComment() {

        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        commentService = new CommentService();
        comment = new Comment();

        userId = Integer.parseInt(user.getUserId());

        comment.setUserId(userId);

        String commentJsonBody = commentService.generateCommentRequest(comment);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .body(commentJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();
        COMMENT_ID = response.getBody().jsonPath().get("commentId");
        String contentComment=response.getBody().jsonPath().get("content");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assert.assertNotNull(ContentType.JSON);
        Assert.assertEquals(contentComment, comment.getContent(),"Content does not match");
        System.out.println(responseBody);

    }


}
