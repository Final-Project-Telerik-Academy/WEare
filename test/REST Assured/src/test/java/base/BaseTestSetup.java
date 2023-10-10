package base;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.github.javafaker.Faker;
import com.weare.api.Models.User;
import com.weare.api.Services.UserService;
import com.weare.api.Utils.Constants;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.weare.api.Utils.Constants.*;
import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertTrue;

public class BaseTestSetup {
    protected String username;
    protected String password;
    private String userId;
    protected static User user;

    @BeforeClass
    public void setupUser() {
        baseURI = String.format("%s%s", BASE_URL, REGISTER_ENDPOINT);
        UserService userService = new UserService();
        user = new User();

        username = user.getUsername();
        password = user.getPassword();

        String registrationJsonBody = userService.generateRegistrationRequest(user);

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
            username = matcher.group(1);
            userId = matcher.group(3);
        }

        Assert.assertEquals(username, user.getUsername(), "Username does not match expected value");
        Assert.assertTrue(Integer.parseInt(userId) > 0, "User ID is not valid.");
    }

    /**
     * Provided configuration resolve REST Assured issue with a POST request without request body.
     * Missing configuration leads to response status code 415 (Unsupported Media Type)
     */
    @BeforeSuite
    public void setup() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
            .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
    }

    public RequestSpecification getApplicationAuthentication() {
        return given()
                .multiPart("username", username)
                .multiPart("password", password)
                .log().all();
    }
}
