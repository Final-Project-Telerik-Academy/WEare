package weare.api.tests;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Constants.*;
import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;

public class AuthenticateLoginTest extends BaseTestSetup {
    @Test
    public void successfulRegistration_when_createNewUser() {
        baseURI = format("%s%s", BASE_URL, REGISTER_ENDPOINT);
    }

    @Test
public void testAuthentication() {
    // Set the base URL for your localhost
    RestAssured.baseURI = "http://localhost:8081";

    // Send a POST request with form parameters
    Response response = RestAssured.given()
            .formParam("username", USERNAME )
            .formParam("password", PASSWORD)
            .when()
            .post(AUTH_ENDPOINT);

    // Extract the cookie from the response
    COOKIE = response.getCookie("JSESSIONID");

    System.out.println("Cookie Value: " + COOKIE);

}
}

