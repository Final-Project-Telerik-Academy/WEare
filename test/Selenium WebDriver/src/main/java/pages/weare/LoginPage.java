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
    public void assertUserIsLoggedIn() {
        //Find your professional is present
        actions.waitForElementPresent("weare.findYourProfessional");
    }
}
