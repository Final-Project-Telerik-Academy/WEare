package test.cases.weare;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    UserActions actions = new UserActions();

    @BeforeEach
    public void setUp() {

        UserActions.loadBrowser("weare.homepage");
    }

    @AfterEach
    public void tearDown() {
        UserActions.quitDriver();
    }
}

