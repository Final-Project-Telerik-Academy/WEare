package test.cases.weare;

import org.junit.jupiter.api.Test;

public class HomePageTests extends BaseTest {

    @Test
    public void searchByProfession() {
        homePage.searchUserByProfession();
        homePage.assertResultSearch();
    }

}
