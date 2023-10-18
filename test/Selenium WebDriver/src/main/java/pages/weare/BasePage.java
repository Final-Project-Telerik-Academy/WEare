package pages.weare;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

import static com.telerikacademy.testframework.RandomGenerator.*;

public abstract class BasePage extends com.telerikacademy.testframework.pages.BasePage {

    LocalDate randomBirthday = LocalDate.parse("1990-12-01");
//    LocalDate randomBirthday = generateRandomBirthday(1930, 2000);
    protected String firstName = generateRandomFirstName();
    protected String lastName = generateRandomLastName();

    protected String fullName = firstName + " " + lastName;

    public BasePage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }
}
