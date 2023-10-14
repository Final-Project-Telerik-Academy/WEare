package pages.weare;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

import java.security.SecureRandom;

public abstract class BasePage extends com.telerikacademy.testframework.pages.BasePage {

    protected static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Faker faker;

    public BasePage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
        faker = new Faker();
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

    public String generateRandomMessage() {
        return faker.lorem().sentence();
    }
}
