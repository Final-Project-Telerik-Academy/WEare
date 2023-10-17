package pages.weare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.telerikacademy.testframework.RandomGenerator.*;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;

public class PersonalProfilePage extends BasePage {

    LocalDate randomBirthday = generateRandomBirthday();
    private String firstName = generateRandomFirstName();
    private String lastName = generateRandomLastName();

    private String fullName = firstName + " " + lastName;
    private final String randomName2 = generateRandomString(2);
    private final String randomMessage = generateRandomMessage();
    private final String skillOne = generateRandomMessage();
    private final String skillTwo = generateRandomMessage();
    private final String skillTree = generateRandomMessage();
    private final String skillFour = generateRandomMessage();
    private final String skillFive = generateRandomMessage();


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
        actions.waitFor(2000);
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
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElementWithJavaScriptExecutor("weare.personalProfileButton");
        actions.waitForElementPresent("weare.openRequestButton");
        actions.clickElement("weare.openRequestButton");
        actions.waitFor(2000);
        actions.waitForElementPresent("weare.approveRequest");
        actions.clickElement("weare.approveRequest");
    }

    public void disconnectUser() {
        actions.waitFor(2000);
        actions.waitForElementPresent("weare.searchUserByName");
        actions.clickElementWithJavaScriptExecutor("weare.searchUserByName");
        actions.typeValueInField(fullName, "weare.searchUserByName");
        actions.waitForElementPresent("weare.searchUserButton");
        actions.clickElement("weare.searchUserButton");
        actions.waitForElementPresent("weare.seeUserProfileButton");
        actions.clickElement("weare.seeUserProfileButton");
        actions.waitForElementPresent("weare.disconnectButton");
        actions.clickElement("weare.disconnectButton");
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

    public void editFirstNameWithTwoCharacters() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        clearFirsNameField();
        actions.waitForElementVisible("weare.firstName");
        actions.typeValueInField(randomName2, "weare.firstName");
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");

    }

    public void editLastNameWithTwoCharacters() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        actions.waitForElementVisible("weare.lastName");
        clearLastNameField();
        actions.typeValueInField(randomName2, "weare.lastName");
        actions.waitFor(500);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
    }
    public void personalReviewField(){
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        fillPersonalDetails(firstName, lastName, randomBirthday);
        actions.waitForElementVisible("weare.personalReview");
        actions.typeValueInField(randomMessage, "weare.personalReview");
        actions.waitFor(500);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");

    }
    public void updateSkillDetails() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        fillSkillDetails(skillOne, skillTwo, skillTree,skillFour,skillFive);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
    }
    public void fillSkillDetails(String skillOne , String skillTwo, String skillTree,String skillFour,String skillFive) {
        fillFieldById("weare.skill1", skillOne);
        fillFieldById("weare.skill2",skillTwo);
        fillFieldById("weare.skill3", skillTree);
        fillFieldById("weare.skill4",skillFour);
        fillFieldById("weare.skill5",skillFive);
    }
    private void photoUser(String imagePath) {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        actions.waitFor(500);
        fillPersonalDetails(firstName, lastName, randomBirthday);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.imageUser");
        actions.typeValueInField(imagePath, "weare.imageUser");
        actions.waitFor(5000);
        actions.clickElement("weare.updateProfileImage");
    }
    public void updatePersonalImage(){
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo-2.jpg").toAbsolutePath().toString();
        photoUser(imagePath);

    }
    private void clearFirsNameField() {
        WebElement emailInput = driver.findElement(By.id("nameE"));
        emailInput.clear();
    }

    private void clearLastNameField() {
        WebElement emailInput = driver.findElement(By.id("lastnameE"));
        emailInput.clear();
    }

    public void assertFirstNameField() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertFirstNameField"), randomName2));
    }

    public void assertLastNameField() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertLastNameField"), randomName2));
    }
    public void assertPersonalReview(){
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertPersonalReview"),randomMessage));
    }

    public void assertFriendRequestSent() {
    actions.assertElementPresent("weare.friendRequestSentMessage");
    }

    public void assertFriendRequestApproved() {
        actions.assertElementPresent("weare.thereAreNoFriendRequestMessage");
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.hoverOverElement("weare.assertFriendsNumber");
        actions.assertElementPresent("weare.assertFriendsNumber");
    }
    public void assertUserDisconnected() {
        actions.assertElementPresent("weare.connectButton");
    }
}

