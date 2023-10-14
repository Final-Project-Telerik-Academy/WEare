package test.cases.weare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.weare.CommentPage;
import pages.weare.LoginPage;
import pages.weare.PostPage;
import pages.weare.RegistrationPage;

public class BaseTest {

    protected String password;
    protected String username;

    protected String email;

    UserActions actions = new UserActions();

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());
    CommentPage commentPage = new CommentPage((actions.getDriver()));
    Faker faker = new Faker();


    @BeforeEach
    protected void setUp() {
        UserActions.loadBrowser("weare.homepage");
    }

    @AfterEach
    protected void tearDown() {
        UserActions.quitDriver();
    }

    protected void register() {
        username = generateRandomUsername();
        email = generateRandomEmail();
        password = generatePassword();
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    protected void login() {
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

    protected String generateRandomUsername() {
        String username = faker.lorem().characters(2, 20, false, false);
        return username.toLowerCase();
    }

    protected String generatePassword() {
        return faker.internet().password(8, 20, true, true, true);
    }

    protected static String generateTwoCharacterUsername() {
        Faker faker = new Faker();
        return faker.lorem().characters(2, false, false);
    }

    protected static String generateTwentyCharacterUsername() {
        Faker faker = new Faker();
        return faker.lorem().characters(20, false, false);
    }
}

