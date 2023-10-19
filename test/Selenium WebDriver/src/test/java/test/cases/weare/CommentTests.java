package test.cases.weare;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CommentTests extends BaseTest {
    @BeforeEach
    public void setupTest(TestInfo testInfo) {
        if (!"commentTextAreaNotVisibleForAnonymousUserTest".equals(testInfo.getTestMethod().get().getName())) {
            register();
            login();
        }
    }

    @AfterEach
    public void performLogout(TestInfo testInfo) {
        if (!"commentTextAreaNotVisibleForAnonymousUserTest".equals(testInfo.getTestMethod().get().getName())) {
            logout();
        }
    }

    @Test
    public void createCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.assertNewCommentCreated();
        commentPage.asserCreatedCommentContent();
    }

    @Test
    public void createCommentWithOneCharacterTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithOneCharacter();
        commentPage.assertNewCommentCreated();
    }

    @Test
    public void createCommentWith999CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithWith999Characters();
        commentPage.assertNewCommentCreated();
    }

    @Test
    public void createCommentWith1000CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000Characters();
        commentPage.assertNewCommentCreated();
    }

    @Test
    public void createCommentWithSpecialCharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithSpecialCharacters();
        commentPage.assertCommentWithSpecialCharsCreated();
    }

    @Test
    public void createCommentWithHyperLinkTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithHyperLink();
        commentPage.assertCommentWithHyperlinkCreated();
    }

    @Test
    public void createCommentWith1000CharsSpecialCharsAndHyperLinkTest() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000CharsSpecialCharsAndHyperLinkTest();
        commentPage.assertCommentWithMessageHyperLinkAndSpecialChars();
    }

    @Test
    public void likeCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.likeSpecificComment();
        commentPage.assertCommentIsLiked();
    }

    @Test
    public void dislikeCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.exploreThisPost();
        commentPage.createComment();
        commentPage.dislikeSpecificComment();
        commentPage.assertCommentIsDisiked();
    }

    @Test
    public void editLastCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.editLastCreatedComment();
        commentPage.assertCommentContentIsEdited();
    }

    @Test
    public void deleteLastCreatedComment() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.deleteLastCreatedComment();
        commentPage.assertCommentIsDeleted();
    }

    @Test
    public void createCommentWith1001Characters() {
        postPage.createPublicPost();
        commentPage.createCommentWith1001Characters();
        commentPage.assertCommentWith1001CharsNotCreated();
    }

    @Test
    public void anonymousUserCantCreateComment() {
        commentPage.anonymousUserTryToCreateComment();
        commentPage.assertMissingCommentTextAreaForAnonymous();
        commentPage.assertMissingCreateCommentButtonForAnonymous();
    }
}
