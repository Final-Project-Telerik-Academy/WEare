package pages.weare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.RandomGenerator.*;
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
    private final String randomStringWithHyperLink = generateRandomHyperlink();
    int commentsCounter;

    public void createComment() {
        exploreThisPost();
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.waitFor(500);
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWithOneCharacter() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWithWith999Characters() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWith1000Characters() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomString1000, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWithSpecialCharacters() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomStringWithSpecialCharacters, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWithHyperLink() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomStringWithHyperLink, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void createCommentWith1000CharsSpecialCharsAndHyperLinkTest() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomMessage, "weare.messageForm");
        actions.typeValueInField(randomStringWithSpecialCharacters, "weare.messageForm");
        actions.typeValueInField(randomStringWithHyperLink, "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    public void likeSpecificComment() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");

        if (actions.isElementVisible("weare.likeBtnFirstComment")) {
            actions.clickElement("weare.likeBtnFirstComment");
        } else if (actions.isElementVisible("weare.dislikeBtnFirstComment")) {
            actions.clickElement("weare.dislikeBtnFirstComment");
            actions.waitForElementClickable("weare.likeBtnFirstComment");
            actions.clickElement("weare.likeBtnFirstComment");
        }
    }

    public void dislikeSpecificComment() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");

        if (actions.isElementVisible("weare.dislikeBtnFirstComment")) {
            actions.clickElement("weare.dislikeBtnFirstComment");
        } else if (actions.isElementVisible("weare.likeBtnFirstComment")) {
            actions.clickElement("weare.likeBtnFirstComment");
            actions.waitForElementClickable("weare.dislikeBtnFirstComment");
            actions.clickElement("weare.dislikeBtnFirstComment");
        }
    }

    public void editLastCreatedComment() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.waitForElementPresent("weare.editLastCreatedCommentButton");
        actions.hoverOverElement("weare.editLastCreatedCommentButton");
        actions.clickElement("weare.editLastCreatedCommentButton");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
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

    public void createCommentWith1001Characters() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitForElementPresent("weare.messageForm");
        actions.waitFor(500);
        actions.typeValueInField(randomString1000 + "A", "weare.messageForm");
        actions.waitForElementPresent("weare.postCommentBtn");
        actions.clickElement("weare.postCommentBtn");
    }

    @Test
    public void anonymousUserTryToCreateComment() {
        actions.waitForElementPresent("weare.LatestPostsButton");
        actions.hoverOverElement("weare.LatestPostsButton");
        actions.clickElement("weare.LatestPostsButton");
        actions.waitFor(500);
        actions.waitForElementPresent("weare.firstCreatedPostExploreThisPostBtn");
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
    }

    public void assertNewCommentCreated() {
        ++commentsCounter;
        String numberAsString = Integer.toString(commentsCounter);
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentsNumber"), numberAsString));
    }

    public void asserCreatedCommentContent() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertComment"), randomMessage));
    }

    public void assertCommentWithSpecialCharsCreated() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentsWithSpecChars"), randomStringWithSpecialCharacters));
    }

    public void assertCommentWithHyperlinkCreated() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentsWithHyperLink"), randomStringWithHyperLink));
    }

    public void assertCommentIsLiked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementPresent("weare.assertCommentIsLiked");
    }

    public void assertCommentIsDisiked() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actions.assertElementPresent("weare.assertCommentIsDisiked");
    }

    public void assertCommentContentIsEdited() {
        actions.waitForElementPresent("weare.showCommentsBtn");
        actions.hoverOverElement("weare.showCommentsBtn");
        actions.waitFor(500);
        actions.clickElement("weare.showCommentsBtn");
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentIsEdited"), randomMessage));
    }

    public void assertCommentIsDeleted() {
        actions.assertElementPresent("weare.assertCommentIsDeleted");
    }

    public void assertCommentWithMessageHyperLinkAndSpecialChars() {
        String ranMessgage = randomMessage;
        String ranMesWithSpecChars = randomStringWithSpecialCharacters;
        String radMesWithHyperlink = randomStringWithHyperLink;
        String appendedStrging = ranMessgage + ranMesWithSpecChars + radMesWithHyperlink;
        actions.assertElementPresent(format(getUIMappingByKey("weare.assertCommentsWithEveryTypeChars"), appendedStrging));
    }

    public void assertCommentWith1001CharsNotCreated() {
        actions.assertElementPresent("weare.assertCommentWith1001CharsNotCreated");
    }

    public void assertMissingCommentTextAreaForAnonymous() {
        boolean result = actions.isElementNotVisible("weare.messageForm", 4);
        Assertions.assertTrue(result, "The comment text area is still visible for an anonymous user.");
    }

    public void assertMissingCreateCommentButtonForAnonymous() {
        boolean result = actions.isElementNotVisible("weare.postCommentBtn", 4);
        Assertions.assertTrue(result, "The post comment button is still visible for an anonymous user.");
    }

    public void exploreThisPost() {
        actions.waitForElementVisible("weare.firstCreatedPostExploreThisPostBtn");
        actions.waitFor(500);
        actions.clickElement("weare.firstCreatedPostExploreThisPostBtn");
    }
}
