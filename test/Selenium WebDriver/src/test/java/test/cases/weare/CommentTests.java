package test.cases.weare;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CommentTests extends BaseTest {
    @BeforeEach
    public void setupTest() {
        register();
        login();
    }

    @AfterEach
    public void performLogout() {
        logout();
    }

    @Test
    @Order(1)
    public void createCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.assertCommentCreated();
    }

    @Test
    @Order(2)
    public void createCommentWithOneCharacterTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithOneCharacter();
        commentPage.assertCommentCreated();
    }

    @Test
    @Order(3)
    public void createCommentWith999CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithWith999Characters();
        commentPage.assertCommentCreated();
    }

    @Test
    @Order(4)
    public void createCommentWith1000CharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWith1000Characters();
        commentPage.assertCommentCreated();
    }

    @Test
    @Order(5)
    public void createCommentWithSpecialCharactersTest() {
        postPage.createPublicPost();
        commentPage.createCommentWithSpecialCharacters();
        commentPage.assertCommentWithSpecialCharsCreated();
    }

   /* @Test
    @Order(5)
    public void likeCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.likeSpecificComment();
        commentPage.assertCommentIsLiked();
    }

    @Test
    @Order(6)
    public void editLastCreatedCommentTest() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.editLastCreatedComment();
        commentPage.assertCommentContentIsEdited();
    }

    @Test
    @Order(7)
    public void deleteLastCreatedComment() {
        postPage.createPublicPost();
        commentPage.createComment();
        commentPage.deleteLastCreatedComment();
        commentPage.assertCommentIsDeleted();
    }*/
}
