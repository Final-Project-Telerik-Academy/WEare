package pages.weare;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

import java.security.SecureRandom;

public abstract class BaseWEArePage extends BasePage {

    protected static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public BaseWEArePage(WebDriver driver, String urlKey) {
        super(driver, "weare.homepage");
    }

    protected static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}
