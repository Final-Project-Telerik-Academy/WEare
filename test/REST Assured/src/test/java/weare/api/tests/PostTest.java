package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.Comment;
import com.weare.api.Models.Post;
import com.weare.api.Models.User;
import com.weare.api.Services.CommentService;
import com.weare.api.Services.PostService;
import com.weare.api.Services.UserService;
import com.weare.api.Utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Constants.POST_ID;
import static com.weare.api.Utils.Endpoints.*;
import static com.weare.api.Utils.JSONRequests.POST;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;

public class PostTest extends BaseTestSetup {
    private int postId;
    protected static Post post;
    protected static Comment comment;
    private String content;
    private String picture;
    private boolean isPublic;
    private  Cookie authCookie;

    @Test
    public void authenticationTest() {
        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);

        // Make a request to get authentication and set the cookie
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

    @Test
    public void createPost() {


        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        PostService postService = new PostService();
        post = new Post();

        content = post.getContent();
        picture = post.getPicture();
        isPublic = post.isPublic();

        String postJsonBody = postService.generatePostRequest(post);

        // Use the previously saved authentication cookie for this request
        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue()) // Use the saved authentication cookie
                .body(postJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println(responseBody);
    }
//    @Test
//    public void editPost() {
//
//
//       baseURI = format("%s%s%d", BASE_URL, EDIT_POST,POST_ID);
//        PostService postService = new PostService();
//
//        content = Constants.CONTENT_EDIT_POST;
//        picture = post.getPicture();
//        isPublic = post.isPublic();
//
//        String editJsonBody = postService.editPostRequest(post);
//
//        // Use the previously saved authentication cookie for this request
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .cookie("JSESSIONID", authCookie.getValue()) // Use the saved authentication cookie
//                .body(editJsonBody)
//                .when()
//                .put( baseURI =BASE_URL+  "/api/post/auth/editor?postId="+POST_ID);
//
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
//        // System.out.println(responseBody);
//    }
//    @Test
//    public void getPost() {
//        baseURI = format("%s%s", BASE_URL, GET_POST);
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .cookie("JSESSIONID", authCookie.getValue())
//                //.queryParam("principal", user.getUsername())
//                .when()
//                .get();
//
//        int statusCode = response.getStatusCode();
//        String responseBody = response.getBody().asString();
//        System.out.println(responseBody);
//        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");
//
//    }
//    @Test
//    public void likePost() {
//        baseURI = format("%s%s", BASE_URL, LIKE_POST);
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .cookie("JSESSIONID", authCookie.getValue())
//                .queryParam("postId", POST_ID)
//                .when()
//                .post();
//
//        int statusCode = response.getStatusCode();
//        String responseBody = response.getBody().asString();
//        System.out.println(responseBody);
//        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");
//
//    }
    @Test
    public void createComment() {


        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        CommentService commentService = new CommentService();
        comment = new Comment();

      //  content = comment.getContent();

      //  userId = Constants.USER_ID;
       // isPublic = post.isPublic();

        String commentJsonBody = commentService.generateCommentRequest(comment);

        // Use the previously saved authentication cookie for this request
        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue()) // Use the saved authentication cookie
                .body(commentJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        System.out.println(responseBody);
    }
//    @Test
//    public void deletePost() {
//        baseURI = format("%s%s", BASE_URL, DELETE_POST);
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .cookie("JSESSIONID", authCookie.getValue())
//               .queryParam("postId", POST_ID)
//                .when()
//                .delete();
//
//        int statusCode = response.getStatusCode();
//        String responseBody = response.getBody().asString();
//        System.out.println(responseBody);
//        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");
//
//    }
}

