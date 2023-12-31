package pages.weare;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;

import static com.telerikacademy.testframework.RandomGenerator.generateRandomMessage;
import static com.telerikacademy.testframework.RandomGenerator.generateRandomString;

public class PostPage extends BasePage {
    public PostPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    private final String randomMessage = generateRandomMessage();
    private final String randomString1 = generateRandomString(1);
    private final String randomString999 = generateRandomString(999);
    private final String randomString1000 = generateRandomString(1000);

    public void createPublicPost() {
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.selectPublicOrPrivate");
        actions.hoverOverElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.selectPublicOrPrivate");
        actions.waitForElementPresent("weare.publicPostOption");
        actions.clickElement("weare.publicPostOption");
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPrivatePost() {
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.selectPublicOrPrivate");
        actions.hoverOverElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.selectPublicOrPrivate");
        actions.waitForElementPresent("weare.privatePostOption");
        actions.clickElement("weare.privatePostOption");
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPostWithOneCharacter() {
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomString1, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPostWith999Character() {
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomString999, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPostWith1000Character() {
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomString1000, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPostWithPngFormatPhoto() {
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo.png").toAbsolutePath().toString();
        photoLogic(imagePath);
    }

    private void photoLogic(String imagePath) {
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.hoverOverElement("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.fileInput");
        actions.typeValueInField(imagePath, "weare.fileInput");
        actions.waitFor(5000);
        actions.clickElement("weare.submitButton");
    }

    public void createPostWithJpgFormatPhoto() {
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo-2.jpg").toAbsolutePath().toString();
        photoLogic(imagePath);
    }

    public void createPostWithPanoramicPhoto() {
        String imagePath = Paths.get("src", "main", "resources", "images", "panoramic.jpg").toAbsolutePath().toString();
        photoLogic(imagePath);
    }

    public void createPostWithTextAndPhoto() {
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomMessage, "weare.messageForm");
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo-2.jpg").toAbsolutePath().toString();
        actions.waitForElementPresent("weare.fileInput");
        actions.typeValueInField(imagePath, "weare.fileInput");
        actions.waitFor(5000);
        actions.clickElement("weare.submitButton");
    }

    public void likePost() {
        actions.clickElement("weare.likePostButton");
    }


    public void dislikePost() {
        if (actions.isElementVisible("weare.likePostButton")) {
            actions.clickElement("weare.likePostButton");
        }
        actions.waitForElementVisible("weare.dislikePostButton");
        actions.clickElement("weare.dislikePostButton");
    }

    public void editPostContent() {
        actions.waitForElementPresent("weare.exploreThisPost");
        actions.hoverOverElement("weare.exploreThisPost");
        actions.clickElement("weare.exploreThisPost");
        actions.waitForElementClickable("weare.editPostButton");
        actions.clickElement("weare.editPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.submitButton");
        actions.clickElement("weare.submitButton");
    }

    public void deletePost() {
        actions.waitForElementPresent("weare.exploreThisPost");
        actions.hoverOverElement("weare.exploreThisPost");
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

    public void assertPublicPostCreated() {
        actions.assertElementPresent("weare.assertPostPublic");
        actions.assertElementPresent("//p[contains(text(), '" + randomMessage + "')]");
    }

    public void assertPrivatePostCreated() {
        actions.assertElementPresent("weare.assertPostPrivate");
        actions.assertElementPresent("//p[contains(text(), '" + randomMessage + "')]");
    }


    public void assertPostWithOneCharacterCreated() {
        actions.assertElementPresent("//p[contains(text(), '" + randomString1 + "')]");
    }

    public void assertPostWith999CharactersCreated() {
        actions.assertElementPresent("//p[contains(text(), '" + randomString999 + "')]");
    }

    public void assertPostWith1000CharactersCreated() {
        actions.assertElementPresent("//p[contains(text(), '" + randomString1000 + "')]");
    }

    public void assertPostCreatedWithPhoto() {
        actions.assertElementPresent("weare.assertPhotoExists");
    }

    public void assertPostCreatedWithTextAndPhoto() {
        actions.assertElementPresent("//p[contains(text(), '" + randomMessage + "')]");
        actions.assertElementPresent("weare.assertPhotoExists");
    }

    public void assertPostIsLiked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementPresent("weare.assertPostLiked");
    }

    public void assertPostIsDisliked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementPresent("weare.assertPostDisliked");
    }

    public void assertSpecificPostIsLiked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementPresent("weare.dislikeSecondPost");
    }

    public void assertSpecificPostIsDisliked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementPresent("weare.likeFirstPost");
    }

    public void assertPostContentIsEdited() {
        actions.assertElementPresent("//p[contains(text(), '" + randomMessage + "')]");
    }

    public void assertPostDeleted() {
        actions.assertElementPresent("weare.postDeletedSuccessfully");
    }
}
