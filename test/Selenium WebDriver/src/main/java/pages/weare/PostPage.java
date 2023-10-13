package pages.weare;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;

public class PostPage extends BaseWEArePage {
    public PostPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void createPublicPost(String message) {
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
        actions.typeValueInField(message, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPrivatePost(String message) {
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
        actions.typeValueInField(message, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPostWithPhoto() {
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo.png").toAbsolutePath().toString();
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.hoverOverElement("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.waitFor(500);
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

    public void editPostContent(String editMessage) {
        actions.waitForElementPresent("weare.exploreThisPost");
        actions.hoverOverElement("weare.exploreThisPost");
        actions.clickElement("weare.exploreThisPost");
        actions.waitForElementClickable("weare.editPostButton");
        actions.clickElement("weare.editPostButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(editMessage, "weare.messageForm");
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
        actions.assertElementPresent("weare.assertTopic");
    }

    public void assertPrivatePostCreated() {
        actions.assertElementPresent("weare.assertPostPrivate");
        actions.assertElementPresent("weare.assertTopicToPrivatePost");
    }

    public void assertPostCreatedWithPhoto() {
        actions.assertElementPresent("weare.assertPhotoExists");
    }

    public void assertPostIsLiked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        actions.waitForJavascript();
//        actions.waitForElementPresent("weare.assertPostLiked");
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

    public void assertPostContentIsEdited() {
        actions.assertElementPresent("weare.assertEditedPost");
    }
    public void assertPostDeleted() {
        actions.assertElementPresent("weare.postDeletedSuccessfully");
    }


}
