package com.weare.api.utils;
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
    public static void assertAvailabilityMatches(Float expectedAvailability, Float actualAvailability) {
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

    public static void assertPositiveUserId(int userId) {
        Assertions.assertTrue(userId > 0, "The user ID should be a positive integer");
    }

    public static void assertNotEmptyResponse(String responseBody) {
        Assertions.assertFalse(responseBody.trim().isEmpty(), "Response body should not be empty.");
    }

    public static void assertResponseBodyEquals(String expected, String actual) {
        Assertions.assertEquals(expected, actual, "Response body does not match the expected value.");
    }

    public static void assertJsonFieldExists(JSONObject jsonObject, String fieldName) {
        Assertions.assertTrue(jsonObject.has(fieldName), " Field '" + fieldName + "' not found in the response.");
    }

    public static void assertUserExists(boolean userExists, int userId) {
        Assertions.assertTrue(userExists, "User with ID " + userId + " should exist.");
    }

    public static void assertUserPostExist(boolean postExists, int postId) {
        if (!postExists) {
            throw new AssertionError("Post with ID " + postId + " not exists.");
        }
    }

    public static void assertFriendRequestStatus(boolean isFriendRequestSent, int senderId, int receiverId) {
        Assertions.assertTrue(isFriendRequestSent, "Friend request from user " + senderId + " to user " + receiverId + " should be sent.");
    }

    public static void assertPostCountByUserId(int actualCount, int expectedCount, int userId) {
        Assertions.assertEquals(expectedCount, actualCount, "User with ID " + userId + " should have " + expectedCount + " posts.");
    }

    public static void assertRequestPresence(boolean requestExists, int requestId) {
        Assertions.assertTrue(requestExists, "The request with ID " + requestId + " should exist in the database.");
    }

    public static void assertRequestApprovalStatus(boolean isApproved, int requestId) {
        Assertions.assertTrue(isApproved, "The request with ID " + requestId + " should be approved in the database.");
    }

    public static void assertPostIsLikedByUser(boolean isPostLiked) {
        Assertions.assertFalse(isPostLiked, "The post should be liked by the user.");
    }
}

