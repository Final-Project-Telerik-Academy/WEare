package test.cases.weare;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class PersonalProfileTests extends BaseTest {

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
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        logout();
        updatePersonalProfilePage.backToHome();
        register();
        login();
        updatePersonalProfilePage.sendFriendRequest();
    }

    @Test
    public void approveRequest() {
        sendFriendRequest();
        logout();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.approveFriendRequest();
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
    public void personalReviewField(){
        register();
        login();
        updatePersonalProfilePage.personalReviewField();
        updatePersonalProfilePage.backToHome();
        updatePersonalProfilePage.assertPersonalReview();
    }
    @Test
    public void updateSkillDetails(){
        updateUserProfile();
        updatePersonalProfilePage.updateSkillDetails();
    }
    @Test
    public void updatePersonalImage(){
       // updateUserProfile();
        register();
        login();
        updatePersonalProfilePage.updatePersonalImage();
    }
}
