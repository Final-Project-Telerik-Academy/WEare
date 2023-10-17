package test.cases.weare;

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

    @Test
    public void updateUserProfile() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
    }

    @Test
    public void changeProfessionalCategory() {
        updateUserProfile();
        updatePersonalProfilePage.changeProfessionalCategory();
    }

    @Test
    public void sendFriendRequest() {
        registrationPage.userRegistration(receiverUsername, receiverEmail, receiverPassword);
        loginPage.loginUser(receiverUsername, receiverPassword);
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        logout();
        updatePersonalProfilePage.backToHome();
        registrationPage.userRegistration(senderEmail, senderEmail, senderPassword);
        loginPage.loginUser(senderUsername, senderPassword);
        updatePersonalProfilePage.sendFriendRequest();
        updatePersonalProfilePage.assertFriendRequestSent();
    }

    @Test
    public void approveRequest() {
        sendFriendRequest();
        logout();
        updatePersonalProfilePage.backToHome();
        loginPage.loginUser(receiverUsername, receiverPassword);
        updatePersonalProfilePage.approveFriendRequest();
        updatePersonalProfilePage.assertFriendRequestApproved();
    }

    @Test
    public void disconnectUser() {
        approveRequest();
        loginPage.loginUser(senderUsername, senderPassword);
        updatePersonalProfilePage.disconnectUser();
        updatePersonalProfilePage.assertUserDisconnected();
    }


    @Test
    public void editFirstNameWithTwoCharacters() {
        updateUserProfile();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.editFirstNameWithTwoCharacters();
        updatePersonalProfilePage.assertFirstNameField();
    }

    @Test
    public void editLastNameWithTwoCharacters() {
        updateUserProfile();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.editLastNameWithTwoCharacters();
        updatePersonalProfilePage.assertLastNameField();
    }

    @Test
    public void personalReviewField() {
        register();
        login();
        updatePersonalProfilePage.personalReviewField();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.assertPersonalReview();
    }

    @Test
    public void updateSkillDetails() {
        updateUserProfile();
        updatePersonalProfilePage.updateSkillDetails();
    }

    @Test
    public void updatePersonalImage() {
        // updateUserProfile();
        register();
        login();
        updatePersonalProfilePage.updatePersonalImage();
    }
}
