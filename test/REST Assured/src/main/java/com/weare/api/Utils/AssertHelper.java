package com.weare.api.Utils;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

public class AssertHelper {
    public static void assertStatusCode(int expected, int actual) {
        Assertions.assertEquals(expected, actual, "Incorrect status code. Expected Status 200.");
    }
    public static void assertResponseBodyNotNull(Object responseBody) {
        Assertions.assertNotNull(responseBody, "Response body is empty.");
    }
    public static void assertUserIdEquals(int expectedUserId, int actualUserId) {
        Assertions.assertEquals(expectedUserId, actualUserId, "User ID does not match the expected value.");
    }
    public static void assertUsernameEquals(String actualUsername, String expectedUsername) {
        Assertions.assertEquals(expectedUsername, actualUsername, "Incorrect username.");
    }
    public static void assertEmailEquals(String expectedEmail, String actualEmail) {
        Assertions.assertEquals(expectedEmail, actualEmail, "Incorrect email.");
    }
    public static void assertPostIsPrivate(boolean condition) {
        Assertions.assertTrue(condition,"This post is not a public");
    }
    public static void assertContentTypeNotNull(Object contentType) {
        Assertions.assertNotNull(contentType, "Content type is null.");
    }
    public static void assertContentEquals(String expectedContent, String actualContent) {
        Assertions.assertEquals(expectedContent, actualContent, "Mismatch between actual and expected content.");
    }
    public static void assertCategoryIdNotNull(Object categoryId) {
        Assertions.assertNotNull(categoryId, "Missing category ID.");
    }
    public static void assertCategoryIdsMatch(Object expectedCategoryId, Object actualCategoryId) {
        Assertions.assertEquals(expectedCategoryId, actualCategoryId, "Expected category ID doesn't match user's category ID.");
    }
    public static void assertAvailabilityMatches(String expectedAvailability, String actualAvailability) {
        Assertions.assertEquals(expectedAvailability, actualAvailability, "Mismatch between actual and expected availability.");
    }
    public static void assertSkillIdNotNull(Object skillId) {
        Assertions.assertNotNull(skillId, "Missing skill ID.");
    }
    public static void assertSkillNameNotNull(String skillName) {
        Assertions.assertNotNull(skillName, "The skill name is missing.");
    }
    public static void assertSkillNameMatches(String expectedSkillName, String actualSkillName) {
        Assertions.assertEquals(expectedSkillName, actualSkillName, "Mismatch between the actual skill name and expected skill name.");
    }
    public static void assertCategoryNameMatches(String expectedCategoryName, String actualCategoryName) {
        Assertions.assertEquals(expectedCategoryName, actualCategoryName, "Mismatch between actual and expected category name.");
    }
    public static void assertSkillIdMatches(Integer expectedSkillId, Integer actualSkillId) {
        Assertions.assertEquals(expectedSkillId, actualSkillId, "Invalid skill ID.");
    }
    public static void assertUsername(String expected, String actual) {
        Assertions.assertEquals(expected, actual, "Username does not match the expected value");
    }

    public static void assertPositiveUserId(int userId) {
        Assertions.assertTrue(userId > 0, "The user ID should be a positive integer");
    }

    public static void assertUserCreationResponse(String expected, String actual) {
        Assertions.assertEquals(expected, actual, "Response body does not match the expected value");
    }

    public static void assertNotEmptyResponse(String responseBody) {
        Assertions.assertFalse(responseBody.trim().isEmpty());
    }

    public static void assertValidStatusCode(boolean isValidStatusCode) {
        Assertions.assertTrue(isValidStatusCode, "Incorrect status code. Expected Status 200.");
    }

    public static void assertResponseBodyEquals(String expected, String actual) {
        Assertions.assertEquals(expected, actual, "Response body does not match the expected value.");
    }
    public static void assertRequestApprovalResponse(String expected, String actual) {
        Assertions.assertEquals(expected, actual, "Response body does not match the expected value");
    }

    public static void assertJsonFieldExists(JSONObject jsonObject, String fieldName, String errorMessage) {
        Assertions.assertTrue(jsonObject.has(fieldName), errorMessage);
    }
}

