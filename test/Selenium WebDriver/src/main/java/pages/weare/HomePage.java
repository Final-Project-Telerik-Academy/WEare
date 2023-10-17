package pages.weare;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import static com.telerikacademy.testframework.RandomGenerator.*;
import static com.telerikacademy.testframework.RandomGenerator.generateRandomFirstName;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;

public class HomePage extends BasePage {

    LocalDate randomBirthday = generateRandomBirthday();
    private String firstName = generateRandomFirstName();
    private String lastName = generateRandomFirstName();
    private String fullName = firstName + " " + lastName;


    public HomePage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void updatePersonalRegistration() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        fillPersonalDetails(firstName, lastName, randomBirthday);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
    }

    public void searchUserByProfession() {
        actions.waitForElementClickable("weare.searchProfessionField");
        actions.typeValueInField("Actor", "weare.searchProfessionField");

        actions.waitForElementVisible("weare.searchButton");
        actions.clickElement("weare.searchButton");
    }

    public void assertResultSearch() {
        actions.assertElementPresent("weare.assertSearchResult");
    }

    public void searchUserByFullName() {
        actions.waitForElementVisible("weare.homeButton");
        actions.clickElement("weare.homeButton");
        actions.waitForElementClickable("weare.searchUserByName");
        actions.typeValueInField(fullName, "weare.searchUserByName");
        actions.waitForElementPresent("weare.searchUserButton");
        actions.clickElement("weare.searchUserButton");

    }

    public void searchUserByFirstName() {
        actions.waitForElementVisible("weare.homeButton");
        actions.clickElement("weare.homeButton");
        actions.waitForElementClickable("weare.searchUserByName");
        actions.typeValueInField(firstName, "weare.searchUserByName");
        actions.waitForElementPresent("weare.searchUserButton");
        actions.clickElement("weare.searchUserButton");
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

    public void assertUserResultSearch() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertFullnameSearch"), fullName));
    }

    public void assertUserResulFirstSearch() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertFirstNameSearchResult"), firstName,lastName));
    }

}

