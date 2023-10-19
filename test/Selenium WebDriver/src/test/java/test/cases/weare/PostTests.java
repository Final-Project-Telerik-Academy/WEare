package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostTests extends BaseTest {

    @BeforeEach
    public void setupTest() {
        UserRegistered_When_ValidCredentialsEntered();
        UserLoggedIn_When_ValidDetailsEntered();
    }

    @Feature("Post")
    @Story("Create a public post with valid input successfully.")
    @AfterEach
    public void performLogout() {
        UserLoggedOut_When_ClickLogout();
    }

    @Feature("Post")
    @Story("Create a public post with valid input successfully.")
    @Test
    public void PublicPostCreated_When_ClickAddNewPost() {
        postPage.createPublicPost();
        postPage.assertPublicPostCreated();
    }

    @Feature("Post")
    @Story("Create a private post with valid input successfully.")
    @Test
    public void PrivatePostCreated_When_ClickAddNewPost() {
        postPage.createPrivatePost();
        postPage.assertPrivatePostCreated();
    }

    @Feature("Post")
    @Story("Create a post with one character successfully.")

    @Test
    public void PostCreated_When_OneCharacterEntered() {
        postPage.createPostWithOneCharacter();
        postPage.assertPostWithOneCharacterCreated();
    }

    @Feature("Post")
    @Story("Create a post with 999 characters successfully.")

    @Test
    public void PostCreated_When_999CharactersEntered() {
        postPage.createPostWith999Character();
        postPage.assertPostWith999CharactersCreated();
    }

    @Feature("Post")
    @Story("Create a post with 1000 characters successfully.")
    @Test
    public void PostCreated_When_1000CharactersEntered() {
        postPage.createPostWith1000Character();
        postPage.assertPostWith1000CharactersCreated();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a png format photo successfully.")
    @Test
    public void PostCreated_When_PngPhotoAttached() {
        postPage.createPostWithPngFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a jpg format photo successfully.")
    @Test
    public void PostCreated_When_JpgPhotoAttached() {
        postPage.createPostWithJpgFormatPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a panoramic photo successfully.")
    @Test
    public void PostCreated_When_PanoramicPhotoAttached() {
        postPage.createPostWithPanoramicPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Feature("Post")
    @Story("Create a post with valid size of a photo and text successfully.")
    @Test
    public void PostCreated_When_Text_Entered_And_Post_Attached() {
        postPage.createPostWithTextAndPhoto();
        postPage.assertPostCreatedWithTextAndPhoto();
    }

    @Feature("Post")
    @Story("Like a post successfully.")
    @Test
    public void PostLiked_When_ClickLikeButton() {
        postPage.createPublicPost();
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Feature("Post")
    @Story("Dislike a post successfully.")
    @Test
    public void PostDisliked_When_ClickDislike() {
        postPage.createPublicPost();
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Feature("Post")
    @Story("Like specific post successfully.")
    @Test
    public void SpecificPostLiked_When_ClickLikeButton() {
        postPage.likeSpecificPost();
        postPage.assertSpecificPostIsLiked();
    }

    @Feature("Post")
    @Story("Dislike specific post successfully.")
    @Test
    public void SpecificPostDisiked_When_ClickDislikeButton() {
        postPage.dislikeSpecificPost();
        postPage.assertSpecificPostIsDisliked();
    }

    @Feature("Post")
    @Story("Edit post content successfully.")
    @Test
    public void PostContentEdited_When_ClickEditButton() {
        postPage.createPublicPost();
        postPage.editPostContent();
        postPage.assertPostContentIsEdited();
    }

    @Feature("Post")
    @Story("Delete post successfully.")
    @Test
    public void PostDeleted_When_ClickDeletePost() {
        postPage.createPublicPost();
        postPage.deletePost();
        postPage.assertPostDeleted();
    }
}
