package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.Post;
import com.weare.api.Services.PostService;
import com.weare.api.Services.UserService;
import com.weare.api.Utils.Constants;
import com.weare.api.Utils.JSONRequests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import weare.api.tests.Utils.AssertHelper;

import static com.weare.api.Utils.Constants.POST_ID;
import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class UserTests extends BaseTestSetup {
    private Integer postId;

    @BeforeMethod
    public void setupTest() {
        register();
        login();
    }

    @AfterMethod
    public void tearDownAfterTest() {
        logout();
    }

    @Test(priority = 1)
    public void updatePersonalProfileTest() {
        String formattedEndpoint = String.format(UPDATE_PERSONAL_PROFILE_ENDPOINT, userId);
        baseURI = format("%s%s", BASE_URL,formattedEndpoint);

        String updateUserBody = UserService.generateUpdatePersonalProfile(user);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateUserBody)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }

    @Test(priority = 2)
    public void getUserByIdTest() {
        String formattedEndpoint = String.format(USER_BY_ID_ENDPOINT, userId);
        baseURI = format("%s%s", BASE_URL, formattedEndpoint);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("principal", user.getUsername())
                .when()
                .get();

        int statusCode = response.getStatusCode();
        String resUserId = response.getBody().jsonPath().getString("id");
        String resUsername = response.getBody().jsonPath().getString("username");
        String resEmail = response.getBody().jsonPath().getString("email");

        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertResponseBodyNotNull(response.getBody());
        AssertHelper.assertUserIdEquals(Integer.parseInt(resUserId), user.getUserId());
        AssertHelper.assertUsernameEquals(resUsername, user.getUsername());
        AssertHelper.assertEmailEquals(resEmail, user.getEmail());
    }

    @Test(priority = 3)
    public void searchByUserTest() {
        baseURI = format("%s%s", BASE_URL, SEARCH_USER_ENDPOINT);
        String searchUserBody = UserService.generateSearchUserRequest(user);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(searchUserBody)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);

        String resUserId = response.getBody().jsonPath().getString("[0].userId");
        String resUsername = response.getBody().jsonPath().getString("[0].username");

        AssertHelper.assertUserIdEquals(Integer.parseInt(resUserId), user.getUserId());
        AssertHelper.assertUsernameEquals(resUsername, user.getUsername());
    }

    @Test(priority = 4)
    public void createPostTest() {
        baseURI = format("%s%s", BASE_URL, CREATE_POST_ENDPOINT);
        PostService postService = new PostService();
        Post post = new Post();
        post.setPublic(true);
        String postJsonBody = postService.generatePostRequest(post);

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue()) // Use the saved authentication cookie
                .body(postJsonBody)
                .when()
                .post();

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

    @Test(priority = 5)
    public void searchUserPostsTest() {
        String formattedEndpoint = format(SEARCH_USER_POSTS_ENDPOINT, userId) ;
        baseURI = format("%s%s", BASE_URL, formattedEndpoint);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(JSONRequests.SHOW_PROFILE_POSTS)
                .when()
                .get();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        String responseBody = response.getBody().prettyPrint();

        //no post id and post content problem
      /*  String resPostId = response.getBody().jsonPath().getString("[0].postId");
        Assert.assertEquals(Integer.parseInt(resPostId), postId, "Incorrect user's post ID");
        String postContent = response.getBody().jsonPath().getString("[0].content");
        Assert.assertEquals(postContent, Constants.CONTENT_POST, "The content of the post is not the same.");*/
    }

    @Test(priority = 6)
    public void updateUserExpertiseTest() {
        String formattedString = format(UPDATE_USER_EXPERTISE_ENDPOINT, userId);
        baseURI = format("%s%s", BASE_URL, formattedString);

        String updateUserExpertiseBody = UserService.generateUpdateExpertiseProfile(user);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateUserExpertiseBody)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

        Integer resUserId = response.getBody().jsonPath().get("id");
        Integer resCategoryId = response.getBody().jsonPath().get("category.id");
        String availability = response.getBody().jsonPath().get("availability");

        AssertHelper.assertUserIdEquals(resUserId, user.getUserId());
        AssertHelper.assertCategoryIdNotNull(resCategoryId);
        AssertHelper.assertCategoryIdsMatch(resCategoryId, user.getCategoryId());
        AssertHelper.assertAvailabilityMatches(availability, Constants.AVAILABILITY);
    }
}

