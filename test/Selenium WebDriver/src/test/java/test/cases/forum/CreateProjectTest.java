package test.cases.forum;

import org.junit.jupiter.api.Test;
import pages.forum.CreateNewProject;


public class CreateProjectTest extends BaseTest {
    @Test
    public void createNewProject() {

        login();

        CreateNewProject newProject = new CreateNewProject(actions.getDriver());
        newProject.createProject("SeleniumWebdriverHomework");
    }
}
