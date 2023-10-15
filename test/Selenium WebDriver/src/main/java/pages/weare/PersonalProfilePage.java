package pages.weare;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class PersonalProfilePage extends BasePage {

    LocalDate randomBirthday = generateRandomBirthday();
    private String firstName = generateRandomFirstName();
    private String lastName = generateRandomFirstName();

    private String fullName = firstName + " " + lastName;


    public PersonalProfilePage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void updatePersonalInfoAfterRegistration() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        fillPersonalDetails(firstName, lastName, randomBirthday);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
    }

    public void changeProfessionalCategory() {
        actions.waitForElementPresent("//select[@id='category.id']");
        actions.clickElement("//select[@id='category.id']");
        actions.waitForElementPresent("//option[@value='102']");
        actions.clickElement("//option[@value='102']");

        actions.waitForElementPresent("(//button[@type='submit' and contains(text(), 'Update')])[2]");
        actions.clickElement("(//button[@type='submit' and contains(text(), 'Update')])[2]");
    }

    public void sendFriendRequest() {
        actions.waitFor(5000);
        actions.waitForElementPresent("weare.searchUserByName");
        actions.clickElementWithJavaScriptExecutor("weare.searchUserByName");
        actions.typeValueInField(fullName, "weare.searchUserByName");
        actions.waitForElementPresent("weare.searchUserButton");
        actions.clickElement("weare.searchUserButton");
        actions.waitForElementPresent("weare.seeUserProfileButton");
        actions.clickElement("weare.seeUserProfileButton");
        actions.waitForElementPresent("weare.connectButton");
        actions.clickElement("weare.connectButton");
    }

    public void approveFriendRequest() {
        actions.waitFor(5000);

        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElementWithJavaScriptExecutor("weare.personalProfileButton");
        actions.waitForElementPresent("weare.openRequestButton");
        actions.clickElement("weare.openRequestButton");
        actions.waitForElementPresent("weare.approveRequest");
        actions.clickElement("weare.approveRequest");
    }
    public void backToHome() {
//        actions.waitFor(2000);
        actions.waitForElementPresent("weare.homeButton");
        actions.clickElement("weare.homeButton");

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

