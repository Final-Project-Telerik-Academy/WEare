package weare.api.tests.Utils;


import org.junit.jupiter.api.Assertions;

public class AssertHelper {
    public static void assertStatusCode(int actual, int expected) {
        Assertions.assertEquals(actual, expected, "Incorrect status code. Expected Status 200.");
    }
    public static void assertResponseBodyNotNull(Object responseBody) {
        Assertions.assertNotNull(responseBody, "Response body is empty.");
    }
    public static void assertUserIdEquals(int actualUserId, int expectedUserId) {
        Assertions.assertEquals(actualUserId, expectedUserId, "User ID does not match the expected value.");
    }
    public static void assertUsernameEquals(String actualUsername, String expectedUsername) {
        Assertions.assertEquals(actualUsername, expectedUsername, "Incorrect username.");
    }
    public static void assertEmailEquals(String actualEmail, String expectedEmail) {
        Assertions.assertEquals(actualEmail, expectedEmail, "Incorrect email.");
    }
    public static void assertPostIsPrivate(boolean condition) {
        Assertions.assertTrue(condition,"This post is not a public");
    }
    public static void assertContentTypeNotNull(Object contentType) {
        Assertions.assertNotNull(contentType, "Content type is null.");
    }
    public static void assertContentEquals(String actualContent, String expectedContent) {
        Assertions.assertEquals(actualContent, expectedContent, "Mismatch between actual and expected content.");
    }
    public static void assertCategoryIdNotNull(Object categoryId) {
        Assertions.assertNotNull(categoryId, "Missing category ID.");
    }
    public static void assertCategoryIdsMatch(Object actualCategoryId, Object expectedCategoryId) {
        Assertions.assertEquals(actualCategoryId, expectedCategoryId, "Expected category ID doesn't match user's category ID.");
    }
    public static void assertAvailabilityMatches(String actualAvailability, String expectedAvailability) {
        Assertions.assertEquals(actualAvailability, expectedAvailability, "Mismatch between actual and expected availability.");
    }
    public static void assertSkillIdNotNull(Object skillId) {
        Assertions.assertNotNull(skillId, "Missing skill ID.");
    }
    public static void assertSkillNameNotNull(String skillName) {
        Assertions.assertNotNull(skillName, "The skill name is missing.");
    }
    public static void assertSkillNameMatches(String actualSkillName, String expectedSkillName) {
        Assertions.assertEquals(actualSkillName, expectedSkillName, "Mismatch between the actual skill name and expected skill name.");
    }
    public static void assertCategoryNameMatches(String actualCategoryName, String expectedCategoryName) {
        Assertions.assertEquals(actualCategoryName, expectedCategoryName, "Mismatch between actual and expected category name.");
    }
    public static void assertSkillIdMatches(Integer actualSkillId, Integer expectedSkillId) {
        Assertions.assertEquals(actualSkillId, expectedSkillId, "Invalid skill ID.");
    }
}

