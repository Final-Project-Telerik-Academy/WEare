package test.cases.weare;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.RandomGenerator.*;

public class RegistrationTest extends BaseTest {
    @Feature("Registration")
    @Story("Register user with two characters for username successfully.")
    @Test
    public void UserRegistered_When_TwoCharactersForUsernameEntered() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Feature("Registration")
    @Story("Register user with twenty characters for username successfully.")
    @Test
    public void UserRegistered_When_TwentyCharactersForUsernameEntered() {
        username = generateRandomUsername(20);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Feature("Registration")
    @Story("Register user with one character for username unsuccessfully.")
    @Test
    public void UserNotRegistered_When_TryToEnterOneCharacterForUsername() {
        username = generateRandomUsername(1);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserNotRegisteredWithUnderTheRangeCharsForUsername();
    }

    @Feature("Registration")
    @Story("Register user with an already used username unsuccessfully.")
    @Test
    public void UserNotRegistered_When_TryToEnterAlreadyUsedUsername() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
        updatePersonalProfilePage.backToHome();
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserNotRegisteredWithAlreadyUsedUsername();
    }

    @Feature("Registration")
    @Story("Register user with twenty-one characters for username unsuccessfully.")
    @Test
    public void UserNotRegistered_When_TryToEnterTwentyOneCharactersForUsername() {
        username = generateRandomUsername(21);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Feature("Registration")
    @Story("Register user with eight characters for password successfully.")
    @Test
    public void UserRegistered_When_EightCharactersForPasswordEntered() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(8);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Feature("Registration")
    @Story("Register user with five characters for password unsuccessfully.")
    @Disabled(value = "Bug link: https://weare-project.atlassian.net/browse/WFP-160")
    @Test
    public void UserNotRegistered_When_TryToEnterFiveCharactersPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(5);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserNotRegisteredWithInvalidPassword();
    }

    @Feature("Registration")
    @Story("Register user with different confirmed password unsuccessfully.")
    @Test
    public void UserNotRegistered_With_TryToEnterDifferentConfirmedPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(8);
        confirmedPassword = generateRandomPassword(9);
        registrationPage.userRegistrationWithDifferentConfirmedPassword(username, email, password, confirmedPassword);
        registrationPage.assertUserNotRegisteredWithDifferentConfirmedPassword();
    }

    @Feature("Registration")
    @Story("Register user with seven characters for password unsuccessfully.")
    @Disabled(value = "Bug link: https://weare-project.atlassian.net/browse/WFP-159")
    @Test
    public void UserNotRegistered_When_TryToEnterSevenCharactersForPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(7);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserNotRegisteredWithInvalidPassword();
    }

    @Feature("Registration")
    @Story("Register user with only small letters for password unsuccessfully.")
    @Disabled(value = "Bug link: https://weare-project.atlassian.net/browse/WFP-182")

    @Test
    public void UserNotRegistered_When_TryToEnterOnlySmallLettersForPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateSmallLettersRandomString(10);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserNotRegisteredWithOnlyLettersForPassword();
    }
}
