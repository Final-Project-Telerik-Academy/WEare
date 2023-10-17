package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.models.Comment;
import com.weare.api.models.Post;
import com.weare.api.services.CommentService;
import com.weare.api.services.PostService;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import com.weare.api.utils.AssertHelper;

import static com.weare.api.services.CommentService.*;
import static com.weare.api.services.PostService.deletePostApi;
import static com.weare.api.services.UserService.createPostApi;
import static com.weare.api.utils.Constants.COMMENT_ID;
import static com.weare.api.utils.Constants.POST_ID;
import static com.weare.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CommentTest extends BaseTestSetup {
    protected static Post post;
    protected static Comment comment;
    private Cookie authCookie;
    protected Integer userId;

    @BeforeEach
    public void setupTest() {
        register();
        login();
    }

    @AfterEach
    public void tearDownAfterTest() {
        logout();
    }

    @Test
    @Order(1)
    public void createPost() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        post = new Post();

        String postJsonBody = PostService.generatePostRequest(post);
        Response response = createPostApi(postJsonBody, cookie);

        POST_ID = response.getBody().jsonPath().get("postId");
        String contentPost=response.getBody().jsonPath().get("content");
        Boolean privatePost=response.getBody().jsonPath().get("public");

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(SC_OK, statusCode);
        AssertHelper.assertPostIsPrivate(privatePost);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertContentEquals(contentPost, post.getContent());
    }

    @Test
    @Order(2)
    public void createComment() {
        baseURI = format("%s%s", BASE_URL, CREATE_COMMENT_ENDPOINT);
        comment = new Comment();

        userId = user.getUserId();
        comment.setUserId(userId);

        String commentJsonBody = CommentService.generateCommentRequest(comment);
        Response response = createCommentApi(commentJsonBody, cookie);

        COMMENT_ID = response.getBody().jsonPath().get("commentId");
        String contentComment = response.getBody().jsonPath().get("content");

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertContentTypeNotNull(ContentType.JSON);
        AssertHelper.assertContentEquals(contentComment, comment.getContent());
    }

    @Test
    @Order(3)
    public void getComment() {
        baseURI = format("%s%s", BASE_URL, GET_COMMENT);

        Response response = getCommentApi(cookie);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
//    @Test(priority = 4,dependsOnMethods = "createComment")
    @Test
    @Order(4)
    public void editComment() {
        baseURI = format("%s%s", BASE_URL, EDIT_COMMENT);

        String editJsonBody = PostService.editPostRequest(post);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", cookie.getValue())
                .queryParam("commentId", COMMENT_ID)
                .queryParam("content",comment.getContent())
                .body(editJsonBody)
                .when()
                .put();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
//    @Test(priority = 5,dependsOnMethods = "createComment")
    @Test
    @Order(5)
    public void likeComment() {
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        Response response = likeCommentApi(cookie, COMMENT_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
//    @Test(priority = 6,dependsOnMethods = "likeComment")
    @Test
    @Order(6)
    public void dislikeComment() {
        baseURI = format("%s%s", BASE_URL, LIKE_COMMENT);

        Response response = diskCommentApi(cookie, COMMENT_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Test
    @Order(7)
    public void getAllComment() {
        baseURI = format("%s%s", BASE_URL, FIND_ALL_COMMENTS);

        Response response = getAllCommentApi(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Test
    @Order(8)
    public void getOneComment() {
        baseURI = format("%s%s", BASE_URL, FIND_ONE_COMMENTS);

        Response response = getOneCommentApi(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
//    @Test(priority = 9,dependsOnMethods = "createComment")
    @Test
    @Order(9)
    public void deleteComment() {
        baseURI = format("%s%s", BASE_URL, DELETE_COMMENTS);

        Response response =deleteCommentApi(cookie, COMMENT_ID);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Test
    @Order(10)
    public void deletePost() {
        baseURI = format("%s%s", BASE_URL, DELETE_POST);

        Response response = deletePostApi(cookie, POST_ID);

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
}