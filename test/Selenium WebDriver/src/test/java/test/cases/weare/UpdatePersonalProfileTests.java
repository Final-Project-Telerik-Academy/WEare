package test.cases.weare;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.weare.UpdatePersonalProfilePage;

public class UpdatePersonalProfileTests extends BaseTest{

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
        updatePersonalProfilePage.approveFriendRequest();
    }
}
