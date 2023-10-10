package pages.weare;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseWEArePage {
    public LoginPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void loginUser(String username, String password) {

        //SIGN IN button
        actions.waitForElementVisible("weare.signInButton");
        actions.clickElement("weare.signInButton");

        //username
        actions.waitForElementVisible("weare.loginUsername");
        actions.typeValueInField(username, "weare.loginUsername");

        //password
        actions.waitForElementVisible("weare.loginPassword");
        actions.typeValueInField(password, "weare.loginPassword");

        //submit form
        actions.clickElement("weare.LoginSubmitButton");
    }

    public void logoutUser() {

        //Home section
        actions.waitForElementVisible("weare.homeButton");
        actions.clickElement("weare.homeButton");

        //Logout button
        actions.waitForElementVisible("weare.logoutButton");
        actions.clickElement("weare.logoutButton");
    }

    public void assertUserIsLoggedIn() {
        //Find your professional is present
        actions.waitForElementPresent("weare.findYourProfessional");
    }

    public void assertUserIsLoggedOut() {
        //Find your professional is present
        actions.waitForElementPresent("weare.assertLoggedOutText");
    }
}
