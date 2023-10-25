package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.RandomGenerator.*;

public class AdminTests extends BaseTest {

    String usernameTest = generateRandomUsername(12);
    String emailTest = generateRandomEmail();
    String passwordTest = generateRandomPassword(8);
    String adminUsername = "admin" + generateRandomUsername(12);
    String adminEmail = generateRandomEmail();
    String adminPassword = generateRandomPassword(8);

    @Feature("Admin")
    @Story("Admin locks a single user successfully.")
    @Test
    public void AdminRegistered_When_LockSingleUser() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);
        updatePersonalProfilePage.disableUser();
        adminPage.assertUserIsLocked();
    }

    @Feature("Admin")
    @Story("Admin unlocks a single user successfully.")
    @Test
    public void AdminRegistered_When_UnlockSingleUser() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);
        updatePersonalProfilePage.enableUser();
        adminPage.assertUserIsUnlocked();
    }

    @Feature("Admin")
    @Story("Admin edits other user's personal profile post successfully.")
    @Test
    public void AdminRegistered_When_EditUserPersonalProfile() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);

        updatePersonalProfilePage.useractionLogic();
        adminPage.editOtherUserProfile();
        adminPage.assertUserProfileEmailIsUpdated();
    }

    @Feature("Admin")
    @Story("Admin edits other user's last created post successfully.")
    @Test
    public void AdminRegistered_When_EditLastCreatedUserPost() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);
        postPage.createPublicPost();
        adminPage.lastCreatedPost();
        adminPage.editUserPost();
        adminPage.assertUserPostIsEdited();
    }

    @Feature("Admin")
    @Story("Admin deletes other user's post successfully.")
    @Test
    public void AdminRegistered_When_DeleteLastCreatedUserPost() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);
        postPage.createPublicPost();
        adminPage.lastCreatedPost();
        adminPage.deleteLastCreatedPost();
        adminPage.assertPostIsDeletedSuccessfully();
    }

    @Feature("Admin")
    @Story("Admin edits other user's last created comment successfully.")
    @Test
    public void AdminRegistered_When_EditLastCreatedUserComment() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);
        postPage.createPublicPost();
        commentPage.createComment();
        adminPage.editOtherUserComment();
        adminPage.asserEditedUserCommentContent();
    }

    @Feature("Admin")
    @Story("Admin deletes other user's last created comment successfully.")
    @Test
    public void AdminRegistered_When_DeleteLastCreatedUserComment() {
        registrationPage.userRegistration(usernameTest, emailTest, passwordTest);
        loginPage.loginUser(usernameTest, passwordTest);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(adminUsername, adminEmail, adminPassword);
        loginPage.loginUser(adminUsername, adminPassword);
        postPage.createPublicPost();
        commentPage.createComment();
        adminPage.deleteOtherUserComment();
        adminPage.assertUserCommentIsDeleted();
    }
}
