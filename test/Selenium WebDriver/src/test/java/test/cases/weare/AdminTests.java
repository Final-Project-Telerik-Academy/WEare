package test.cases.weare;

import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.RandomGenerator.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AdminTests extends BaseTest {

    String usernameTest = generateRandomUsername(12);
    String emailTest = generateRandomEmail();
    String passwordTest = generateRandomPassword(8);

    String adminUsername = "admin" + generateRandomUsername(12);
    String adminEmail = generateRandomEmail();
    String adminPassword = generateRandomPassword(8);
/*    @BeforeEach
    public void setupTest() {
        registerAsAdmin();
        loginAsAdmin();
    }*/
/*
    @AfterEach
    public void performLogout() {
        logout();
    }*/

    @Test
    @Order(1)
    public void adminLockASingleUserTest() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        logout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);
        updatePersonalProfilePage.disableUser();
        adminPage.assertUserIsLocked();
    }

    @Test
    @Order(2)
    public void adminUnlockASingleUserTest() {
        adminPage.userPersonalProfile();
        adminPage.enableProfile();
        adminPage.assertUserIsUnlocked();
    }

    @Test
    @Order(3)
    public void editLastCreatedUserPostTest() {
        adminPage.lastCreatedPost();
        adminPage.editUserPost();
        adminPage.assertUserPostIsEdited();
    }

    @Test
    @Order(4)
    public void deleteLastCreatedUserPostTest() {
        adminPage.lastCreatedPost();
        adminPage.deleteLastCreatedPost();
        adminPage.assertPostIsDeletedSuccessfully();
    }

    @Test
    @Order(5)
    public void adminEditOtherUserPersonalProfileTest() {
        adminPage.userPersonalProfile();
        adminPage.editOtherUserProfile();
        adminPage.assertUserProfileEmailIsUpdated();
    }

    @Test
    @Order(6)
    public void adminEditOtherUserComment() {
        adminPage.lastCreatedPost();
        adminPage.editOtherUserComment();
        adminPage.asserEditedUserCommentContent();
    }

    @Test
    @Order(7)
    public void adminDeleteUserComment() {
        adminPage.lastCreatedPost();
        adminPage.deleteOtherUserComment();
        adminPage.assertUserCommentIsDeleted();
    }
}
