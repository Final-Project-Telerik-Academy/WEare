package test.cases.weare;

import org.junit.jupiter.api.Test;


public class RegistrationTest extends BaseTest {
    @Test
    public void registerWithTwoCharactersForUsername() {
        username = generateTwoCharacterUsername();
        email = generateRandomEmail();
        password = generateEightSymbolsPassword();
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }

    @Test
    public void registerWithTwentyCharactersForUsername() {
        username = generateTwentyCharacterUsername();
        email = generateRandomEmail();
        password = generateEightSymbolsPassword();
        registrationPage.userRegistration(username, email, password);
        registrationPage.assertUserRegistered();
    }
}
