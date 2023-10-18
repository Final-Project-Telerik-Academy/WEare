package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.models.User;
import com.weare.api.utils.AssertHelper;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static com.weare.api.utils.Endpoints.BASE_URL;
import static com.weare.api.utils.Endpoints.SEND_REQUEST;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

public class ConnectionTests extends BaseTestSetup {

    protected static User sender;
    protected static User receiver;
    private static int requestId;

//    @BeforeEach
//    public void setupTest() {
//        sender = new User();
//        register(sender);
//        receiver = new User();
//        register(receiver);
//        login(sender);
//
//    }

    @Override @BeforeEach
    protected void beforeEach() {
        sender = new User();
        register(sender);
        receiver = new User();
        register(receiver);
        login(sender);
    }

    @AfterEach
    public void tearDownAfterTest() {
        logout();
    }
    @Feature("Connections")
    @Story("Send connection request")
    @Description("Test to verify that a connection request can be sent successfully.")
    @Test
    public void sendConnectionRequest() {

        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);

        JSONObject sendRequestJsonBody = new JSONObject();
        sendRequestJsonBody.put("id", receiver.getUserId());
        sendRequestJsonBody.put("username", receiver.getUsername());

        Response response = given()
                .cookie("JSESSIONID", cookie.getValue())
                .contentType("application/json")
                .body(sendRequestJsonBody.toString())
                .when()
                .post();
        System.out.println(cookie.getValue());
        System.out.println(response.asString());
        System.out.println(cookie.getValue());
        System.out.println(response.asString());

        String responseBody = response.getBody().asString();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        String expectedResponseBody = String.format("%s send friend request to %s", sender.getUsername(), receiver.getUsername());
        AssertHelper.assertResponseBodyEquals(expectedResponseBody, responseBody);
    }

    @Feature("Connections")
    @Story("Get user requests")
    @Description("Test to verify that user requests can be retrieved successfully.")
    @Test
    public void getUserRequests() {
        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);

        JSONObject sendRequestJsonBody = new JSONObject();
        sendRequestJsonBody.put("id", receiver.getUserId());
        sendRequestJsonBody.put("username", receiver.getUsername());

        Response response = given()
                .cookie("JSESSIONID", cookie.getValue())
                .contentType("application/json")
                .body(sendRequestJsonBody.toString())
                .when()
                .post();

        logout();
        login(receiver);
        baseURI = format("http://localhost:8081/api/auth/users/%d/request/", receiver.getUserId());

         response = given()
                .cookie(cookie.getName(), cookie.getValue())
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

    @Feature("Connections")
    @Story("Approve connection request")
    @Description("Test to verify that a connection request can be approved successfully.")
    @Test
    public void approveConnectionRequest() {
        baseURI = format("%s%s", BASE_URL, SEND_REQUEST);

        JSONObject sendRequestJsonBody = new JSONObject();
        sendRequestJsonBody.put("id", receiver.getUserId());
        sendRequestJsonBody.put("username", receiver.getUsername());

        Response response = given()
                .cookie("JSESSIONID", cookie.getValue())
                .contentType("application/json")
                .body(sendRequestJsonBody.toString())
                .when()
                .post();

        login(receiver);
        baseURI = format("http://localhost:8081/api/auth/users/%d/request/", receiver.getUserId());

        response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .when()
                .get();

        System.out.println(response.asString());

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);

        JSONArray jsonArray = new JSONArray(response.asString());

            requestId = jsonArray.getJSONObject(0).getInt("id");
            System.out.println("Extracted ID from response: " + requestId);

            JSONObject jsonObject = jsonArray.getJSONObject(0);

        logout();
        login(receiver);
        baseURI = String.format("http://localhost:8081/api/auth/users/%s/request/approve", receiver.getUserId());

         response = given()
                .cookie("JSESSIONID", cookie.getValue())
                .formParam("requestId", requestId)
                .when()
                .post();

        System.out.println(response.asString());

//        int statusCode = response.getStatusCode();

        String responseBody = response.getBody().asString();
        String expectedResponseBody = String.format("%s approved request of %s", receiver.getUsername(), sender.getUsername());
        AssertHelper.assertResponseBodyEquals(expectedResponseBody, responseBody);
        AssertHelper.assertStatusCode(statusCode, SC_OK);
    }
}
