package test.cases.weare;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PostTests extends BaseTest {

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
    public void createPublicPost() {
        postPage.createPublicPost();
        postPage.assertPublicPostCreated();
    }

    @Test
    public void createPrivatePost() {
        postPage.createPrivatePost();
        postPage.assertPrivatePostCreated();
    }

    @Test
    public void createPostWithOneCharacter() {
        postPage.createPostWithOneCharacter();
        postPage.assertPostWithOneCharacterCreated();
    }

    @Test
    public void createPostWith999Characters() {
        postPage.createPostWith999Character();
        postPage.assertPostWith999CharactersCreated();
    }

    @Test
    public void createPostWith1000Characters() {
        postPage.createPostWith1000Character();
        postPage.assertPostWith1000CharactersCreated();
    }

    @Test
    public void createPostWithPngPhoto() {
        postPage.createPostWithPngFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    public void createPostWithJpgPhoto() {
        postPage.createPostWithJpgFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    public void createPostWithPanoramicPhoto() {
        postPage.createPostWithPanoramicPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    public void createPostWithTextAndPhoto() {
        postPage.createPostWithTextAndPhoto();
        postPage.assertPostCreatedWithTextAndPhoto();
    }

    @Test
    public void likePost() {
        postPage.createPublicPost();
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Test
    public void dislikePost() {
        postPage.createPublicPost();
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Test
    public void likeSpecificPost() {
        postPage.likeSpecificPost();
        postPage.assertSpecificPostIsLiked();
    }

    @Test
    public void dislikeSpecificPost() {
        postPage.dislikeSpecificPost();
        postPage.assertSpecificPostIsDisliked();
    }

    @Test
    public void editPostContent() {
        postPage.createPublicPost();
        postPage.editPostContent();
        postPage.assertPostContentIsEdited();
    }

    @Test
    public void deletePost() {
        postPage.createPublicPost();
        postPage.deletePost();
        postPage.assertPostDeleted();
    }
}
