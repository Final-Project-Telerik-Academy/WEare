package test.cases.weare;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.weare.PostPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PostTests extends BaseTest {
    @Test
    @Order(1)
    public void createPublicPost() {
        login(username, password);
        postPage.createPublicPost(publicPostMessage);
        postPage.assertPublicPostCreated();
    }

    @Test
    @Order(2)
    public void createPrivatePost() {
        login(username, password);
        postPage.createPrivatePost(privatePostMessage);
        postPage.assertPrivatePostCreated();
    }

    @Test
    @Order(3)
    public void createPostWithOneCharacter() {
        login(username, password);
        postPage.createPostWithValidCharacters(postMessageOneCharacters);
        postPage.assertPostWithOneCharacterCreated();
    }

    @Test
    @Order(4)
    public void createPostWith999Characters() {
        login(username, password);
        postPage.createPostWithValidCharacters(postMessage999Characters);
        postPage.assertPostWith999CharactersCreated();
    }

    @Test
    @Order(5)
    public void createPostWithPngPhoto() {
        login(username, password);
        postPage.createPostWithPngFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    @Order(6)
    public void createPostWithJpgPhoto() {
        login(username, password);
        postPage.createPostWithJpgFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    @Order(7)
    public void createPostWithPanoramicPhoto() {
        login(username, password);
        postPage.createPostWithPanoramicPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    @Order(8)
    public void createPostWithTextAndPhoto() {
        login(username, password);
        postPage.createPostWithTextAndPhoto(publicPostMessage);
        postPage.assertPostCreatedWithTextAndPhoto();
    }
    @Test
    @Order(9)
    public void likePost() {
        login(username, password);
        postPage.createPublicPost(publicPostMessage);
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Test
    @Order(10)
    public void dislikePost() {
        login(username, password);
        postPage.createPublicPost(publicPostMessage);
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Test
    @Order(11)
    public void editPostContent() {
        login(username, password);
        postPage.createPublicPost(publicPostMessage);
        postPage.editPostContent(editedPostMessage);
        postPage.assertPostContentIsEdited();
    }
    @Test
    @Order(12)
    public void deletePost() {
        login(username, password);
        postPage.createPublicPost(publicPostMessage);
        postPage.deletePost();
        postPage.assertPostDeleted();
    }
}
