package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.RandomGenerator.*;

public class LoginTest extends BaseTest {

    String invalidUsername = generateRandomUsername(1);
    String invalidPassword = generateRandomPassword(5);

    @Feature("Login")
    @Story("Login user with valid credentials successfully.")
    @Test
    protected void loginWithValidDetails() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(8);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
        loginPage.loginUser(username, password);
        loginPage.assertUserIsLoggedIn();
    }

    @Feature("Login")
    @Story("Login user with invalid username unsuccessfully.")
    @Test
    public void loginWithInvalidUsername() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
        loginPage.loginUserWithInvalidUsername(invalidUsername, password);
        loginPage.assertUserNotLoggedInWithInvalidCredentials();
    }

    @Feature("Login")
    @Story("Login user with invalid password unsuccessfully.")
    @Test
    public void loginWithInvalidPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
        loginPage.loginUserWithInvalidPassword(username, invalidPassword);
        loginPage.assertUserNotLoggedInWithInvalidCredentials();
    }

    @Feature("Login")
    @Story("Login user with invalid credentials unsuccessfully.")
    @Test
    public void loginWithInvalidCredentials() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
        loginPage.loginUserWithInvalidCredentials(invalidUsername, invalidPassword);
        loginPage.assertUserNotLoggedInWithInvalidCredentials();
    }

    @Feature("Logout")
    @Story("Logout user successfully.")
    @Test
    public void logoutUser() {
        loginWithValidDetails();
        loginPage.logoutUser();
        loginPage.assertUserIsLoggedOut();

    }
}
