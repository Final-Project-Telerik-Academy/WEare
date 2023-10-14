package pages.weare;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;

public class CommentPage extends BasePage {
    public CommentPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    private final String randomMessage = generateRandomMessage();
    private final String randomString1 = generateRandomString(1);
    private final String randomString999 = generateRandomString(999);
    private final String randomString1000 = generateRandomString(1000);
    private final String randomStringWithSpecialCharacters = generateRandomSpecialCharactersMessage(30);
    int commentsCounter;
    public void createComment() {
        //Create a post
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
        //Create a comment
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWithOneCharacter() {
        //Create a post
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
        actions.typeValueInField(randomString1, "weare.messageForm");
        actions.clickElement("weare.submitButton");
        //Create a comment
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWithWith999Characters() {
        //Create a post
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
        actions.typeValueInField(randomString999, "weare.messageForm");
        actions.clickElement("weare.submitButton");
        //Create a comment
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWith1000Characters() {
        //Create a post
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
        actions.typeValueInField(randomString1, "weare.messageForm");
        actions.clickElement("weare.submitButton");
        //Create a comment
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomString1000, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWithSpecialCharacters() {
        //Create a post
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
        actions.typeValueInField(randomString1, "weare.messageForm");
        actions.clickElement("weare.submitButton");
        //Create a comment
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomStringWithSpecialCharacters, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void likeSpecificComment() {
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.clickElement("weare.showCommentsBtn");

        if (actions.isElementVisible("weare.likeBtnFirstComment")) {
            actions.clickElement("weare.likeBtnFirstComment");
        } else if(actions.isElementVisible("weare.dislikeBtnFirstComment")) {
            actions.clickElement("weare.dislikeBtnFirstComment");
            actions.waitForElementClickable("weare.likeBtnFirstComment");
            actions.clickElement("weare.likeBtnFirstComment");
        }
    }

    public void editLastCreatedComment() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.clickElement("weare.showCommentsBtn");
        actions.waitForElementPresent("weare.editLastCreatedCommentButton");
        actions.hoverOverElement("weare.editLastCreatedCommentButton");
        actions.clickElement("weare.editLastCreatedCommentButton");
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.createCommentEditBtn");
        actions.clickElement("weare.createCommentEditBtn");
    }

    public void deleteLastCreatedComment() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(1000);
        actions.clickElement("weare.showCommentsBtn");
        actions.waitForElementPresent("weare.deleteLastCreatedCommentBtn");
        actions.hoverOverElement("weare.deleteLastCreatedCommentBtn");
        actions.clickElement("weare.deleteLastCreatedCommentBtn");
        actions.waitFor(1000);
        actions.waitForElementPresent("weare.selectDeleteCommentOption");
        actions.clickElement("weare.selectDeleteCommentOption");
        actions.waitFor(1000);
        actions.waitForElementPresent("weare.submitBtnForDelete");
        actions.clickElement("weare.submitBtnForDelete");
    }

    public void assertCommentCreated() {
        ++commentsCounter;
        String numberAsString = Integer.toString(commentsCounter);
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentsNumber"), numberAsString));
    }

    public void assertCommentWithSpecialCharsCreated() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.clickElement("weare.showCommentsBtn");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentsWithSpecCharsNumber"), randomStringWithSpecialCharacters));
    }

    public void assertCommentIsLiked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementPresent("weare.assertCommentIsLiked");
    }

    public void assertCommentContentIsEdited() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.clickElement("weare.showCommentsBtn");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentIsEdited"), randomMessage));
    }
    public void assertCommentIsDeleted() {
        actions.assertElementPresent("weare.assertCommentIsDeleted");
    }
}
