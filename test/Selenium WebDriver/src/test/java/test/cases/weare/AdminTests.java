package test.cases.weare;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AdminTests extends BaseTest {
    @BeforeEach
    public void setupTest() {
        registerAsAdmin();
        loginAsAdmin();
    }

    @AfterEach
    public void performLogout() {
        logout();
    }

    @Test
    @Order(1)
    public void adminLockASingleUserTest() {
        adminPage.userPersonalProfile();
        adminPage.disableProfile();
        adminPage.assertUserIsLocked();
    }

    @Test
    @Order(2)
    public void adminUnlockASingleUserTest() {
        adminPage.userPersonalProfile();
        adminPage.enableProfile();
        adminPage.assertUserIsUnlocked();
    }
}
