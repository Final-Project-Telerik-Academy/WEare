package pages.weare;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;

import java.security.SecureRandom;
import java.util.List;

public abstract class BasePage extends com.telerikacademy.testframework.pages.BasePage {

    protected static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    protected static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?/[]{}|\\~`";
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

    public String generateRandomFirstName() {
        return faker.name().firstName();
    }

    public String generateRandomLastName() {
        return faker.name().firstName();
    }

    public String generateRandomSpecialCharactersMessage(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(SPECIAL_CHARACTERS.length());
            char randomChar = SPECIAL_CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }


    public String generateRandomHyperlink() {
        String domain = faker.internet().domainName();
        String path = generateSlug();
       // String path = faker.lorem().slug();

        String url = "http://" + domain + "/" + path;

        return url;
    }

    private String generateSlug() {
        List<String> words = faker.lorem().words(2);
        return String.join("-", words);
    }
}
