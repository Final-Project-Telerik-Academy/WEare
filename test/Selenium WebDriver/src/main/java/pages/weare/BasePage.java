package pages.weare;

import org.openqa.selenium.WebDriver;

public abstract class BasePage extends com.telerikacademy.testframework.pages.BasePage {

    public BasePage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }
}
