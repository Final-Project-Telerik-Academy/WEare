package pages.weare;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.telerikacademy.testframework.RandomGenerator.generateRandomMessage;
import static com.telerikacademy.testframework.RandomGenerator.generateRandomString;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;

public class PersonalProfilePage extends BasePage {

    private final String randomName2 = generateRandomString(2);
    private final String randomName3 = generateRandomString(3);
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
        fillPersonalDetails(firstName, lastName, birthday);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
    }

    public void changeProfessionalCategory() {
        actions.waitForElementPresent("weare.categoryId");
        actions.clickElement("weare.categoryId");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.optionActor");
        actions.waitFor(500);
        actions.clickElement("weare.optionActor");
        actions.waitForElementPresent("weare.updateMyProfileSecondButton");
        actions.clickElement("weare.updateMyProfileSecondButton");
    }

    public void sendFriendRequest() {
        useractionLogic();
        actions.waitForElementPresent("weare.connectButton");
        actions.clickElement("weare.connectButton");
    }

    public void useractionLogic() {
        actions.waitFor(2000);
        actions.waitForElementPresent("weare.searchUserByName");
        actions.clickElementWithJavaScriptExecutor("weare.searchUserByName");
        actions.typeValueInField(fullName, "weare.searchUserByName");
        actions.waitForElementPresent("weare.searchUserButton");
        actions.clickElement("weare.searchUserButton");
        actions.waitForElementPresent("weare.seeUserProfileButton");
        actions.clickElement("weare.seeUserProfileButton");
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
        useractionLogic();
        actions.waitForElementPresent("weare.disconnectButton");
        actions.clickElement("weare.disconnectButton");
    }

    public void enableUser() {
        useractionLogic();
        if (actions.isElementVisible("weare.enableButton")) {
            actions.clickElement("weare.enableButton");
        } else if (actions.isElementVisible("weare.disableButton")) {
            actions.waitForElementVisible("weare.disableButton");
            actions.clickElement("weare.disableButton");
            actions.waitForElementVisible("weare.enableButton");
            actions.clickElement("weare.enableButton");
        }
    }

    public void disableUser() {
        useractionLogic();
        if (actions.isElementVisible("weare.enableButton")) {
            actions.clickElement("weare.enableButton");
        }
        actions.waitForElementVisible("weare.disableButton");
        actions.waitFor(500);
        actions.clickElement("weare.disableButton");
    }


    public void backToHome() {
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
        personalProfileButton();
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        clearFirsNameField();
        actions.waitForElementVisible("weare.firstName");
        actions.typeValueInField(randomName2, "weare.firstName");
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
        personalProfileButton();

    }

    public void editLastNameWithTwoCharacters() {
        personalProfileButton();
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        actions.waitForElementVisible("weare.lastName");
        clearLastNameField();
        actions.typeValueInField(randomName2, "weare.lastName");
        actions.waitFor(500);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
        personalProfileButton();
    }

    public void personalReviewField() {
        personalProfileButton();
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        fillPersonalDetails(firstName, lastName, birthday);
        actions.waitForElementVisible("weare.personalReview");
        actions.typeValueInField(randomMessage, "weare.personalReview");
        actions.waitFor(500);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
        actions.waitFor(200);
        personalProfileButton();


    }

    public void updateSkillDetails() {
        personalProfileButton();
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        fillSkillDetails(skillOne, skillTwo, skillTree, skillFour, skillFive);
        actions.hoverOverElement("weare.updateSkillButton");
        actions.waitForElementPresent("weare.updateSkillButton");
        actions.clickElement("weare.updateSkillButton");

    }

    public void fillSkillDetails(String skillOne, String skillTwo, String skillTree, String skillFour, String skillFive)
    {
        fillFieldById("weare.skill1", skillOne);
        fillFieldById("weare.skill2", skillTwo);
        fillFieldById("weare.skill3", skillTree);
        fillFieldById("weare.skill4", skillFour);
        fillFieldById("weare.skill5", skillFive);
    }

    private void photoUser(String imagePath) {
        personalProfileButton();
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        actions.waitFor(500);
        fillPersonalDetails(firstName, lastName, birthday);
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.hoverOverElement("weare.imageUser");
        actions.waitForElementPresent("weare.imageUser");
        actions.typeValueInField(imagePath, "weare.imageUser");
        actions.waitFor(5000);
        actions.clickElement("weare.updateProfileImage");
    }

    public void updatePersonalImage() {
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo-2.jpg")
                .toAbsolutePath().toString();
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
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertNameField"), randomName2, lastName));
    }

    public void assertLastNameField() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertNameField"), firstName, randomName2));
    }

    public void assertPersonalReview() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.assertElementPresent(format(getUIMappingByKey("//p[text() = '%s']"), randomMessage));
    }

    public void assertUserProfileUpdated() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
        actions.hoverOverElement(format(getUIMappingByKey("weare.assertPersonalReview"), fullName));

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

    public void assertCategoryChanged() {
        actions.assertElementPresent("weare.assertCategoryChanged");
    }

    public void assertSkillField() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertSkill"), skillOne));
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertSkill"), skillTwo));
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertSkill"), skillTree));
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertSkill"), skillFour));
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertSkill"), skillFive));

    }

    public void assertUserCreatedUploadPhoto() {
        actions.assertElementPresent("weare.assertPhotoExists");
    }

    public void assertUpdatePhoto() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertUpdateImage")));
    }

    public void editFirstNameWithTreeCharacters() {
        personalProfileButton();
        actions.waitForElementPresent("weare.editProfileButton");
        actions.clickElement("weare.editProfileButton");
        clearFirsNameField();
        actions.waitForElementVisible("weare.firstName");
        actions.typeValueInField(randomName3, "weare.firstName");
        actions.hoverOverElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.updateMyProfileFirstButton");
        actions.clickElement("weare.updateMyProfileFirstButton");
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
    }

    public void assertFirstNameFieldWithTreeCharacters() {
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertNameField"), randomName3, lastName));
    }

    public void personalProfileButton() {
        actions.waitForElementPresent("weare.personalProfileButton");
        actions.clickElement("weare.personalProfileButton");
    }
}

