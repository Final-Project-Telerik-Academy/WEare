package base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weare.api.Models.User;
import com.weare.api.Services.UserService;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.junit.jupiter.api.*;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class BaseTestSetup {
    protected String username;
    protected String password;
    protected Integer userId;
    protected User user;
    protected Cookie cookie;

    protected void register() {
        baseURI = String.format("%s%s", BASE_URL, REGISTER_ENDPOINT);
        user = new User();

        username = user.getUsername();
        password = user.getPassword();

        String registrationJsonBody = UserService.generateRegistrationRequest(user);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(registrationJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
        Assertions.assertFalse(responseBody.trim().isEmpty());

        String regex = "name (\\w+)(.*)id (\\d+)";
        Matcher matcher = Pattern.compile(regex).matcher(responseBody);
        if (matcher.find()) {
            username = matcher.group(1);
            userId = Integer.parseInt(matcher.group(3));
        }

        user.setUserId(userId);

        Assertions.assertEquals(username, user.getUsername(), "Username does not match expected value");
        Assertions.assertTrue(userId > 0, "The user ID should be a positiveinteger");
        System.out.println(response.asString());
    }

    public void login() {
        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);

        Response response = getApplicationAuthentication()
                .when()
                .post();

        if (response.getDetailedCookie("JSESSIONID") != null) {
            cookie = response.getDetailedCookie("JSESSIONID");
        } else {
            cookie = createAuthenticationCookieWithValue("value");
        }

        int statusCode = response.getStatusCode();
        boolean isValidStatusCode = (statusCode == SC_OK) || (statusCode == SC_MOVED_TEMPORARILY);
        Assertions.assertTrue(isValidStatusCode, "Incorrect status code. Expected Status 200.");
        System.out.println("User 1 authenticated successfully - Username: " + user.getUsername() + " - Cookie: " + cookie.getValue());
    }

    protected void logout() {
        cookie = null;
    }

    protected Cookie createAuthenticationCookieWithValue(String value) {
        return new Cookie.Builder("JSESSIONID", value).setPath("/").build();
    }

    /**
     * Provided configuration resolve REST Assured issue with a POST request without request body.
     * Missing configuration leads to response status code 415 (Unsupported Media Type)
     */
//    @BeforeSuite
    @BeforeAll
    public static void setup() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
            .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
    }

    public RequestSpecification getApplicationAuthentication() {
        return given()
                .multiPart("username", username)
                .multiPart("password", password);
    }
}
