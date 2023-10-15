package pages.weare;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver, "weare.homepage");
    }
    public void searchUserByProfession(){
        actions.waitForElementClickable("weare.searchProfessionField");
        actions.typeValueInField("Actor","weare.searchProfessionField");

        actions.waitForElementVisible("weare.searchButton");
        actions.clickElement("weare.searchButton");
    }
    public void assertResultSearch(){
        actions.assertElementPresent("weare.assertSearchResult");
    }
}
