package test.cases.weare;

import org.junit.jupiter.api.Test;
import pages.weare.PostPage;

public class PostTests extends BaseTest {
    @Test
    public void createPublicPost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost("This is my new post");
        postPage.assertPublicPostCreated();
    }

    @Test
    public void likePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost("This is my new post");
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Test
    public void dislikePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost("This is my new post");
        postPage.likePost();
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Test
    public void editPostContent() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost("This is my new post");
        postPage.editPostContent("This is my edited post");
        postPage.assertPostContentIsEdited();
    }
    @Test
    public void deletePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost("This is my new post");
        postPage.deletePost();
        postPage.assertPostDeleted();
    }

    @Test
    public void createPostWithPhoto() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPostWithPhoto();
    }
}
