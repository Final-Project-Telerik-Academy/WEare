package test.cases.weare;

import org.junit.jupiter.api.Test;
import pages.weare.PostPage;

public class PostTest extends BaseTest {
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

}
