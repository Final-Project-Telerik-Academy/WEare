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
    @Order(1)
    public void createPublicPost() {
        postPage.createPublicPost();
        postPage.assertPublicPostCreated();
    }

    @Test
    @Order(2)
    public void createPrivatePost() {
        postPage.createPrivatePost();
        postPage.assertPrivatePostCreated();
    }

    @Test
    @Order(3)
    public void createPostWithOneCharacter() {
        postPage.createPostWithOneCharacter();
        postPage.assertPostWithOneCharacterCreated();
    }

    @Test
    @Order(4)
    public void createPostWith999Characters() {
        postPage.createPostWith999Character();
        postPage.assertPostWith999CharactersCreated();
    }

    @Test
    @Order(5)
    public void createPostWith1000Characters() {
        postPage.createPostWith1000Character();
        postPage.assertPostWith1000CharactersCreated();
    }

    @Test
    @Order(6)
    public void createPostWithPngPhoto() {
        postPage.createPostWithPngFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    @Order(7)
    public void createPostWithJpgPhoto() {
        postPage.createPostWithJpgFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    @Order(8)
    public void createPostWithPanoramicPhoto() {
        postPage.createPostWithPanoramicPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    @Order(9)
    public void createPostWithTextAndPhoto() {
        postPage.createPostWithTextAndPhoto();
        postPage.assertPostCreatedWithTextAndPhoto();
    }

    @Test
    @Order(10)
    public void likePost() {
        postPage.createPublicPost();
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Test
    @Order(11)
    public void dislikePost() {
        postPage.createPublicPost();
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Test
    @Order(12)
    public void likeSpecificPost() {
        postPage.likeSpecificPost();
        postPage.assertSpecificPostIsLiked();
    }

    @Test
    @Order(13)
    public void dislikeSpecificPost() {
        postPage.dislikeSpecificPost();
        postPage.assertSpecificPostIsDisliked();
    }

    @Test
    @Order(14)
    public void editPostContent() {
        postPage.createPublicPost();
        postPage.editPostContent();
        postPage.assertPostContentIsEdited();
    }

    @Test
    @Order(15)
    public void deletePost() {
        postPage.createPublicPost();
        postPage.deletePost();
        postPage.assertPostDeleted();
    }
}
