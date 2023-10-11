package test.cases.weare;

import org.junit.jupiter.api.Test;
import pages.weare.PostPage;

public class PostTests extends BaseTest {
    @Test
    public void createPublicPost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.assertPublicPostCreated();
    }

    @Test
    public void createPrivatePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPrivatePost(privatePostMessage);
        postPage.assertPrivatePostCreated();
    }

    @Test
    public void createPostWithPhoto() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPostWithPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    public void likePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Test
    public void dislikePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.likePost();
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Test
    public void editPostContent() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.editPostContent(editedPostMessage);
        postPage.assertPostContentIsEdited();
    }
    @Test
    public void deletePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.deletePost();
        postPage.assertPostDeleted();
    }
}
