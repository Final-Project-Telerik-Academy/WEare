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
        if (!"anonymousUserCantCreateComment".equals(testInfo.getTestMethod().get().getName())) {
            UserRegistered_When_ValidCredentialsEntered();
            UserLoggedIn_When_ValidDetailsEntered();
        }
    }

    @AfterEach
    public void performLogout(TestInfo testInfo) {
        if (!"anonymousUserCantCreateComment".equals(testInfo.getTestMethod().get().getName())) {
            UserLoggedOut_When_ClickLogout();
        }
    }

    @Feature("Comment")
    @Story("Create a comment with valid input successfully.")
    @Test
    public void CommentCreated_When_ClickSubmitButton() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.assertNewCommentCreated();
        commentPage.asserCreatedCommentContent();
    }

    @Feature("Comment")
    @Story("Create a comment with one character successfully.")
    @Test
    public void CommentCreated_When_OneCharacterEntered() {
        postPage.createPublicPost();
        commentPage.createCommentWithOneCharacter();
        commentPage.assertNewCommentCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with 999 characters successfully.")
    @Test
    public void CommentCreated_When_999CharacterEntered() {
        postPage.createPublicPost();
        commentPage.createCommentWithWith999Characters();
        commentPage.assertNewCommentCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with 1000 characters successfully.")
    @Test
    public void CommentCreated_When_1000CharacterEntered() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000Characters();
        commentPage.assertNewCommentCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with special characters successfully.")
    @Test
    public void CommentCreated_When_SpecialCharactersEntered() {
        postPage.createPublicPost();
        commentPage.createCommentWithSpecialCharacters();
        commentPage.assertCommentWithSpecialCharsCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with hyperlink successfully.")
    @Test
    public void CommentCreated_When_HyperLinkEntered() {
        postPage.createPublicPost();
        commentPage.createCommentWithHyperLink();
        commentPage.assertCommentWithHyperlinkCreated();
    }

    @Feature("Comment")
    @Story("Create a comment with 1000 characters, special characters and hyperlink successfully.")
    @Test
    public void CommentCreated_When_Combine1000CharactersSpecialCharactersAndHyperlink() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000CharsSpecialCharsAndHyperLinkTest();
        commentPage.assertCommentWithMessageHyperLinkAndSpecialChars();
    }

    @Feature("Comment")
    @Story("Like a created comment successfully.")
    @Test
    public void UserLogged_When_LikeCreatedComment() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.likeSpecificComment();
        commentPage.assertCommentIsLiked();
    }

    @Feature("Comment")
    @Story("Dislike a created comment successfully.")
    @Test
    public void UserLogged_When_DislikeCreatedComment() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.dislikeSpecificComment();
        commentPage.assertCommentIsDisiked();
    }

    @Feature("Comment")
    @Story("Edit last created comment successfully.")
    @Test
    public void UserLogged_When_EditLastCreatedComment() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.editLastCreatedComment();
        commentPage.assertCommentContentIsEdited();
    }

    @Feature("Comment")
    @Story("Delete last created comment successfully.")
    @Test
    public void UserLogged_When_deleteLastCreatedComment() {
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
    @Story("Anonymous user can't create comment.")
    @Test
    public void anonymousUserCantCreateComment() {
        commentPage.anonymousUserTryToCreateComment();
        commentPage.assertMissingCommentTextAreaForAnonymous();
        commentPage.assertMissingCreateCommentButtonForAnonymous();
    }
}
