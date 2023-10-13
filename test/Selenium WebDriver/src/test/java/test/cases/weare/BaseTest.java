package test.cases.weare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.weare.LoginPage;
import pages.weare.PostPage;
import pages.weare.RegistrationPage;

import java.security.SecureRandom;

public class BaseTest {

    protected String password;
    protected String username;

    protected String email;

    UserActions actions = new UserActions();

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());
    Faker faker = new Faker();


    @BeforeEach
    public void setUp() {
        UserActions.loadBrowser("weare.homepage");
    }

    @AfterEach
    public void tearDown() {
        UserActions.quitDriver();
    }

    protected void register() {
        username = generateRandomUsername();
        email = generateRandomEmail();
        password = generateEightSymbolsPassword();
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    protected void login(String username, String password) {
        loginPage.loginUser(username, password);
        loginPage.assertUserIsLoggedIn();
    }
    protected void logout() {
        loginPage.logoutUser();
        loginPage.assertUserIsLoggedOut();
    }

    protected String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String generateRandomUsername() {
        String username = faker.lorem().characters(2, 20, false, false);
        return username.toLowerCase();
    }

    protected String generateEightSymbolsPassword() {
        return faker.internet().password(8, 20,  true, true, true);
    }

    public static String generateTwoCharacterUsername() {
        Faker faker = new Faker();
        return faker.lorem().characters(2, false, false);
    }

    public static String generateTwentyCharacterUsername() {
        Faker faker = new Faker();
        return faker.lorem().characters(20, false, false);
    }
}

