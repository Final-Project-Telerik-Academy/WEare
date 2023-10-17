package test.cases.weare;

import org.junit.jupiter.api.Test;

public class HomePageTests extends BaseTest {

    @Test
    public void searchByProfession() {
        homePage.searchUserByProfession();
        homePage.assertResultSearch();
    }
    @Test
    public void searchUserByFullname() {
        register();
        login();
        homePage.updatePersonalRegistration();
        logout();
        homePage.searchUserByFullname();
        homePage.assertUserResultSearch();
    }

    @Test
    public void searchUserByFirstname() {
        register();
        login();
        homePage.updatePersonalRegistration();
        logout();
        homePage.searchUserByFirstname();
        homePage.assertUserResultSearch();
    }
}