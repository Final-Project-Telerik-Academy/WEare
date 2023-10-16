package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.UserC;
import com.weare.api.Services.ConnectionService;
import com.weare.api.Services.UserServiceC;
import com.weare.api.Utils.AssertHelper;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static org.apache.http.HttpStatus.SC_OK;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ConnectionTests extends BaseTestSetup {

    protected static UserC user;
    protected static UserC user2;
    private Cookie cookie1;
    private Cookie cookie2;
    protected static int requestId;


    @Test
    @Order(1)
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


        String regex = "name (\\w+)(.*)id (\\d+)";
        Matcher matcher = Pattern.compile(regex).matcher(responseBody);
        if (matcher.find()) {
            user.setUsername(matcher.group(1));
            user.setUserId(Integer.valueOf(matcher.group(3)));
        }

     int statusCode = response.getStatusCode();

        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertUsername(user.getUsername(), user.getUsername());
        AssertHelper.assertPositiveUserId(user.getUserId());
        String expectedResponseBody = String.format("User with name %s and id %d was created", user.getUsername(), user.getUserId());
        AssertHelper.assertResponseBodyEquals(expectedResponseBody, responseBody);
        AssertHelper.assertNotEmptyResponse(responseBody);

        System.out.println(response.asString());
    }

    @Test
    @Order(2)
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


        String regex = "name (\\w+)(.*)id (\\d+)";
        Matcher matcher = Pattern.compile(regex).matcher(responseBody);
        if (matcher.find()) {
            user2.setUsername(matcher.group(1));
            user2.setUserId(Integer.valueOf(matcher.group(3)));
        }
        int statusCode = response.getStatusCode();

        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertUsername(user.getUsername(), user.getUsername());
        AssertHelper.assertPositiveUserId(user.getUserId());
        String expectedResponseBody = String.format("User with name %s and id %d was created", user2.getUsername(), user2.getUserId());
        AssertHelper.assertResponseBodyEquals(expectedResponseBody, responseBody);
        AssertHelper.assertNotEmptyResponse(responseBody);
        System.out.println(response.asString());
    }
    @Test
    @Order(3)
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
        AssertHelper.assertValidStatusCode(isValidStatusCode);

        System.out.println(response.asString());

        System.out.println("User 1 authenticated successfully - Username: " + user.getUsername() + " - Cookie: " + cookie1.getValue());
    }
    @Test
    @Order(5)
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
        AssertHelper.assertValidStatusCode(isValidStatusCode);
        System.out.println(response.asString());

        System.out.println("User 2 authenticated successfully - Username: " + user2.getUsername() + " - Cookie: " + cookie2.getValue());
    }
    @Test
    @Order(4)
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

        String responseBody = response.getBody().asString();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        String expectedResponseBody = String.format("%s send friend request to %s", user.getUsername(), user2.getUsername());
        AssertHelper.assertResponseBodyEquals(expectedResponseBody, responseBody);
    }

    @Test
    @Order(6)
    public void getUserRequests() {
        baseURI = format("http://localhost:8081/api/auth/users/%d/request/", user2.getUserId());

        Response response = given()
                .cookie(cookie2.getName(), cookie2.getValue())

                .when()
                .get();

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);

        JSONArray jsonArray = new JSONArray(response.asString());

        if (jsonArray.length() > 0) {
            requestId = jsonArray.getJSONObject(0).getInt("id");
            System.out.println("Extracted ID from response: " + requestId);

            JSONObject jsonObject = jsonArray.getJSONObject(0);

            AssertHelper.assertJsonFieldExists(jsonObject, "id");
            AssertHelper.assertJsonFieldExists(jsonObject, "approved");
            AssertHelper.assertJsonFieldExists(jsonObject, "seen");
            AssertHelper.assertJsonFieldExists(jsonObject, "timeStamp");
        } else {
            System.out.println("No items found in the response or ID not found.");
        }
    }
    @Test
    @Order(7)
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
        AssertHelper.assertResponseBodyEquals(expectedResponseBody, responseBody);
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
}
