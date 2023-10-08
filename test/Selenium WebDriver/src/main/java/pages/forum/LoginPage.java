package pages.forum;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends BaseJiraPage {
    public LoginPage(WebDriver driver) {
        super(driver, "jira.homepage");
    }

    public void loginUser(String userKey) {
        String username = getConfigPropertyByKey("jira.users." + userKey + ".username");
        String password = getConfigPropertyByKey("jira.users." + userKey + ".password");

        navigateToPage();
        assertPageNavigated();

        actions.waitForElementVisible("jira.signIn");
        actions.clickElement("jira.signIn");

        actions.waitForElementVisible("jira.usernameField");
        actions.typeValueInField(username, "jira.usernameField");

        actions.clickElement("jira.loginSubmitButton");

        actions.waitForElementVisible("jira.passwordField");
        actions.typeValueInField(password, "jira.passwordField");

        actions.clickElement("jira.loginSubmitButton");

    }
}
