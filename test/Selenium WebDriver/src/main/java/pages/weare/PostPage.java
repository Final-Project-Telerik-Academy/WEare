package pages.weare;

import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;

public class PostPage extends BaseWEArePage {
    public PostPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void createPublicPost(String message) {

        //click Add New Post
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");

        //Select public post
        actions.waitForElementPresent("weare.selectPublicOrPrivate");
        actions.hoverOverElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.selectPublicOrPrivate");

        actions.waitForElementPresent("weare.publicPostOption");
        actions.clickElement("weare.publicPostOption");

        //Fill in Post message
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(message, "weare.messageForm");

        actions.clickElement("weare.submitButton");
    }

    public void createPrivatePost(String message) {

        //click Add New Post
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");

        //Select private post
        actions.waitForElementPresent("weare.selectPublicOrPrivate");
        actions.hoverOverElement("weare.selectPublicOrPrivate");
        actions.clickElement("weare.selectPublicOrPrivate");

        actions.waitForElementPresent("weare.privatePostOption");
        actions.clickElement("weare.privatePostOption");

        //Fill in Post message
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(message, "weare.messageForm");

        actions.clickElement("weare.submitButton");
    }

    public void createPostWithPhoto() {
        // Get absolute image path
        String imagePath = Paths.get("src", "main", "resources", "images", "bug-photo.png").toAbsolutePath().toString();

        actions.waitForElementPresent("weare.addNewPostButton");
        actions.hoverOverElement("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");

        // File input
        actions.waitForElementPresent("weare.fileInput");
        actions.typeValueInField(imagePath, "weare.fileInput");

        actions.waitFor(5000);

        // Confirm the upload
        actions.clickElement("weare.submitButton");

    }

    public void likePost() {
        //click Like
        actions.waitForElementPresent("weare.likePostButton");
        actions.clickElement("weare.likePostButton");
    }

    public void dislikePost() {
        //click dislike
        actions.waitForElementPresent("weare.dislikePostButton");
        actions.clickElement("weare.dislikePostButton");
    }

    public void editPostContent(String editMessage) {
        //click Explore this post
        actions.waitForElementPresent("weare.exploreThisPost");
        actions.hoverOverElement("weare.exploreThisPost");
        actions.clickElement("weare.exploreThisPost");

        //click Edit post
        actions.waitForElementClickable("weare.editPostButton");
        actions.clickElement("weare.editPostButton");

        //Click and fulfill message form
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(1000);
        actions.typeValueInField(editMessage, "weare.messageForm");

        actions.waitForElementPresent("weare.submitButton");
        actions.clickElement("weare.submitButton");
    }

    public void deletePost() {
        //click Explore this post
        actions.waitForElementPresent("weare.exploreThisPost");
        actions.hoverOverElement("weare.exploreThisPost");
        actions.clickElement("weare.exploreThisPost");

        //click Delete post
        actions.waitForElementClickable("weare.deletePostbutton");
        actions.clickElement("weare.deletePostbutton");

        //Confirm delete
        actions.waitFor(1000);
        actions.waitForElementPresent("weare.confirmDelete");
        actions.clickElement("weare.confirmDelete");

        //Choose delete
        actions.waitForElementPresent("weare.deleteOption");
        actions.clickElement("weare.deleteOption");

        //Submit
        actions.waitForElementPresent("weare.submitButton");
        actions.clickElement("weare.submitButton");
    }

    public void assertPublicPostCreated() {
        //Welcome to our community header is present
        actions.waitForElementPresent("weare.assertPostPublic");
        //Please update your profile is present
        actions.waitForElementPresent("weare.assertTopic");
    }

    public void assertPrivatePostCreated() {
        //Welcome to our community header is present
        actions.waitForElementPresent("weare.assertPostPrivate");
        //Please update your profile is present
        actions.waitForElementPresent("weare.assertTopicToPrivatePost");
    }

    public void assertPostCreatedWithPhoto() {
        actions.waitForElementPresent("weare.assertPhotoExists");
    }

    public void assertPostIsLiked() {
        actions.waitForElementPresent("weare.assertPostLiked");
    }
    public void assertPostIsDisliked() {
        actions.waitForElementPresent("weare.assertPostDisliked");
    }

    public void assertPostContentIsEdited() {
        actions.waitForElementPresent("weare.assertEditedPost");
    }
    public void assertPostDeleted() {
        actions.waitForElementPresent("weare.postDeletedSuccessfully");
    }


}
