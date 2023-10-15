package test.cases.weare;

import org.junit.jupiter.api.Test;
import pages.weare.UpdatePersonalProfilePage;

public class UpdatePersonalProfileTests extends BaseTest{

    @Test
    public void updateUserProfile() {
        register();
        login();
        UpdatePersonalProfilePage updatePersonalProfilePage = new UpdatePersonalProfilePage(actions.getDriver());
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
    }

    @Test
    public void sendFriendRequest() {
        register();
        login();
        UpdatePersonalProfilePage updatePersonalProfilePage = new UpdatePersonalProfilePage(actions.getDriver());
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        logout();
        updatePersonalProfilePage.backToHome();
        register();
        login();
        updatePersonalProfilePage.sendFriendRequest();
    }
}
