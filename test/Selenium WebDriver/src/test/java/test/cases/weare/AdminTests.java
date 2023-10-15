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
