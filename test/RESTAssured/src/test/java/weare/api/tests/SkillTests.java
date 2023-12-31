package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.models.Skill;
import com.weare.api.models.User;
import com.weare.api.services.SkillService;
import com.weare.api.utils.AssertHelper;
import com.weare.api.utils.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.weare.api.services.SkillService.*;
import static com.weare.api.utils.Endpoints.*;
import static io.restassured.RestAssured.baseURI;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

public class SkillTests extends BaseTestSetup {
    Skill skill = new Skill();

    @Override
    @BeforeEach
    protected void beforeEach() {
        user = new User();
        register(user);
        login(user);
    }

    @AfterEach
    public void tearDownAfterTest() {
        logout();
    }

    @Feature("Skills")
    @Story("Create a skill")
    @Description("Test to verify that a new skill can be created successfully.")
    @Test
    public void createASkillTest() {
        baseURI = format("%s%s", BASE_URL, CREATE_SKILL_ENDPOINT);

        String createSkillBody = SkillService.createASkillRequest(skill);
        Response response = createASkill(createSkillBody, cookie);

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);

        Integer skillId = response.getBody().jsonPath().get("skillId");
        skill.setId(skillId);

        String skillName = response.getBody().jsonPath().get("skill");
        Integer categoryId = response.getBody().jsonPath().get("category.id");
        String categoryName = response.getBody().jsonPath().get("category.name");

        AssertHelper.assertSkillIdNotNull(skillId);
        AssertHelper.assertSkillNameMatches(skillName, skill.getName());
        AssertHelper.assertCategoryIdsMatch(categoryId, Constants.CATEGORY_ID);
        AssertHelper.assertCategoryNameMatches(categoryName, Constants.CATEGORY_NAME);
    }

    @Feature("Skills")
    @Story("Retrieve all skills")
    @Description("Test to verify that all skills can be retrieved successfully.")
    @Test
    public void getSkillsTest() {
        baseURI = format("%s%s", BASE_URL, GET_SKILLS_ENPOINT);

        Response response = getSkill(cookie);

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, SC_OK, "Incorrect status code. Expected Status 200.");

        Integer skillId = response.getBody().jsonPath().get("[0].skillId");
        String skillName = response.getBody().jsonPath().get("[0].skill");
        Integer categoryId = response.getBody().jsonPath().get("[0].category.id");
        String categoryName = response.getBody().jsonPath().get("[0].category.name");
        AssertHelper.assertSkillIdNotNull(skillId);
        AssertHelper.assertSkillNameNotNull(skillName);
        AssertHelper.assertCategoryIdsMatch(categoryId, Constants.CATEGORY_ID);
        AssertHelper.assertCategoryNameMatches(categoryName, Constants.CATEGORY_NAME);
    }

    @Feature("Skills")
    @Story("Edit a skill")
    @Description("Test to verify that a skill can be edited successfully.")
    @Test
    public void editASkillTest() {
        createASkillTest();
        baseURI = format("%s%s", BASE_URL, EDIT_SKILL_ENDPOINT);

        Response response = editASkill(cookie, skill.getName(), skill.getId());

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(SC_OK, statusCode);
        AssertHelper.assertResponseBodyNotNull(response.getBody());
    }

    @Feature("Skills")
    @Story("Retrieve a single Skill")
    @Description("Test to verify that a single skill can be retrieved by its unique ID.")
    @Test
    public void getOneSkillTest() {
        createASkillTest();
        baseURI = format("%s%s", BASE_URL, GET_ONE_SKILL_ENDPOINT);

        Response response = getOneSKill(cookie, skill.getId());

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);

        Integer skillId = response.getBody().jsonPath().get("skillId");
        String skillName = response.getBody().jsonPath().get("skill");
        Integer categoryId = response.getBody().jsonPath().get("category.id");
        String categoryName = response.getBody().jsonPath().get("category.name");

        AssertHelper.assertSkillIdMatches(skillId, skill.getId());
        AssertHelper.assertSkillNameMatches(skillName, skill.getName());
        AssertHelper.assertCategoryIdsMatch(categoryId, Constants.CATEGORY_ID);
        AssertHelper.assertCategoryNameMatches(categoryName, Constants.CATEGORY_NAME);
    }

    @Feature("Skills")
    @Story("Delete a skill")
    @Description("Test to verify that a skill can be deleted successfully.")
    @Test
    public void deleteASkillTest() {
        createASkillTest();
        baseURI = format("%s%s", BASE_URL, DELETE_SKILL_ENDPOINT);

        Response response = deleteSkill(cookie, skill.getId());

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertResponseBodyNotNull(response.getBody());
    }
}
