package test.cases.weare;

import org.junit.jupiter.api.Test;

import static com.telerikacademy.testframework.RandomGenerator.*;

public class RegistrationTest extends BaseTest {

    @Test
    public void registerWithTwoCharactersForUsername() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Test
    public void registerWithTwentyCharactersForUsername() {
        username = generateRandomUsername(20);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Test
    public void registerWithUnderTheRangeCharactersForUsername() {
        username = generateRandomUsername(1);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserNotRegisteredWithUnderTheRangeCharsForUsername();
    }

    @Test
    public void registerWithAlreadyUsedUsername() {
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

    @Test
    public void registerWithAboveTheRangeCharactersForUsername() {
        username = generateRandomUsername(21);
        email = generateRandomEmail();
        password = generateRandomPassword(12);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Test
    public void registerWithEightCharactersForPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(8);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Test
    public void registerWithFiveCharactersForPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(5);
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserNotRegisteredWithInvalidPassword();
    }

    @Test
    public void registerUserWithDifferentConfirmedPassword() {
        username = generateRandomUsername(2);
        email = generateRandomEmail();
        password = generateRandomPassword(8);
        confirmedPassword = generateRandomPassword(9);
        registrationPage.userRegistrationWithDifferentConfirmedPassword(username, email, password, confirmedPassword);
        registrationPage.assertUserNotRegisteredWithDifferentConfirmedPassword();
    }

}
