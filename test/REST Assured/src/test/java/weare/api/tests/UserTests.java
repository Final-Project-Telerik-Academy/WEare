package weare.api.tests;

import base.BaseTestSetup;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;

public class UserTests extends BaseTestSetup {
    @Test
    public void updatePersonalProfileTest() {
        String formattedEndpoint = String.format(UPDATE_PERSONAL_PROFILE_ENDPOINT, userId);
        baseURI = format("%s%s", BASE_URL,formattedEndpoint);

        String updateUserJsonBody = userService.generateUpdatePersonalProfile(user);

        Response response = given()
                .cookie("JSESSIONID", cookie.getValue())
                .contentType(ContentType.JSON)
                .body(updateUserJsonBody)
                .when()
                .post();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, SC_OK, format("Incorrect status code. Expected %s.", SC_OK));
    }


    //private Cookie cookie;

//    @Test
//    public void authenticationTest() {
//        baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);
//
//        Response response = getApplicationAuthentication()
//                .when()
//                .post();
//
//        if (response.getDetailedCookie("JSESSIONID") != null) {
//            cookie = response.getDetailedCookie("JSESSIONID");
//        }
//
//        int statusCode = response.getStatusCode();
//        boolean isValidStatusCode = (statusCode == SC_OK) || (statusCode == SC_MOVED_TEMPORARILY);
//        Assert.assertTrue(isValidStatusCode, format("Incorrect status code. Expected %s.", SC_OK));
//    }
//
//    @Test(dependsOnMethods = "authenticationTest")
//        public void authenticateWithCookie() {
//            baseURI = format("%s%s", BASE_URL, AUTH_ENDPOINT);
//            String cookieValue = cookie.getValue();
//            Response response = getApplicationAuthentication()
//                    .cookie("JSESSIONID", cookieValue)
//                    .when()
//                    .post();
//
//        System.out.println("JSESSIONID cookie: " + response.getCookie("JSESSIONID"));
//        System.out.println("Response code: " + response.getStatusCode());
//
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, SC_MOVED_TEMPORARILY, "Cookie status code is correct");
//        }
}

