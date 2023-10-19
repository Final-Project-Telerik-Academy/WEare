package pages.weare;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.telerikacademy.testframework.RandomGenerator.generateRandomEmail;
import static com.telerikacademy.testframework.RandomGenerator.generateRandomMessage;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;

public class AdminPage extends BasePage {
    Faker faker = new Faker();
    String updatedEmail;
    String editedContent;
    private final String randomMessage = generateRandomMessage();

    public AdminPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void loginAdmin(String username, String password) {
        actions.waitForElementVisible("weare.signInButton");
        actions.clickElement("weare.signInButton");
        actions.waitForElementVisible("weare.loginUsername");
        actions.typeValueInField(username, "weare.loginUsername");
        actions.waitForElementVisible("weare.loginPassword");
        actions.typeValueInField(password, "weare.loginPassword");
        actions.clickElement("weare.LoginSubmitButton");
    }

    public void userPersonalProfile() {
        actions.waitForElementPresent("weare.searchUsersField");
        actions.waitFor(500);
        actions.typeValueInField(firstName + " " + lastName, "weare.searchUsersField");
        actions.waitFor(500);
        actions.clickElement("weare.searchButton");
        actions.waitForElementPresent("weare.seeProfileButton");
        actions.hoverOverElement("weare.seeProfileButton");
        actions.waitFor(500);
        actions.clickElement("weare.seeProfileButton");
    }

    public void lastCreatedPost() {
        actions.waitForElementPresent("weare.LatestPostsButton");
        actions.waitFor(500);
        actions.clickElement("weare.LatestPostsButton");
        actions.waitForElementPresent("weare.lastCreatedPostExploreThisPostBtn");
        actions.waitFor(500);
        actions.clickElement("weare.lastCreatedPostExploreThisPostBtn");
    }

    public void deleteLastCreatedPost() {
        actions.waitForElementPresent("weare.deletePostBtn");
        actions.waitFor(500);
        actions.clickElement("weare.deletePostBtn");
        actions.waitForElementPresent("weare.selectDeletePostOption");
        actions.waitFor(500);
        actions.clickElement("weare.selectDeletePostOption");
        actions.waitForElementPresent("weare.submitBtnForPostDelete");
        actions.waitFor(500);
        actions.clickElement("weare.submitBtnForPostDelete");
    }

    public void deletePostTwo() {
        actions.waitForElementClickable("weare.exploreThisPost");
        actions.waitFor(500);
        actions.clickElement("weare.exploreThisPost");
        actions.waitFor(1000);
        actions.waitForElementClickable("weare.deletePostbutton");
        actions.clickElement("weare.deletePostbutton");
        actions.waitFor(1000);
        actions.waitForElementPresent("weare.confirmDelete");
        actions.clickElement("weare.confirmDelete");
        actions.waitForElementPresent("weare.deleteOption");
        actions.clickElement("weare.deleteOption");
        actions.waitForElementPresent("weare.submitButton");
        actions.clickElement("weare.submitButton");
    }

    public void editOtherUserProfile() {
        actions.waitForElementPresent("weare.editProfileHyperlink");
        actions.hoverOverElement("weare.editProfileHyperlink");
        actions.clickElement("weare.editProfileHyperlink");
        actions.waitForElementPresent("weare.emailProfileField");
        actions.waitFor(500);
        clearEmailField();
        updatedEmail = generateRandomEmail();
        actions.typeValueInField(updatedEmail, "weare.emailProfileField");
        changeBirthdayDate();
        actions.waitForElementPresent("weare.updateProfileButton");
        actions.waitFor(500);
        actions.clickElement("weare.updateProfileButton");
    }

    public void editUserPost() {
        actions.waitForElementPresent("weare.editPostButton");
        actions.waitFor(500);
        actions.clickElement("weare.editPostButton");
        actions.waitForElementPresent("weare.publicPostOption");
        actions.waitFor(500);
        actions.clickElement("weare.publicPostOption");
        actions.waitForElementPresent("weare.messageForm");
        editedContent = generateRandomMessage();
        actions.typeValueInField(editedContent, "weare.messageForm");
        actions.waitForElementPresent("weare.savePostButton");
        actions.waitFor(500);
        actions.clickElement("weare.savePostButton");
    }

    public void editOtherUserComment() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.waitForElementPresent("weare.editLastCreatedCommentButton");
        //actions.hoverOverElement("weare.editLastCreatedCommentButton");
        actions.waitFor(500);
        actions.clickElement("weare.editLastCreatedCommentButton");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.createCommentEditBtn");
        actions.clickElement("weare.createCommentEditBtn");
    }

    public void deleteOtherUserComment() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.waitForElementPresent("weare.deleteLastCreatedCommentBtn");
        actions.waitFor(500);
        actions.hoverOverElement("weare.deleteLastCreatedCommentBtn");
        actions.clickElement("weare.deleteLastCreatedCommentBtn");
        actions.waitFor(1000);
        actions.waitForElementPresent("weare.confirmDelete");
        actions.clickElement("weare.confirmDelete");
        actions.waitFor(1000);
        actions.waitForElementPresent("weare.selectDeleteCommentOption");
        actions.clickElement("weare.selectDeleteCommentOption");
        actions.waitForElementPresent("weare.submitBtnForDelete");
        actions.waitFor(500);
        actions.clickElement("weare.submitBtnForDelete");
    }

    public void assertAdminIsLoggedIn() {
        actions.assertElementPresent("weare.findYourProfessional");
    }

    public void disableProfile() {
        if (actions.isElementVisible("weare.enableButton")) {
            actions.clickElement("weare.enableButton");
        }
        actions.waitForElementVisible("weare.disableButton");
        actions.waitFor(500);
        actions.clickElement("weare.disableButton");
    }

    public void enableProfile() {
        if (actions.isElementVisible("weare.enableButton")) {
            actions.clickElement("weare.enableButton");
        } else if (actions.isElementVisible("weare.disableButton")) {
            actions.waitForElementVisible("weare.disableButton");
            actions.clickElement("weare.disableButton");
            actions.waitForElementVisible("weare.enableButton");
            actions.clickElement("weare.enableButton");
        }
    }

    public void assertUserIsLocked() {
        if (actions.isElementVisible("weare.enableButton")) {
            actions.assertElementPresent("weare.enableButton");
        }
    }

    public void assertUserIsUnlocked() {
        actions.waitForElementPresent("weare.disableButton");
        actions.waitFor(500);
        actions.assertElementPresent("weare.disableButton");
    }

    public void assertPostIsDeletedSuccessfully() {
        actions.assertElementPresent("weare.assertPostIsDeleted");
    }

    public void assertUserProfileEmailIsUpdated() {
        actions.assertElementPresent(String.format(getUIMappingByKey("weare.assertUpdatedEmail"), updatedEmail));
    }

    public void assertUserPostIsEdited() {
        actions.assertElementPresent(String.format(getUIMappingByKey("weare.assertPostIsEdited"), editedContent));
    }

    public void asserEditedUserCommentContent() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertComment"), randomMessage));
    }

    public void assertUserCommentIsDeleted() {
        actions.assertElementPresent("weare.assertCommentIsDeleted");
    }


    private void changeBirthdayDate() {
        WebElement dateInput = driver.findElement(By.id("birthDayE"));
        dateInput.clear();
        dateInput.sendKeys("1990-11-03");
    }

    private void clearEmailField() {
        WebElement emailInput = driver.findElement(By.id("emailE"));
        emailInput.clear();
    }
}
