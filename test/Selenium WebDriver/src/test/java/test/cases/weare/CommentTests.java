package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class CommentTests extends BaseTest {
    @BeforeEach
    public void setupTest(TestInfo testInfo) {
        if (!"commentTextAreaNotVisibleForAnonymousUserTest".equals(testInfo.getTestMethod().get().getName())) {
            UserRegistered_When_ValidCredentialsEntered();
            UserLoggedIn_When_ValidDetailsEntered();
        }
    }

    @AfterEach
    public void performLogout(TestInfo testInfo) {
        if (!"commentTextAreaNotVisibleForAnonymousUserTest".equals(testInfo.getTestMethod().get().getName())) {
            UserLoggedOut_When_ClickLogout();
        }
    }

    @Feature("Comment")
    @Story("Create a comment with valid input successfully.")
    @Test
    public void createCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.assertNewCommentCreated();
        commentPage.asserCreatedCommentContent();
    }

    @Feature("Comment")
    @Story("Create a comment with one character successfully.")
    @Test
    public void createCommentWithOneCharacterTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithOneCharacter();
        commentPage.assertNewCommentCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with 999 characters successfully.")
    @Test
    public void createCommentWith999CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithWith999Characters();
        commentPage.assertNewCommentCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with 1000 characters successfully.")
    @Test
    public void createCommentWith1000CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000Characters();
        commentPage.assertNewCommentCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with special characters successfully.")
    @Test
    public void createCommentWithSpecialCharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithSpecialCharacters();
        commentPage.assertCommentWithSpecialCharsCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with hyperlink successfully.")
    @Test
    public void createCommentWithHyperLinkTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithHyperLink();
        commentPage.assertCommentWithHyperlinkCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with 1000 characters, special characters and hyperlink successfully.")
    @Test
    public void createCommentWith1000CharsSpecialCharsAndHyperLinkTest() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000CharsSpecialCharsAndHyperLinkTest();
        commentPage.assertCommentWithMessageHyperLinkAndSpecialChars();
    }

    @Feature("Comment")
    @Story("Like a created comment successfully.")
    @Test
    public void likeCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.likeSpecificComment();
        commentPage.assertCommentIsLiked();
    }

    @Feature("Comment")
    @Story("Dislike a created comment successfully.")
    @Test
    public void dislikeCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.dislikeSpecificComment();
        commentPage.assertCommentIsDisiked();
    }

    @Feature("Comment")
    @Story("Edit last created comment successfully.")
    @Test
    public void editLastCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.editLastCreatedComment();
        commentPage.assertCommentContentIsEdited();
    }

    @Feature("Comment")
    @Story("Delete last created comment successfully.")
    @Test
    public void deleteLastCreatedComment() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.deleteLastCreatedComment();
        commentPage.assertCommentIsDeleted();
    }

    @Feature("Comment")
    @Story("Create a comment with 1001 characters unsuccessfully.")
    @Test
    public void createCommentWith1001Characters() {
        postPage.createPublicPost();
        commentPage.createCommentWith1001Characters();
        commentPage.assertCommentWith1001CharsNotCreated();
    }

    @Feature("Comment")
    @Story("commentTextAreaNotVisibleForAnonymousUserTest")
    @Test
    public void anonymousUserCantCreateComment() {
        commentPage.anonymousUserTryToCreateComment();
        commentPage.assertMissingCommentTextAreaForAnonymous();
        commentPage.assertMissingCreateCommentButtonForAnonymous();
    }
}
