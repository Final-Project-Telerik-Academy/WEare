package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.Skill;
import com.weare.api.Services.SkillService;
import com.weare.api.Utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import org.junit.jupiter.api.*;
import com.weare.api.Utils.AssertHelper;

import static com.weare.api.Utils.Endpoints.*;
import static com.weare.api.Utils.ResponseHelper.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class SkillTests extends BaseTestSetup {
    private Skill skill = new Skill();
//    @BeforeMethod
    @BeforeEach
    public void setupTest() {
        register();
        login();
    }

    @AfterEach
//    @AfterMethod
    public void tearDownAfterTest() {
        logout();
    }

    @Test
    @Order(1)
//    @Test(priority = 1)
    public void createASkillTest() {
        baseURI = format("%s%s", BASE_URL, CREATE_SKILL_ENDPOINT);

        String createSkillBody = SkillService.generateCreateSkill(skill);
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

    @Test
    @Order(2)
//    @Test(priority = 2)
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

    @Test
    @Order(3)
//    @Test(priority = 3)
    public void editASkillTest() {
        createASkillTest();
        baseURI = format("%s%s", BASE_URL, EDIT_SKILL_ENDPOINT);

        Response response = editASkill(cookie, skill.getName(), skill.getId());

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(SC_OK, statusCode);
        AssertHelper.assertResponseBodyNotNull(response.getBody());
    }

    @Test
    @Order(4)
//    @Test(priority = 4)
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

    @Test
    @Order(5)
//    @Test(priority = 5)
    public void deleteASkillTest() {
        createASkillTest();
        baseURI = format("%s%s", BASE_URL, DELETE_SKILL_ENDPOINT);

        Response response = deleteSkill(cookie, skill.getId());

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertResponseBodyNotNull(response.getBody());
    }
}
