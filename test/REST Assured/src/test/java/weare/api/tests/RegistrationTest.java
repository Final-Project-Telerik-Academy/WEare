package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.User;
import com.weare.api.Services.UserService;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;

public class RegistrationTest extends BaseTestSetup {
    @Test
    public void successfulRegistration_when_createNewUser() {
        UserService userService = new UserService();
        User user = new User();

        String registrationJson = userService.generateRegistrationRequest(user);
    }
}
