package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.UserC;
import com.weare.api.Services.ConnectionService;
import com.weare.api.Services.UserServiceC;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;

public class ConnectionTests extends BaseTestSetup {

    protected static UserC user;
    protected static UserC user2;
    private Cookie cookie1;
    private Cookie cookie2;
    protected static int requestId;


    @Test(priority = 1)
    public void registerUser1() {
        baseURI = String.format("%s%s", BASE_URL, REGISTER_ENDPOINT);
        user = new UserC();

        String registrationJsonBody = UserServiceC.generateRegistrationRequest(user);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(registrationJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assert.assertFalse(responseBody.trim().isEmpty());

        String regex = "name (\\w+)(.*)id (\\d+)";
        Matcher matcher = Pattern.compile(regex).matcher(responseBody);
        if (matcher.find()) {
            user.setUsername(matcher.group(1));
            user.setUserId(Integer.valueOf(matcher.group(3)));
        }

        Assert.assertEquals(user.getUsername(), user.getUsername(), "Username does not match the expected value");
        Assert.assertTrue(user.getUserId() > 0, "The user ID should be a positive integer");
        String expectedResponseBody = String.format("User with name %s and id %d was created", user.getUsername(), user.getUserId());
        Assert.assertEquals(responseBody, expectedResponseBody, "Response body does not match the expected value");

        System.out.println(response.asString());

    }

    @Test(priority = 2)
    public void registerUser2() {
        baseURI = String.format("%s%s", BASE_URL, REGISTER_ENDPOINT);
        user2 = new UserC();

        String registrationJsonBody = UserServiceC.generateRegistrationRequest(user2);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(registrationJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assert.assertFalse(responseBody.trim().isEmpty());

        String regex = "name (\\w+)(.*)id (\\d+)";
        Matcher matcher = Pattern.compile(regex).matcher(responseBody);
        if (matcher.find()) {
            user2.setUsername(matcher.group(1));
            user2.setUserId(Integer.valueOf(matcher.group(3)));
        }

        Assert.assertEquals(user2.getUsername(), user2.getUsername(), "Username does not match the expected value");
        Assert.assertTrue(user2.getUserId() > 0, "The user ID should be a positive integer");
        String expectedResponseBody = String.format("User with name %s and id %d was created", user2.getUsername(), user2.getUserId());
        Assert.assertEquals(responseBody, expectedResponseBody, "Response body does not match the expected value");
        System.out.println(response.asString());

    }

    @Test(priority = 3)
    public void authenticateUser1() {
        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);

        Response response = given()
                .formParam("username", user.getUsername())
                .formParam("password", user.getPassword())
                .when()
                .post();

        if (response.getDetailedCookie("JSESSIONID") != null) {
            cookie1 = response.getDetailedCookie("JSESSIONID");
        } else {
            cookie1 = createAuthenticationCookieWithValue("value");
        }

        int statusCode = response.getStatusCode();
        boolean isValidStatusCode = (statusCode == SC_OK) || (statusCode == SC_MOVED_TEMPORARILY);
        Assert.assertTrue(isValidStatusCode, "Incorrect status code. Expected Status 200.");
        System.out.println(response.asString());

        System.out.println("User 1 authenticated successfully - Username: " + user.getUsername() + " - Cookie: " + cookie1.getValue());
    }

    @Test(priority = 5)
    public void authenticateUser2() {
        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);

        Response response = given()
                .formParam("username", user2.getUsername())
                .formParam("password", user2.getPassword())
                .when()
                .post();

        if (response.getDetailedCookie("JSESSIONID") != null) {
            cookie2 = response.getDetailedCookie("JSESSIONID");
        } else {
            cookie2 = createAuthenticationCookieWithValue("value");
        }

        int statusCode = response.getStatusCode();
        boolean isValidStatusCode = (statusCode == SC_OK) || (statusCode == SC_MOVED_TEMPORARILY);
        Assert.assertTrue(isValidStatusCode, "Incorrect status code. Expected Status 200.");
        System.out.println(response.asString());

        System.out.println("User 2 authenticated successfully - Username: " + user2.getUsername() + " - Cookie: " + cookie2.getValue());
    }

    @Test(priority = 4)
    public void sendConnectionRequest() {

        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);

        String sendRequestJsonBody = ConnectionService.generateSendRequest(user2.getUserId(), user2.getUsername());

        Response response = given()
                .cookie("JSESSIONID", cookie1.getValue())
                .contentType("application/json")
                .body(sendRequestJsonBody)
                .when()
                .post();

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

        String expectedResponseBody = String.format("%s send friend request to %s", user.getUsername(), user2.getUsername());
        Assert.assertEquals(response.getBody().asString(), expectedResponseBody, "Response body does not match the expected value.");
    }

    @Test(priority = 6)
    public void getUserRequests() {
        baseURI = format("http://localhost:8081/api/auth/users/%d/request/", user2.getUserId());

        Response response = given()
                .cookie(cookie2.getName(), cookie2.getValue())

                .when()
                .get();

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

        JSONArray jsonArray = new JSONArray(response.asString());

        if (jsonArray.length() > 0) {
            requestId = jsonArray.getJSONObject(0).getInt("id");
            System.out.println("Extracted ID from response: " + requestId);

            JSONObject jsonObject = jsonArray.getJSONObject(0);

            Assert.assertTrue(jsonObject.has("id"), "Field 'id' not found in the response.");
            Assert.assertTrue(jsonObject.has("approved"), "Field 'approved' not found in the response.");
            Assert.assertTrue(jsonObject.has("seen"), "Field 'seen' not found in the response.");
            Assert.assertTrue(jsonObject.has("timeStamp"), "Field 'timeStamp' not found in the response.");
        } else {
            System.out.println("No items found in the response or ID not found.");
        }
    }

    @Test(priority = 7)
    public void approveConnectionRequest() {
        baseURI = String.format("http://localhost:8081/api/auth/users/%s/request/approve", user2.getUserId());

        Response response = given()
                .cookie("JSESSIONID", cookie2.getValue())
                .formParam("requestId", requestId)
                .when()
                .post();

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();

        String responseBody = response.getBody().asString();
        String expectedResponseBody = String.format("%s approved request of %s", user2.getUsername(), user.getUsername());
        Assert.assertEquals(responseBody, expectedResponseBody, "Response body does not match the expected value.");
        Assert.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

    }
}