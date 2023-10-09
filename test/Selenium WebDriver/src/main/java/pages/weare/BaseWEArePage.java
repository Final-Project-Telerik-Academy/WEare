package pages.weare;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseWEArePage extends BasePage {
    public BaseWEArePage(WebDriver driver, String urlKey) {
        super(driver, "weare.homepage");
    }
}
