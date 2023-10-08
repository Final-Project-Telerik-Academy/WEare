package pages.forum;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static pages.forum.BaseJiraPage.*;

public class JiraActions extends BasePage {

    public JiraActions(WebDriver driver) {
        super(driver, "jira.homepage");
    }

    public void createAStory() {

        actions.waitForElementClickable("jira.createGlobalItemButton");
        actions.clickElement("jira.createGlobalItemButton");

        actions.waitForElementClickable("jira.issueTypeSelect");
        actions.clickElement("jira.issueTypeSelect");

        actions.clickElementWithJavaScriptExecutor("jira.selectStory");

        actions.waitForElementVisible("jira.issueInputTitle");
        actions.typeValueInField(storyTitle, "jira.issueInputTitle");

        actions.waitForElementVisible("jira.issueInputDescription");
        actions.typeValueInField(storyDescription, "jira.issueInputDescription");

        actions.waitForElementClickable("jira.prioritySelect");
        actions.clickElement("jira.prioritySelect");

        actions.waitForElementClickable("jira.selectPriorityHigh");
        actions.clickElement("jira.selectPriorityHigh");

        actions.waitForElementClickable("jira.createIssueButton");
        actions.clickElement("jira.createIssueButton");
    }


    public void createBug() {
        actions.waitForElementClickable("jira.createGlobalItemButton");
        actions.clickElement("jira.createGlobalItemButton");

        actions.waitForElementClickable("jira.issueTypeSelect");
        actions.clickElement("jira.issueTypeSelect");


        actions.clickElementWithJavaScriptExecutor("jira.selectBug");

        actions.waitForElementVisible("jira.issueInputTitle");
        actions.typeValueInField(bugTitle, "jira.issueInputTitle");

        actions.waitForElementVisible("jira.issueInputDescription");
        actions.typeValueInField(bugDescription, "jira.issueInputDescription");

        actions.waitForElementClickable("jira.prioritySelect");
        actions.clickElement("jira.prioritySelect");

        actions.waitForElementClickable("jira.selectPriorityHigh");
        actions.clickElement("jira.selectPriorityHigh");

        actions.waitForElementClickable("jira.createIssueButton");
        actions.clickElement("jira.createIssueButton");
    }

//    public void linkBugToStory() {
//        actions.waitForElementPresent("jira.selectIssuesFromMenu");
//        actions.clickElement("jira.selectIssuesFromMenu");
//
//        actions.waitForElementPresent("jira.selectALlIssues");
//        actions.clickElement("jira.selectALlIssues");
//
//        actions.waitForElementPresent("jira.selectIssueKey2");
//        actions.clickElement("jira.selectIssueKey2");
//
//        actions.waitForElementPresent("jira.linkIssuesButton");
//        actions.clickElement("jira.linkIssuesButton");
//
//        actions.waitForElementClickable("jira.selectIssue");
//        actions.clickElement("jira.selectIssue");
//
//        actions.waitForElementClickable("jira.blocks");
//        actions.clickElement("jira.blocks");
//
////        actions.waitForElementPresent("jira.searchIssue");
////        actions.typeValueInField("SEL-1 How to Book” section is empty", "jira.searchIssue");
//
////        actions.waitForElementPresent("jira.searchIssue");
////        actions.typeValueInField(String.valueOf(Keys.ENTER), "jira.searchIssue");
//
//
//        actions.waitForElementPresent("//input[@id='issue-link-search']");
//        actions.typeValueInField("SEL-1 How to Book” section is empty", "//input[@id='issue-link-search']");
//
//        actions.waitForElementPresent("//input[@id='issue-link-search']");
//        actions.typeValueInField(Keys.ENTER.toString(), "//input[@id='issue-link-search']");
//
//        actions.waitForElementPresent("jira.addIssueButton");
//        actions.clickElement("jira.addIssueButton");
////
//    }

    public void linkBugToStory() {
        actions.waitForElementPresent("jira.selectIssuesFromMenu");
        actions.clickElement("jira.selectIssuesFromMenu");

        actions.waitForElementPresent("jira.selectALlIssues");
        actions.clickElement("jira.selectALlIssues");

        actions.waitForElementPresent("jira.selectIssueKey2");
        actions.clickElement("jira.selectIssueKey2");

        actions.waitForElementPresent("jira.linkIssuesButton");
        actions.clickElement("jira.linkIssuesButton");

        actions.waitForElementClickable("jira.selectIssue");
        actions.clickElement("jira.selectIssue");

        actions.waitForElementClickable("jira.blocks");
        actions.clickElement("jira.blocks");

        actions.waitForElementPresent("jira.searchIssue");
        actions.typeValueInField("SEL-1", "jira.searchIssue");

        actions.waitForElementPresent("jira.searchIssue");
        actions.typeValueInField(String.valueOf(Keys.ENTER), "jira.searchIssue");

        actions.waitForElementPresent("jira.addIssueButton");
        actions.clickElement("jira.addIssueButton");
    }

    public void deleteProject() {
        actions.waitForElementPresent("jira.issueProjectSettings");
        actions.clickElement("jira.issueProjectSettings");

        actions.waitForElementPresent("jira.issueMoreOptions");
        actions.clickElement("jira.issueMoreOptions");

        actions.waitForElementPresent("jira.moveToTrash");
        actions.clickElement("jira.moveToTrash");

        actions.waitForElementPresent("jira.moveToTrashConfirmButton");
        actions.clickElement("jira.moveToTrashConfirmButton");

        actions.waitForElementPresent("jira.trash");
        actions.clickElement("jira.trash");

        actions.waitForElementPresent("jira.searchField");
        actions.clickElement("jira.searchField");
        actions.typeValueInField("SeleniumWebdriverHomework", "jira.searchField");

        actions.waitForElementPresent("jira.deleteOptions");
        actions.clickElement("jira.deleteOptions");

        actions.waitForElementPresent("jira.permanentlyDelete");
        actions.clickElement("jira.permanentlyDelete");

        actions.waitForElementPresent("jira.deleteConfirm");
        actions.clickElement("jira.deleteConfirm");

        //project-permanent-delete-modal.ui.flags.success-flag-expander
    }

    public void assertStoryCreated() {
        actions.waitForElementPresent("jira.assertIssueCreation");
    }

    public void assertBugCreated() {
        actions.waitForElementPresent("jira.assertIssueCreation");
    }

    public void assertLinkCreated() {
        actions.waitForElementPresent("jira.assertLinkedIssuesVisible");
        actions.waitForElementPresent("jira.assertBugLinkedToTheCorrectStory");
    }
}
