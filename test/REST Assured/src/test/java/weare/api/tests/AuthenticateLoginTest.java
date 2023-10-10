package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Constants.*;
import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class AuthenticateLoginTest extends BaseTestSetup {

    @Test
    public void authenticationTest() {
        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);

        Response response = getApplicationAuthentication()
                .contentType(ContentType.JSON)
                .when()
                .post();

        String responseBody = response.getBody().asString();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
    }




   /* @Test
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

}*/
}

