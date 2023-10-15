package weare.api.tests.Utils;
import org.testng.Assert;

public class AssertHelper {
    public static void assertStatusCode(int actual, int expected) {
        Assert.assertEquals(actual, expected, "Incorrect status code. Expected Status 200.");
    }

    public static void assertResponseBodyNotNull(Object responseBody) {
        Assert.assertNotNull(responseBody, "Response body is empty.");
    }

    public static void assertUserIdEquals(int actualUserId, int expectedUserId) {
        Assert.assertEquals(actualUserId, expectedUserId, "User ID does not match the expected value.");
    }

    public static void assertUsernameEquals(String actualUsername, String expectedUsername) {
        Assert.assertEquals(actualUsername, expectedUsername, "Incorrect username.");
    }

    public static void assertEmailEquals(String actualEmail, String expectedEmail) {
        Assert.assertEquals(actualEmail, expectedEmail, "Incorrect email.");
    }


}

