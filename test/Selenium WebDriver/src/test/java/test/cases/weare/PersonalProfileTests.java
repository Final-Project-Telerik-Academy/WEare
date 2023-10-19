package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.RandomGenerator.*;

public class PersonalProfileTests extends BaseTest {
    String senderUsername = generateRandomUsername(12);
    String senderEmail = generateRandomEmail();
    String senderPassword = generateRandomPassword(8);
    String receiverUsername = generateRandomUsername(2);
    String receiverEmail = generateRandomEmail();
    String receiverPassword = generateRandomPassword(8);


    @AfterEach
    public void performLogout() {
        logout();
    }

    @Feature("Personal profile")
    @Story("Edit personal profile - first name, last name and birthday successfully.")
    @Test
    public void updateUserProfile() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        updatePersonalProfilePage.assertUserProfileUpdated();
    }

    @Feature("Personal profile")
    @Story("Change professional category successfully.")
    @Test
    public void changeProfessionalCategory() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        updatePersonalProfilePage.changeProfessionalCategory();
        updatePersonalProfilePage.assertCategoryChanged();
    }

    @Feature("Personal profile")
    @Story("Send friend request to another user successfully.")
    @Test
    public void sendFriendRequest() {
        registrationPage.userRegistration(receiverUsername, receiverEmail, receiverPassword);
        loginPage.loginUser(receiverUsername, receiverPassword);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        logout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(senderUsername, senderEmail, senderPassword);
        loginPage.loginUser(senderUsername, senderPassword);
        updatePersonalProfilePage.sendFriendRequest();
        updatePersonalProfilePage.assertFriendRequestSent();
    }

    @Feature("Personal profile")
    @Story("Approve friend request from another user successfully.")
    @Test
    public void approveRequest() {
        sendFriendRequest();
        logout();
        updatePersonalProfilePage.backToHome();
        loginPage.loginUser(receiverUsername, receiverPassword);
        updatePersonalProfilePage.approveFriendRequest();
        updatePersonalProfilePage.assertFriendRequestApproved();
    }

    @Feature("Personal profile")
    @Story("Disconnect from another user successfully.")
    @Test
    public void disconnectUser() {
        approveRequest();
        logout();
        loginPage.loginUser(senderUsername, senderPassword);
        updatePersonalProfilePage.disconnectUser();
        updatePersonalProfilePage.assertUserDisconnected();
    }

    @Feature("Personal profile")
    @Story("Edit first name with two characters successfully.")
    @Test
    public void editFirstNameWithTwoCharacters() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.editFirstNameWithTwoCharacters();
        updatePersonalProfilePage.assertFirstNameField();
    }

    @Feature("Personal profile")
    @Story("Edit last name with two characters successfully.")
    @Test
    public void editLastNameWithTwoCharacters() {
        updateUserProfile();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.editLastNameWithTwoCharacters();
        updatePersonalProfilePage.assertLastNameField();
    }

    @Feature("Personal profile")
    @Story("Edit personal profile details and review it successfully.")
    @Test
    public void personalReviewField() {
        register();
        login();
        updatePersonalProfilePage.personalReviewField();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.assertPersonalReview();
    }

    @Feature("Personal profile")
    @Story("Update skill details successfully.")
    @Test
    public void updateSkillDetails() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        updatePersonalProfilePage.updateSkillDetails();
        updatePersonalProfilePage.assertSkillField();
    }

    @Feature("Personal profile")
    @Story("Update personal profile image successfully.")
    @Test
    public void updatePersonalImage() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalImage();
        updatePersonalProfilePage.assertUpdatePhoto();
    }

    @Feature("Personal profile")
    @Story("Edit first name with three characters successfully.")
    @Test
    public void updateFirstNameWithTreeCharacters() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        updatePersonalProfilePage.editFirstNameWithTreeCharacters();
        updatePersonalProfilePage.assertFirstNameFieldWithTreeCharacters();
    }
}
