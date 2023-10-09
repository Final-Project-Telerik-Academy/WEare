package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.User;
import com.weare.api.Services.UserService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Endpoints.BASE_URL;
import static com.weare.api.Utils.Endpoints.REGISTER_ENDPOINT;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationTest extends BaseTestSetup {
    @Test
    public void successfulRegistration_when_createNewUser() {
        UserService userService = new UserService();
        User user = new User();

        baseURI = format("%s%s", BASE_URL, REGISTER_ENDPOINT);

        String registrationJsonBody = userService.generateRegistrationRequest(user);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(registrationJsonBody)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
    }
}
