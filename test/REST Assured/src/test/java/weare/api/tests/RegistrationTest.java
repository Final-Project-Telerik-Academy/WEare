package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.User;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Endpoints.BASE_URL;
import static com.weare.api.Utils.Endpoints.REGISTER_ENDPOINT;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;

public class RegistrationTest extends BaseTestSetup {
    @Test
    public void successfulRegistration_when_createNewUser() {

        baseURI = format("%s%s", BASE_URL, REGISTER_ENDPOINT);
    }
}
