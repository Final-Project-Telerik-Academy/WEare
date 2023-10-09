package pages.weare;

import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BaseWEArePage{
    public RegistrationPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }
    public void userRegistration(String username, String email, String password) {

        //click Register
        actions.waitForElementPresent("weare.registerButton");
        actions.clickElement("weare.registerButton");

        //username
        actions.waitForElementPresent("weare.usernameInput");
        actions.typeValueInField(username, "weare.usernameInput");

        //email
        actions.waitForElementPresent("weare.emailInput");
        actions.typeValueInField(email, "weare.emailInput");

        //password
        actions.waitForElementPresent("weare.passwordInput");
        actions.typeValueInField(password, "weare.passwordInput");

        //confirm password
        actions.waitForElementPresent("weare.confirmPasswordInput");
        actions.typeValueInField(password, "weare.confirmPasswordInput");

        //register button
        actions.clickElement("weare.submitButton");
    }
    public void assertUserRegistered() {
        //Welcome to our community header is present
        actions.waitForElementPresent("weare.welcomeHeader");
        //Please update your profile is present
        actions.waitForElementPresent("weare.pleaseUpdateYourProfileButton");
    }
}
