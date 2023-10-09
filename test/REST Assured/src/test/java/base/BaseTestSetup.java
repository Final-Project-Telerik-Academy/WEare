package base;

import java.time.Instant;
import java.time.format.DateTimeFormatter;


import com.github.javafaker.Faker;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.weare.api.Utils.Endpoints.BASE_URL;
import static com.weare.api.Utils.Constants.*;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertTrue;

public class BaseTestSetup {
    @BeforeSuite
    public void initialSetup() {
/*        baseURI = BASE_URL;

        Faker faker = new Faker();

        USERNAME = generateRandomUsername(faker);
        EMAIL = generateRandomEmail(faker);*/
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

/*    private static String generateRandomUsername(Faker faker) {
        return faker.name().username();
    }
    private String generateRandomEmail(Faker faker) {
        return faker.internet().emailAddress();
    }*/
}
