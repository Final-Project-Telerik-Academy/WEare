package pages.weare;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UpdatePersonalProfilePage extends BasePage {

    LocalDate randomBirthday = generateRandomBirthday();
    private String firstName = generateRandomFirstName();
    private String lastName = generateRandomFirstName();

    private String fullName = firstName + " " + lastName;


    public UpdatePersonalProfilePage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void updatePersonalInfoAfterRegistration() {
        actions.waitForElementPresent("//a[@class ='nav-link' and contains(@href, '/profile')]");
        actions.clickElement("//a[@class ='nav-link' and contains(@href, '/profile')]");
        actions.waitForElementPresent("//a[contains(@href, 'profile/editor') and contains(@class, 'nav-link') and contains(text(), 'edit')]");
        actions.clickElement("//a[contains(@href, 'profile/editor') and contains(@class, 'nav-link') and contains(text(), 'edit')]");

        fillPersonalDetails(firstName, lastName, randomBirthday);
    }

    public void fillPersonalDetails(String firstName, String lastName, LocalDate birthday) {
        String formattedBirthday = birthday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        fillFieldById("weare.firstName", firstName);
        fillFieldById("weare.lastName", lastName);
        fillFieldById("weare.birthday", formattedBirthday);
    }

    private void fillFieldById(String locator, String value) {
        actions.waitForElementPresent(locator);
        actions.typeValueInField(value, locator);
    }

    public static LocalDate generateRandomBirthday() {
        Random random = new Random();

        int year = 1900 + random.nextInt(124);
        int monthValue = 1 + random.nextInt(12);
        Month month = Month.of(monthValue);
        int day = 1 + random.nextInt(28);

        return LocalDate.of(year, month, day);
    }
}

