package base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.weare.api.models.User;
import com.weare.api.services.UserService;
import com.weare.api.utils.AssertHelper;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.junit.jupiter.api.*;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.weare.api.utils.Endpoints.*;
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

    protected void register(User user) {
        baseURI = String.format("%s%s", BASE_URL, REGISTER_ENDPOINT);
//        user = new User();

        username = user.getUsername();
        password = user.getPassword();

        String registrationJsonBody = UserService.registrationRequest(user);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(registrationJsonBody)
                .when()
                .post();

        String responseBody = response.getBody().asString();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
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

/*    public void login(User user) {
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
    }*/

    public void login(User user) {
        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);

        Response response = getApplicationAuthentication()
                .when()
                .post();

        // Генериране на уникално куки за всеки потребител
        cookie = new Cookie.Builder("JSESSIONID", user.getUserId() + "_" + System.currentTimeMillis()).setPath("/").build();

        System.out.println("User authenticated successfully - Username: " + user.getUsername() + " - Cookie: " + cookie.getValue());

        int statusCode = response.getStatusCode();
        boolean isValidStatusCode = (statusCode == SC_OK) || (statusCode == SC_MOVED_TEMPORARILY);
        Assertions.assertTrue(isValidStatusCode, "Incorrect status code. Expected Status 200.");
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
