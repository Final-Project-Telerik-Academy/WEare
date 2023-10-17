package pages.weare;

import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void userRegistration(String username, String email, String password) {
        registrationLogic(username, email, password);
        actions.waitForElementPresent("weare.confirmPasswordInput");
        actions.typeValueInField(password, "weare.confirmPasswordInput");
        actions.clickElement("weare.submitButton");
    }

    public void userRegistrationWithDifferentConfirmedPassword(String username, String email, String password, String confirmedPassword) {
        registrationLogic(username, email, password);
        actions.waitForElementPresent("weare.confirmPasswordInput");
        actions.typeValueInField(confirmedPassword, "weare.confirmPasswordInput");
        actions.clickElement("weare.submitButton");
    }

    private void registrationLogic(String username, String email, String password) {
        actions.waitForElementPresent("weare.registerButton");
        actions.clickElement("weare.registerButton");
        actions.waitForElementPresent("weare.usernameInput");
        actions.typeValueInField(username, "weare.usernameInput");
        actions.waitForElementPresent("weare.emailInput");
        actions.typeValueInField(email, "weare.emailInput");
        actions.waitForElementPresent("weare.passwordInput");
        actions.typeValueInField(password, "weare.passwordInput");
    }

    public void assertUserRegistered() {
        actions.assertElementPresent("weare.welcomeHeader");
        actions.assertElementPresent("weare.pleaseUpdateYourProfileButton");
    }

    public void assertUserNotRegisteredWithUnderTheRangeCharsForUsername() {
        actions.assertElementPresent("weare.serverErrorMessage");
        actions.assertElementPresent("weare.invalidUsernameRange");

    }

    public void assertAdminRegistered() {
        actions.assertElementPresent("weare.assertWelcomeMessage");
        actions.assertElementPresent("weare.pleaseUpdateYourProfileButton");
    }

    public void assertUserNotRegisteredWithAlreadyUsedUsername() {
        actions.assertElementPresent("weare.errorMessageAlreadyUsedUsername");
    }

    public void assertUserNotRegisteredWithInvalidPassword() {
        actions.assertElementPresent("weare.invalidPasswordErrorMessage");
    }

    public void assertUserNotRegisteredWithDifferentConfirmedPassword() {
        actions.assertElementPresent("weare.differentConfirmedPasswordErrorMessage");
    }


}
