package pages.forum;

import org.openqa.selenium.WebDriver;

public class CreateNewProject extends BaseJiraPage {
    public CreateNewProject(WebDriver driver) {
        super(driver, "jira.homepage");
    }

    public void createProject(String projectName) {

        actions.waitForElementPresent("jira.select.jira.software");
        actions.clickElement("jira.select.jira.software");

        actions.waitForElementPresent("jira.createProjectsButton");
        actions.clickElement("jira.createProjectsButton");


        actions.waitForElementPresent("jira.selectScrumTemplate");
        actions.clickElement("jira.selectScrumTemplate");

        actions.waitForElementPresent("jira.useTemplateButton");
        actions.clickElement("jira.useTemplateButton");

        actions.waitForElementPresent("jira.selectCompanyManagedTemplate");
        actions.clickElement("jira.selectCompanyManagedTemplate");
        actions.waitForElementPresent("jira.typeProjectNameField");
        actions.typeValueInField(projectName, "jira.typeProjectNameField");

        actions.waitForElementVisible("jira.typeProjectKeyField");
        actions.typeValueInField("SEL", "jira.typeProjectKeyField");

        actions.clickElement("jira.submitButtonWhenProjectCreated");
    }

    public void findProject() {
        actions.waitForElementPresent("jira.select.jira.software");
        actions.clickElement("jira.select.jira.software");

        actions.waitForElementPresent("jira.projects");
        actions.clickElement("jira.projects");

        actions.waitForElementPresent("jira.viewAllProjects");
        actions.clickElement("jira.viewAllProjects");

        actions.waitForElementPresent("jira.searchField");
        actions.typeValueInField("SeleniumWebdriverHomework", "jira.searchField");

        actions.waitForElementPresent("jira.findProjectNameByText");
        actions.clickElement("jira.findProjectNameByText");
    }
}

