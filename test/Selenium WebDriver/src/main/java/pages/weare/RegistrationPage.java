package pages.weare;

import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void userRegistration(String username, String email, String password) {
        actions.waitForElementPresent("weare.registerButton");
        actions.clickElement("weare.registerButton");
        actions.waitForElementPresent("weare.usernameInput");
        actions.typeValueInField(username, "weare.usernameInput");
        actions.waitForElementPresent("weare.emailInput");
        actions.typeValueInField(email, "weare.emailInput");
        actions.waitForElementPresent("weare.passwordInput");
        actions.typeValueInField(password, "weare.passwordInput");
        actions.waitForElementPresent("weare.confirmPasswordInput");
        actions.typeValueInField(password, "weare.confirmPasswordInput");
        actions.clickElement("weare.submitButton");
    }

    public void assertUserRegistered() {
        actions.assertElementPresent("weare.welcomeHeader");
        actions.assertElementPresent("weare.pleaseUpdateYourProfileButton");
    }

    public void assertAdminRegistered() {
        actions.assertElementPresent("weare.assertWelcomeMessage");
        actions.assertElementPresent("weare.pleaseUpdateYourProfileButton");
    }
}
