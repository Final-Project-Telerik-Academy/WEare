package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.User;
import com.weare.api.Services.UserService;
import com.weare.api.Utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.weare.api.Utils.Endpoints.BASE_URL;
import static com.weare.api.Utils.Endpoints.REGISTER_ENDPOINT;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationTest extends BaseTestSetup {
    String username;
    String userId;

    @Test
    public void successfulRegistration_when_createNewUser() {
        UserService userService = new UserService();
        User user = new User();
        setCredentials(user.getUsername(), user.getPassword());

        baseURI = format("%s%s", BASE_URL, REGISTER_ENDPOINT);

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
}
