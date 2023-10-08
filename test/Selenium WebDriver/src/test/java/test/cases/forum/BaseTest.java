package test.cases.forum;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.forum.LoginPage;

public class BaseTest {
    UserActions actions = new UserActions();

    @BeforeEach
    public void setUp() {

        UserActions.loadBrowser("jira.homepage");
    }

    @AfterEach
    public void tearDown() {
        UserActions.quitDriver();
    }
    public void login() {

        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser("user");
    }
}

