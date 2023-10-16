package weare.api.tests;

import base.BaseTestSetup;
import com.weare.api.Models.Skill;
import com.weare.api.Services.SkillService;
import com.weare.api.Utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.*;
import com.weare.api.Utils.AssertHelper;

import static com.weare.api.Utils.Endpoints.*;
import static io.restassured.RestAssured.*;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class SkillTests extends BaseTestSetup {
    protected Skill skill;
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
        skill = new Skill();

        String createSkillBody = SkillService.generateCreateSkill(skill);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .body(createSkillBody)
                .when()
                .post();

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

        Response response = given()
                .contentType(ContentType.JSON)
                .cookie(cookie.getName(), cookie.getValue())
                .when()
                .get();

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
        baseURI = format("%s%s", BASE_URL, EDIT_SKILL_ENDPOINT);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skill", skill.getName())
                .queryParam("skillId", skill.getId())
                .put();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertResponseBodyNotNull(response.getBody());
    }

    @Test
    @Order(4)
//    @Test(priority = 4)
    public void getOneSkillTest() {
        baseURI = format("%s%s", BASE_URL, GET_ONE_SKILL_ENDPOINT);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skill.getId())
                .get();

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

        baseURI = format("%s%s", BASE_URL, DELETE_SKILL_ENDPOINT);

        Response response = given()
                .cookie(cookie.getName(), cookie.getValue())
                .contentType(ContentType.JSON)
                .queryParam("skillId", skill.getId())
                .put();

        int statusCode = response.getStatusCode();
        AssertHelper.assertStatusCode(statusCode, SC_OK);
        AssertHelper.assertResponseBodyNotNull(response.getBody());
    }
}
