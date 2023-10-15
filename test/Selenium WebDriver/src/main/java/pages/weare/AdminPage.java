package pages.weare;

import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage {
    public AdminPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void loginAdmin(String username, String password) {
        actions.waitForElementVisible("weare.signInButton");
        actions.clickElement("weare.signInButton");
        actions.waitForElementVisible("weare.loginUsername");
        actions.typeValueInField(username, "weare.loginUsername");
        actions.waitForElementVisible("weare.loginPassword");
        actions.typeValueInField(password, "weare.loginPassword");
        actions.clickElement("weare.LoginSubmitButton");
    }

    public void userPersonalProfile() {
        actions.waitForElementPresent("weare.searchUsersField");
        actions.waitFor(500);
        actions.typeValueInField("Ivan Ivanov", "weare.searchUsersField");
        actions.waitFor(500);
        actions.clickElement("weare.searchButton");
        actions.waitForElementPresent("weare.seeProfileButton");
        actions.hoverOverElement("weare.seeProfileButton");
        actions.waitFor(500);
        actions.clickElement("weare.seeProfileButton");
    }

    public void assertAdminIsLoggedIn() {
        actions.assertElementPresent("weare.findYourProfessional");
    }

    public void disableProfile() {
        if (actions.isElementVisible("weare.enableButton")){
            actions.clickElement("weare.enableButton");
        }
        actions.waitForElementVisible("weare.disableButton");
        actions.waitFor(500);
        actions.clickElement("weare.disableButton");
    }

    public void enableProfile() {
        if (actions.isElementVisible("weare.enableButton")) {
            actions.clickElement("weare.enableButton");
        } else if (actions.isElementVisible("weare.disableButton")) {
            actions.waitForElementVisible("weare.disableButton");
            actions.clickElement("weare.disableButton");
            actions.waitForElementVisible("weare.enableButton");
            actions.clickElement("weare.enableButton");
        }
    }

    public void assertUserIsLocked() {
        if (actions.isElementVisible("weare.enableButton")) {
            actions.assertElementPresent("weare.enableButton");
        }
        /*actions.waitForElementPresent("weare.enableButton");
        actions.waitFor(500);*/
    }

    public void assertUserIsUnlocked() {
        actions.waitForElementPresent("weare.disableButton");
        actions.waitFor(500);
        actions.assertElementPresent("weare.disableButton");
    }
}
