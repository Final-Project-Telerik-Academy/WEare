package pages.weare;

import org.openqa.selenium.WebDriver;

public class PostPage extends BaseWEArePage {
    public PostPage(WebDriver driver) {
        super(driver, "weare.homepage");
    }

    public void createPublicPost(String message) {

        //click Add New Post
        actions.hoverOverElement("weare.addNewPostButton");
        actions.waitForElementPresent("weare.addNewPostButton");
        actions.clickElement("weare.addNewPostButton");

//        Select public post
        actions.waitForElementPresent("weare.selectPublicOrPrivate");
        actions.clickElement("weare.selectPublicOrPrivate");

        actions.waitForElementPresent("weare.publicPostOption");
        actions.clickElement("weare.publicPostOption");

        //Fill in Post message
        actions.waitForElementPresent("weare.messageForm");
        actions.typeValueInField(message, "weare.messageForm");

        actions.clickElement("weare.submitButton");
    }

    public void assertPublicPostCreated() {
        //Welcome to our community header is present
        actions.waitForElementPresent("weare.assertPostPublic");
        //Please update your profile is present
        actions.waitForElementPresent("weare.assertTopic");
    }
}
