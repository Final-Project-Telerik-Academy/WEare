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
    @Order(1)
    public void createCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.assertNewCommentCreated();
        commentPage.asserCreatedCommentContent();
    }

    @Test
    @Order(2)
    public void createCommentWithOneCharacterTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithOneCharacter();
        commentPage.assertNewCommentCreated();
    }

    @Test
    @Order(3)
    public void createCommentWith999CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithWith999Characters();
        commentPage.assertNewCommentCreated();
    }

    @Test
    @Order(4)
    public void createCommentWith1000CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000Characters();
        commentPage.assertNewCommentCreated();
    }

    @Test
    @Order(5)
    public void createCommentWithSpecialCharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithSpecialCharacters();
        commentPage.assertCommentWithSpecialCharsCreated();
    }

    @Test
    @Order(6)
    public void createCommentWithHyperLinkTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithHyperLink();
        commentPage.assertCommentWithHyperlinkCreated();
    }

    @Test
    @Order(7)
    public void createCommentWith1000CharsSpecialCharsAndHyperLinkTest() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000CharsSpecialCharsAndHyperLinkTest();
        commentPage.assertCommentWithMessageHyperLinkAndSpecialChars();
    }

    @Test
    @Order(8)
    public void likeCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.likeSpecificComment();
        commentPage.assertCommentIsLiked();
    }

    @Test
    @Order(9)
    public void editLastCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.editLastCreatedComment();
        commentPage.assertCommentContentIsEdited();
    }

    @Test
    @Order(10)
    public void deleteLastCreatedComment() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.deleteLastCreatedComment();
        commentPage.assertCommentIsDeleted();
    }

    @Test
    @Order(11)
    public void createCommentWith1001Characters() {
        postPage.createPublicPost();
        commentPage.createCommentWith1001Characters();
        commentPage.assertCommentWith1001CharsNotCreated();
    }

    @Test
    @Order(12)
    public void anonymousUserCantCreateComment() {
        commentPage.anonymousUserTryToCreateComment();
        commentPage.assertMissingCommentTextAreaForAnonymous();
        commentPage.assertMissingCreateCommentButtonForAnonymous();
    }
}
