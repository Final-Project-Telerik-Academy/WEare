package com.weare.api.Utils;
import org.testng.Assert;

public class AssertHelper {
    public static void assertStatusCode(int expected, int actual) {
        Assert.assertEquals(actual, expected, "Incorrect status code. Expected Status 200.");
    }
    public static void assertResponseBodyNotNull(Object responseBody) {
        Assert.assertNotNull(responseBody, "Response body is empty.");
    }
    public static void assertUserIdEquals(int expectedUserId, int actualUserId) {
        Assert.assertEquals(actualUserId, expectedUserId, "User ID does not match the expected value.");
    }
    public static void assertUsernameEquals(String actualUsername, String expectedUsername) {
        Assert.assertEquals(actualUsername, expectedUsername, "Incorrect username.");
    }
    public static void assertEmailEquals(String actualEmail, String expectedEmail) {
        Assert.assertEquals(actualEmail, expectedEmail, "Incorrect email.");
    }
    public static void assertPostIsPrivate(boolean condition) {
        Assert.assertTrue(condition,"This post is not a public");
    }
    public static void assertContentTypeNotNull(Object contentType) {
        Assert.assertNotNull(contentType, "Content type is null.");
    }
    public static void assertContentEquals(String actualContent, String expectedContent) {
        Assert.assertEquals(actualContent, expectedContent, "Mismatch between actual and expected content.");
    }
    public static void assertCategoryIdNotNull(Object categoryId) {
        Assert.assertNotNull(categoryId, "Missing category ID.");
    }
    public static void assertCategoryIdsMatch(Object actualCategoryId, Object expectedCategoryId) {
        Assert.assertEquals(actualCategoryId, expectedCategoryId, "Expected category ID doesn't match user's category ID.");
    }
    public static void assertAvailabilityMatches(String actualAvailability, String expectedAvailability) {
        Assert.assertEquals(actualAvailability, expectedAvailability, "Mismatch between actual and expected availability.");
    }
    public static void assertSkillIdNotNull(Object skillId) {
        Assert.assertNotNull(skillId, "Missing skill ID.");
    }
    public static void assertSkillNameNotNull(String skillName) {
        Assert.assertNotNull(skillName, "The skill name is missing.");
    }
    public static void assertSkillNameMatches(String actualSkillName, String expectedSkillName) {
        Assert.assertEquals(actualSkillName, expectedSkillName, "Mismatch between the actual skill name and expected skill name.");
    }
    public static void assertCategoryNameMatches(String actualCategoryName, String expectedCategoryName) {
        Assert.assertEquals(actualCategoryName, expectedCategoryName, "Mismatch between actual and expected category name.");
    }
    public static void assertSkillIdMatches(Integer actualSkillId, Integer expectedSkillId) {
        Assert.assertEquals(actualSkillId, expectedSkillId, "Invalid skill ID.");
    }
}

