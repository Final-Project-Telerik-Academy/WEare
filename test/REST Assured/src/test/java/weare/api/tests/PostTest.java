package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.Comment;
import com.weare.api.Models.Post;
import com.weare.api.Services.CommentService;
import com.weare.api.Services.PostService;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static com.weare.api.Utils.Constants.*;
import static com.weare.api.Utils.Endpoints.*;
import static com.weare.api.Utils.Endpoints.SHOW_COMMENT;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class PostTest extends BaseTestSetup {

    protected static Post post;
    protected static Comment comment;
    protected static PostService postService;
    protected static CommentService commentService;
    private  Cookie authCookie;
    protected Integer userId;

 /*   @BeforeMethod
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
    }*/


    @Test
    @Order(1)
//    @Test (priority = 1)
    public void createPrivatePost() {


        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        postService = new PostService();
        post = new Post();

        String postJsonBody = postService.generatePostRequest(post);

        Response response = given()

                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue()) // Use the saved authentication cookie
                // .cookie(cookie.getName(), cookie.getValue())
                .body(postJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        String contentPost=response.getBody().jsonPath().get("content");
        Boolean privatePost=response.getBody().jsonPath().get("public");
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();

        Assertions.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assertions.assertFalse(privatePost,"This post is not a public");
        Assertions.assertNotNull(ContentType.JSON);
        Assertions.assertEquals(contentPost, post.getContent());


    }
    @Test
    @Order(2)
//    @Test(priority = 2)
    public void createPublicPost() {


        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        postService = new PostService();
        post = new Post();
        post.setPublic(true);
        String postJsonBody = postService.generatePostRequest(post);

        Response response = given()
                .contentType(ContentType.JSON)
                // .cookie(cookie.getName(), cookie.getValue())
                .cookie("JSESSIONID", cookie.getValue()) // Use the saved authentication cookie
                .body(postJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();
        POST_ID = response.getBody().jsonPath().get("postId");
        int statusCode = response.getStatusCode();
        String contentPost=response.getBody().jsonPath().get("content");
        Boolean privatePost=response.getBody().jsonPath().get("public");
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assertions.assertTrue(privatePost,"This post is not a public");
        Assertions.assertNotNull(ContentType.JSON);
        Assertions.assertEquals(contentPost, post.getContent());
    }
    @Test
    @Order(2)
//    @Test(priority = 2,dependsOnMethods = "createPublicPost")
    public void editPost() {


        baseURI = format("%s%s", BASE_URL, EDIT_POST);
        PostService postService = new PostService();

        String editJsonBody = postService.editPostRequest(post);

        Response response = given()
                // .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("postId", POST_ID)
                .body(editJsonBody)
                .when()
                .put();

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
    }
    @Test
    @Order(3)
//    @Test(priority = 3)
    public void getPost() {
        baseURI = format("%s%s", BASE_URL, GET_POST);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                // .cookie(cookie.getName(), cookie.getValue())
                .when()
                .get();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
    @Test
    @Order(4)
//    @Test(priority = 4,dependsOnMethods = "createPublicPost")
    public void likePost() {
        baseURI = format("%s%s", BASE_URL, LIKE_POST);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                //.cookie(cookie.getName(), cookie.getValue())
                .queryParam("postId", POST_ID)
                .when()
                .post();


        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 5)
    @Test
    @Order(5)
    public void dislikePost() {
        baseURI = format("%s%s", BASE_URL, LIKE_POST);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                // .cookie(cookie.getName(), cookie.getValue())
                .queryParam("postId", POST_ID)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 6 ,dependsOnMethods = "createPublicPost")
    @Test
    @Order(6)
    public void createComment() {

        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        commentService = new CommentService();
        comment = new Comment();

        userId = user.getUserId();

        comment.setUserId(userId);

        String commentJsonBody = commentService.generateCommentRequest(comment);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue()) // Use the saved authentication cookie
                //  .cookie(cookie.getName(), cookie.getValue())
                .body(commentJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();
        String contentComment=response.getBody().jsonPath().get("content");
        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assertions.assertNotNull(ContentType.JSON);
        Assertions.assertEquals(contentComment, comment.getContent(),"Content does not match");
        System.out.println(responseBody);
    }
    @Test
    @Order(7)
//    @Test(priority = 7)
    public void showComment() {
        baseURI = format("%s%s", BASE_URL, SHOW_COMMENT);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                //.cookie(cookie.getName(), cookie.getValue())
                .queryParam("postId", POST_ID)
                .when()
                .get();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
   // @AfterClass
/*    @Test(priority = 8)*/
   @Test
   @Order(8)
    public void deletePost() {
        baseURI = format("%s%s", BASE_URL, DELETE_POST);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                //  .cookie(cookie.getName(), cookie.getValue())
                .queryParam("postId", POST_ID)
                .when()
                .delete();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }

}

