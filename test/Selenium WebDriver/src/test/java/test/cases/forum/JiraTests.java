package test.cases.forum;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pages.forum.CreateNewProject;
import pages.forum.JiraActions;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class JiraTests extends BaseTest {
    @Test
    @Order(1)
    public void CreateAStory() {
        login();

        //If the test are not running all together, create the project by running
        // the commented code or run the CreateProjectTest class first and then the methods in this one (one by one).

//        CreateNewProject newProject = new CreateNewProject(actions.getDriver());
//        newProject.createProject("SeleniumWebdriverHomework");

        CreateNewProject newProject = new CreateNewProject(actions.getDriver());
        newProject.findProject();

        JiraActions jiraActions = new JiraActions(actions.getDriver());
        jiraActions.createAStory();
        jiraActions.assertStoryCreated();
  }

    @Test
    @Order(2)
    public void CreateBug() {
        login();

        CreateNewProject newProject = new CreateNewProject(actions.getDriver());
        newProject.findProject();

        JiraActions jiraActions = new JiraActions(actions.getDriver());
        jiraActions.createBug();
        jiraActions.assertBugCreated();

    }
    @Test
    @Order(3)
    public void LinkBugAndStory() {
        login();

        CreateNewProject newProject = new CreateNewProject(actions.getDriver());
        newProject.findProject();

        JiraActions jiraActions = new JiraActions(actions.getDriver());
        jiraActions.linkBugToStory();
        jiraActions.assertLinkCreated();
    }
    @Test
    @Order(4)
    public void deleteProject() {
        login();

        CreateNewProject newProject = new CreateNewProject(actions.getDriver());
        newProject.findProject();

        JiraActions jiraActions = new JiraActions(actions.getDriver());
        jiraActions.deleteProject();
    }
}
