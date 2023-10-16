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
import com.weare.api.Utils.AssertHelper;

import static com.weare.api.Utils.Constants.COMMENT_ID;
import static com.weare.api.Utils.Constants.POST_ID;
import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CommentTest extends BaseTestSetup {
    protected static Post post;
    protected static Comment comment;
    protected static PostService postService;
    protected static CommentService commentService;
    private Cookie authCookie;
    protected Integer userId;

    @BeforeEach
    public void setupTest() {
        register();
        login();
    }

    @AfterEach
//    @AfterMethod
    public void tearDownAfterTest() {
        logout();
    }

    @BeforeEach
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
        Assertions.assertEquals(statusCode, SC_MOVED_TEMPORARILY, "Cookie status code is correct");
    }
//    @BeforeMethod
//    @Test(priority = 1)

    @Test
    @Order(1)
    public void createPost() {


        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        post = new Post();

        String postJsonBody = PostService.generatePostRequest(post);

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

        AssertHelper.assertStatusCode(SC_OK, statusCode);
        Assertions.assertFalse(privatePost,"This post is not a public");
        Assertions.assertNotNull(ContentType.JSON);
        Assertions.assertEquals(contentPost, post.getContent());

    }
//    @Test(priority = 2)
    @Test
    @Order(2)
    public void createComment() {

        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        commentService = new CommentService();
        comment = new Comment();

        userId = user.getUserId();

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
        Assertions.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assertions.assertNotNull(ContentType.JSON);
        Assertions.assertEquals(contentComment, comment.getContent(),"Content does not match");
        System.out.println(responseBody);

    }
//    @Test(priority = 3)
    @Test
    @Order(3)
    public void getComment() {
        baseURI = format("%s%s", BASE_URL, GET_COMMENT);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                //.queryParam("principal", user.getUsername())
                .when()
                .get();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 4,dependsOnMethods = "createComment")
    @Test
    @Order(4)
    public void editComment() {
        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);

        String editJsonBody = PostService.editPostRequest(post);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .queryParam("commentId", COMMENT_ID)
                .queryParam("content",comment.getContent())
                .body(editJsonBody)
                .when()
                .put();

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));

    }
//    @Test(priority = 5,dependsOnMethods = "createComment")
    @Test
    @Order(5)
    public void likeComment() {
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .queryParam("commentId", COMMENT_ID)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 6,dependsOnMethods = "likeComment")
    @Test
    @Order(6)
    public void dislikeComment() {
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .queryParam("commentId", COMMENT_ID)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 7)
    @Test
    @Order(7)
    public void getAllComment() {
        baseURI = format("%s%s", BASE_URL, FIND_ALL_COMMENTS);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .queryParam("postId", POST_ID)
                .when()
                .get();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 8)
    @Test
    @Order(8)
    public void getOneComment() {
        baseURI = format("%s%s", BASE_URL, FIND_ONE_COMMENTS);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .queryParam("commentId", COMMENT_ID)
                .when()
                .get();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 9,dependsOnMethods = "createComment")
    @Test
    @Order(9)
    public void deleteComment() {
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENTS);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
                .queryParam("commentId", COMMENT_ID)
                .when()
                .delete();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
//    @Test(priority = 10)
    @Test
    @Order(10)
    public void deletePost() {
        baseURI = format("%s%s", BASE_URL, DELETE_POST);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", authCookie.getValue())
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
