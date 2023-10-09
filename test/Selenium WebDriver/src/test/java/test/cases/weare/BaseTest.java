package test.cases.weare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.weare.LoginPage;
import pages.weare.RegistrationPage;

public class BaseTest {
    UserActions actions = new UserActions();

    @BeforeEach
    public void setUp() {

        UserActions.loadBrowser("weare.homepage");
        Faker faker = new Faker();
        String username = generateRandomLowercaseUsername(faker);
        String email = generateRandomEmail(faker);

        RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());
        String password = "P@ssw0rd";
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();

        login(username, password);
    }

    @AfterEach
    public void tearDown() {
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

    private void login(String username, String password) {
        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser(username, password);
        loginPage.assertUserIsLoggedIn();
    }
}

