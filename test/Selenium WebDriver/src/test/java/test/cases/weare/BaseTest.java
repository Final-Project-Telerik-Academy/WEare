package test.cases.weare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.weare.*;

public class BaseTest {

    protected String password;
    protected String username;
    protected String email;
    protected String adminUsername;
    protected String adminPassword;
    protected String adminEmail;

    UserActions actions = new UserActions();

    RegistrationPage registrationPage = new RegistrationPage(actions.getDriver());
    LoginPage loginPage = new LoginPage(actions.getDriver());
    AdminPage adminPage = new AdminPage(actions.getDriver());
    PostPage postPage = new PostPage(actions.getDriver());
    CommentPage commentPage = new CommentPage((actions.getDriver()));

    HomePage homePage=new HomePage(actions.getDriver());
    PersonalProfilePage updatePersonalProfilePage = new PersonalProfilePage(actions.getDriver());

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

    protected void registerAsAdmin() {
        adminUsername = "admin" + generateRandomUsername();
        adminEmail = generateRandomEmail();
        adminPassword = generatePassword();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        registrationPage.assertAdminRegistered();
    }

    protected void login() {
        loginPage.loginUser(username, password);
        loginPage.assertUserIsLoggedIn();
    }

    protected void loginAsAdmin() {
        adminPage.loginAdmin(adminUsername, adminPassword);
        adminPage.assertAdminIsLoggedIn();
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

    protected String generatePasswordEightSymbols() {
        StringBuilder password = new StringBuilder();
        String symbols = "1234567890!@#$%^&*";

        String fakeWords = faker.lorem().characters(8 - symbols.length());
        password.append(fakeWords);

        for (int i = 0; i < 8 - fakeWords.length(); i++) {
            int index = faker.random().nextInt(symbols.length());
            password.append(symbols.charAt(index));
        }
        return password.toString();
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

