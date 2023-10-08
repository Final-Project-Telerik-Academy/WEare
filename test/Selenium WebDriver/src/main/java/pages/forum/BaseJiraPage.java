package pages.forum;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public abstract class BaseJiraPage extends BasePage {
    public static String storyTitle = "How to Book” section is empty";
    public static String storyDescription = "As a user, I want to be able to access the How to book section, so that I can be aware of how to to book a vacation.";
    public static String bugTitle = "“How to Book” section is empty";
    public static String bugDescription = "As a user, As a user I want to be able to see the content in the “How to Book” section\n"
            + "Preconditions:\n"
            + "Browser: Google Chrome\n"
            + "Version 114.0.5735.199 (Official Build) (64-bit)\n"
            + "Steps to reproduce:\n"
            + "1. Navigate to https://www.phptravels.net/ru\n"
            + "2. Navigate to the Corporate section in the footer\n"
            + "3. Click on “How to Book”\n"
            + "Expected result:\n"
            + "1. Page is navigated\n"
            + "2. “How to Book” clicked\n"
            + "3. “How to Book” page is showing content\n"
            + "Actual result:\n"
            + "“How to Book” page does not have content";
    public BaseJiraPage(WebDriver driver, String urlKey) {
        super(driver, "jira.homepage");
    }
}
