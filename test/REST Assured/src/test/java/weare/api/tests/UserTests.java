package weare.api.tests;

import base.BaseTestSetup;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class UserTests extends BaseTestSetup {
    @Test
    public void updatePersonalProfileTest() {
        String formattedEndpoint = String.format(UPDATE_PERSONAL_PROFILE_ENDPOINT, userId);
        baseURI = format("%s%s", BASE_URL,formattedEndpoint);

        String updateUserBody = userService.generateUpdatePersonalProfile(user);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateUserBody)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");
    }

    @Test
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

        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");
        Assert.assertNotNull(response.getBody(), "Response body is null");
        Assert.assertEquals(resUserId, user.getUserId(), "Incorrect user ID");
        Assert.assertEquals(resUsername, user.getUsername(), "Incorrect username");
        Assert.assertEquals(resEmail, user.getEmail(), "Incorrect email");
    }

    @Test
    public void searchByUserTest() {
        baseURI = format("%s%s", BASE_URL, SEARCH_USER_ENDPOINT);

        String searchUserBody = userService.generateSearchUserRequest(user);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .body(searchUserBody)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

        String resUserId = response.getBody().jsonPath().getString("id");
        String resUsername = response.getBody().jsonPath().getString("username");
        Assert.assertEquals(resUserId, user.getUserId(), "Incorrect user ID");
        Assert.assertEquals(resUsername, user.getUsername(), "Incorrect username");
    }
}

