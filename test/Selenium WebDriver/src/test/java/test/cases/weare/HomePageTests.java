package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

public class HomePageTests extends BaseTest {
    @Feature("HomePage")
    @Story("Search user by profession successfully.")
    @Test
    public void searchByProfession() {
        UserRegistered_When_ValidCredentialsEntered();
        UserLoggedIn_When_ValidDetailsEntered();
        updatePersonalProfilePage.updatePersonalInfoAfterRegistration();
        updatePersonalProfilePage.changeProfessionalCategory();
        UserLoggedOut_When_ClickLogout();
        updatePersonalProfilePage.backToHome();
        homePage.searchUserByProfession();
        homePage.assertResultSearch();
    }

    @Feature("HomePage")
    @Story("Search user by full name successfully.")
    @Test
    public void searchUserByFullName() {
        UserRegistered_When_ValidCredentialsEntered();
        UserLoggedIn_When_ValidDetailsEntered();
        homePage.updatePersonalRegistration();
        UserLoggedOut_When_ClickLogout();
        homePage.searchUserByFullName();
        homePage.assertUserResultSearch();
    }

    @Feature("HomePage")
    @Story("Search user by first name successfully.")
    @Test
    public void searchUserByFirstName() {
        UserRegistered_When_ValidCredentialsEntered();
        UserLoggedIn_When_ValidDetailsEntered();
        homePage.updatePersonalRegistration();
        UserLoggedOut_When_ClickLogout();
        homePage.searchUserByFirstName();
        homePage.assertUserResulFirstSearch();
    }
}