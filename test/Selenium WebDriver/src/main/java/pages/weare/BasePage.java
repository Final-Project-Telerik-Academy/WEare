package pages.weare;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

import static com.telerikacademy.testframework.RandomGenerator.generateRandomFirstName;
import static com.telerikacademy.testframework.RandomGenerator.generateRandomLastName;

public abstract class BasePage extends com.telerikacademy.testframework.pages.BasePage {
    LocalDate birthday = LocalDate.parse("1990-12-01");
    protected String firstName = generateRandomFirstName();
    protected String lastName = generateRandomLastName();

    protected String fullName = firstName + " " + lastName;

    public BasePage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }
}
