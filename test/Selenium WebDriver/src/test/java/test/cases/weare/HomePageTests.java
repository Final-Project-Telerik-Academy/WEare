package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

public class HomePageTests extends BaseTest {
    @Feature("HomePage")
    @Story("Search user by profession successfully.")
    @Test
    public void searchByProfession() {
        register();
        login();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        updatePersonalProfilePage.changeProfessionalCategory();
        logout();
        updatePersonalProfilePage.backToHome();
        homePage.searchUserByProfession();
        homePage.assertResultSearch();
    }

    @Feature("HomePage")
    @Story("Search user by full name successfully.")
    @Test
    public void searchUserByFullName() {
        register();
        login();
        homePage.updatePersonalRegistration();
        logout();
        homePage.searchUserByFullName();
        homePage.assertUserResultSearch();
    }

    @Feature("HomePage")
    @Story("Search user by first name successfully.")
    @Test
    public void searchUserByFirstName() {
        register();
        login();
        homePage.updatePersonalRegistration();
        logout();
        homePage.searchUserByFirstName();
        homePage.assertUserResulFirstSearch();
    }
}