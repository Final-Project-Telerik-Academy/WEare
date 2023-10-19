package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostTests extends BaseTest {

    @BeforeEach
    public void setupTest() {
        register();
        login();
    }

    @Feature("Post")
    @Story("Create a public post with valid input successfully.")
    @AfterEach
    public void performLogout() {
        logout();
    }

    @Feature("Post")
    @Story("Create a public post with valid input successfully.")
    @Test
    public void createPublicPost() {
        postPage.createPublicPost();
        postPage.assertPublicPostCreated();
    }

    @Feature("Post")
    @Story("Create a private post with valid input successfully.")
    @Test
    public void createPrivatePost() {
        postPage.createPrivatePost();
        postPage.assertPrivatePostCreated();
    }

    @Feature("Post")
    @Story("Create a post with one character successfully.")

    @Test
    public void createPostWithOneCharacter() {
        postPage.createPostWithOneCharacter();
        postPage.assertPostWithOneCharacterCreated();
    }

    @Feature("Post")
    @Story("Create a post with 999 characters successfully.")

    @Test
    public void createPostWith999Characters() {
        postPage.createPostWith999Character();
        postPage.assertPostWith999CharactersCreated();
    }

    @Feature("Post")
    @Story("Create a post with 1000 characters successfully.")
    @Test
    public void createPostWith1000Characters() {
        postPage.createPostWith1000Character();
        postPage.assertPostWith1000CharactersCreated();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a png format photo successfully.")
    @Test
    public void createPostWithPngPhoto() {
        postPage.createPostWithPngFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a jpg format photo successfully.")
    @Test
    public void createPostWithJpgPhoto() {
        postPage.createPostWithJpgFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a panoramic photo successfully.")
    @Test
    public void createPostWithPanoramicPhoto() {
        postPage.createPostWithPanoramicPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a photo and text successfully.")
    @Test
    public void createPostWithTextAndPhoto() {
        postPage.createPostWithTextAndPhoto();
        postPage.assertPostCreatedWithTextAndPhoto();
    }

    @Feature("Post")
    @Story("Like a post successfully.")
    @Test
    public void likePost() {
        postPage.createPublicPost();
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Feature("Post")
    @Story("Dislike a post successfully.")
    @Test
    public void dislikePost() {
        postPage.createPublicPost();
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Feature("Post")
    @Story("Like specific post successfully.")
    @Test
    public void likeSpecificPost() {
        postPage.likeSpecificPost();
        postPage.assertSpecificPostIsLiked();
    }

    @Feature("Post")
    @Story("Dislike specific post successfully.")
    @Test
    public void dislikeSpecificPost() {
        postPage.dislikeSpecificPost();
        postPage.assertSpecificPostIsDisliked();
    }

    @Feature("Post")
    @Story("Edit post content successfully.")
    @Test
    public void editPostContent() {
        postPage.createPublicPost();
        postPage.editPostContent();
        postPage.assertPostContentIsEdited();
    }

    @Feature("Post")
    @Story("Delete post successfully.")
    @Test
    public void deletePost() {
        postPage.createPublicPost();
        postPage.deletePost();
        postPage.assertPostDeleted();
    }
}
