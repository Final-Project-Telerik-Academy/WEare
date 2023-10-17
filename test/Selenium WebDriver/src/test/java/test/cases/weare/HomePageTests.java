package test.cases.weare;

import org.junit.jupiter.api.Test;

public class HomePageTests extends BaseTest {

    @Test
    public void searchByProfession() {
        homePage.searchUserByProfession();
        homePage.assertResultSearch();
    }

    @Test
    public void searchUserByFullName() {
        register();
        login();
        homePage.updatePersonalRegistration();
        logout();
        homePage.searchUserByFullName();
        homePage.assertUserResultSearch();
    }

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