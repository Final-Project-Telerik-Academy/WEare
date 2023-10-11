package test.cases.weare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.weare.LoginPage;
import pages.weare.RegistrationPage;

public class BaseTest {

    private String username;
    private String email;
    private String password = "P@ssw0rd";
    protected String publicPostMessage = "This is my new post";
    protected String privatePostMessage = "This is my private post";
    protected String editedPostMessage = "This is my edited post";
    UserActions actions = new UserActions();

    @BeforeEach
    public void setUp() {

        UserActions.loadBrowser("weare.homepage");
        Faker faker = new Faker();
        username = generateRandomLowercaseUsername(faker);
        email = generateRandomEmail(faker);

        register(username, email, password);
        login(username, password);
    }

    @AfterEach
    public void tearDown() {
        logout();
        UserActions.quitDriver();
    }

    private String generateRandomLowercaseUsername(Faker faker) {
        String randomUsername = faker.name().username();
        return removeNonLowercaseLetters(randomUsername);
    }

    private String generateRandomEmail(Faker faker) {
        return faker.internet().emailAddress();
    }

    private String removeNonLowercaseLetters(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    private void register(String username, String email, String password) {
        RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    private void login(String username, String password) {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser(username, password);
        loginPage.assertUserIsLoggedIn();
    }
    public void logout() {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.logoutUser();
        loginPage.assertUserIsLoggedOut();
    }
}

