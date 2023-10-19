package test.cases.weare;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.weare.*;

import static com.telerikacademy.testframework.RandomGenerator.*;


public class BaseTest {

    protected String password;
    protected String confirmedPassword;
    protected String username;
    protected String email;

    UserActions actions = new UserActions();

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    AdminPage adminPage = new AdminPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());
    CommentPage commentPage = new CommentPage((actions.getDriver()));
    HomePage homePage = new HomePage(actions.getDriver());
    PersonalProfilePage updatePersonalProfilePage = new PersonalProfilePage(actions.getDriver());

    @BeforeEach
    protected void setUp() {
        UserActions.loadBrowser("weare.homepage");
    }

    @AfterEach
    protected void tearDown() {
        UserActions.quitDriver();
    }

    protected void UserRegistered_When_ValidCredentialsEntered() {
        username = generateRandomUsername();
        email = generateRandomEmail();
        password = generateRandomPassword(8);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    protected void UserLoggedIn_When_ValidDetailsEntered() {
        loginPage.loginUser(username, password);
        loginPage.assertUserIsLoggedIn();
    }

    protected void UserLoggedOut_When_ClickLogout() {
        loginPage.logoutUser();
        loginPage.assertUserIsLoggedOut();
    }
}

