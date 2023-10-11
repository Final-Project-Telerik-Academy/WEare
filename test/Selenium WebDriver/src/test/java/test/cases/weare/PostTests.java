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
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.assertPublicPostCreated();
    }

    @Test
    @Order(2)
    public void createPrivatePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPrivatePost(privatePostMessage);
        postPage.assertPrivatePostCreated();
    }

    @Test
    @Order(3)
    public void createPostWithPhoto() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPostWithPhoto();
        postPage.assertPostCreatedWithPhoto();
    }

    @Test
    @Order(4)
    public void likePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.likePost();
        postPage.assertPostIsLiked();
    }

    @Test
    @Order(5)
    public void dislikePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.likePost();
        postPage.dislikePost();
        postPage.assertPostIsDisliked();
    }

    @Test
    @Order(6)
    public void editPostContent() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.editPostContent(editedPostMessage);
        postPage.assertPostContentIsEdited();
    }
    @Test
    @Order(7)
    public void deletePost() {
        PostPage postPage = new PostPage(actions.getDriver());
        postPage.createPublicPost(publicPostMessage);
        postPage.deletePost();
        postPage.assertPostDeleted();
    }
}
