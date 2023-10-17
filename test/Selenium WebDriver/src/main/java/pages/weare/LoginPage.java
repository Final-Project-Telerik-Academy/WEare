package pages.weare;

import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void loginUser(String username, String password) {
        actions.waitForElementVisible("weare.signInButton");
        actions.clickElement("weare.signInButton");
        actions.waitForElementVisible("weare.loginUsername");
        actions.typeValueInField(username, "weare.loginUsername");
        actions.waitForElementVisible("weare.loginPassword");
        actions.typeValueInField(password, "weare.loginPassword");
        actions.clickElement("weare.LoginSubmitButton");
    }

    public void loginUserWithInvalidUsername(String invalidUsername, String validPassword) {
        actions.waitForElementVisible("weare.signInButton");
        actions.clickElement("weare.signInButton");
        actions.waitForElementVisible("weare.loginUsername");
        actions.typeValueInField(invalidUsername, "weare.loginUsername");
        actions.waitForElementVisible("weare.loginPassword");
        actions.typeValueInField(validPassword, "weare.loginPassword");
        actions.clickElement("weare.LoginSubmitButton");
    }

    public void loginUserWithInvalidPassword(String validUsername, String invalidPassword) {
        actions.waitForElementVisible("weare.signInButton");
        actions.clickElement("weare.signInButton");
        actions.waitForElementVisible("weare.loginUsername");
        actions.typeValueInField(validUsername, "weare.loginUsername");
        actions.waitForElementVisible("weare.loginPassword");
        actions.typeValueInField(invalidPassword, "weare.loginPassword");
        actions.clickElement("weare.LoginSubmitButton");
    }

    public void loginUserWithInvalidCredentials(String invalidUsername, String invalidPassword) {
        actions.waitForElementVisible("weare.signInButton");
        actions.clickElement("weare.signInButton");
        actions.waitForElementVisible("weare.loginUsername");
        actions.typeValueInField(invalidUsername, "weare.loginUsername");
        actions.waitForElementVisible("weare.loginPassword");
        actions.typeValueInField(invalidPassword, "weare.loginPassword");
        actions.clickElement("weare.LoginSubmitButton");
    }

    public void logoutUser() {
        actions.waitForElementVisible("weare.homeButton");
        actions.clickElement("weare.homeButton");
        actions.waitForElementVisible("weare.logoutButton");
        actions.clickElement("weare.logoutButton");
    }

    public void assertUserIsLoggedIn() {
        actions.assertElementPresent("weare.findYourProfessional");
    }

    public void assertUserIsLoggedOut() {
        actions.assertElementPresent("weare.assertLoggedOutText");
    }
    public void assertUserNotLoggedInWithInvalidCredentials() {
        actions.assertElementPresent("weare.wrongUsernameOrPassword");
    }
}


