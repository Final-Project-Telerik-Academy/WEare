package pages.weare;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;

public class PostPage extends BaseWEArePage {
    public PostPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void createPublicPost(String message) {
        actions.waitForJavascript();
        actions.hoverOverElement("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.hoverOverElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.publicPostOption");
        actions.typeValueInField(message, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPrivatePost(String message) {
        actions.waitForJavascript();
        actions.hoverOverElement("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.hoverOverElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.privatePostOption");
        actions.typeValueInField(message, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void createPostWithPhoto() {
        actions.waitForJavascript();
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo.png").toAbsolutePath().toString();
        actions.hoverOverElement("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");
        actions.hoverOverElement("weare.fileInput");
        actions.typeValueInField(imagePath, "weare.fileInput");
        actions.clickElement("weare.submitButton");

    }

    public void likePost() {
        actions.waitForJavascript();
        actions.clickElement("weare.likePostButton");
    }

    public void dislikePost() {
        actions.waitForJavascript();
        actions.hoverOverElement("weare.dislikePostButton");
        actions.clickElement("weare.dislikePostButton");
    }

    public void editPostContent(String editMessage) {
        actions.waitForJavascript();
        actions.hoverOverElement("weare.exploreThisPost");
        actions.clickElement("weare.exploreThisPost");
        actions.hoverOverElement("weare.editPostButton");
        actions.clickElement("weare.editPostButton");
        actions.hoverOverElement("weare.messageForm");
        actions.typeValueInField(editMessage, "weare.messageForm");
        actions.clickElement("weare.submitButton");
    }

    public void deletePost() {
        actions.waitForJavascript();
        actions.hoverOverElement("weare.exploreThisPost");
        actions.clickElement("weare.exploreThisPost");
        actions.clickElement("weare.deletePostbutton");
        actions.hoverOverElement("weare.confirmDelete");
        actions.clickElement("weare.confirmDelete");
        actions.clickElement("weare.deleteOption");
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
        actions.waitForJavascript();
//        actions.waitForElementPresent("weare.assertPostLiked");
        actions.assertElementPresent("weare.assertPostLiked");
    }
    public void assertPostIsDisliked() {
        actions.assertElementPresent("weare.assertPostDisliked");
    }

    public void assertPostContentIsEdited() {
        actions.assertElementPresent("weare.assertEditedPost");
    }
    public void assertPostDeleted() {
        actions.assertElementPresent("weare.postDeletedSuccessfully");
    }


}
