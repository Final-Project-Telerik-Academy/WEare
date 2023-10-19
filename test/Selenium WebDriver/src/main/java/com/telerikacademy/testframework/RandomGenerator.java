package com.telerikacademy.testframework;

import com.github.javafaker.Faker;
import com.github.javafaker.Number;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    protected static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    protected static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=<>?/[]{}|\\~`";
    private static final Faker faker = new Faker();

    public static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public static String generateRandomMessage() {
        return new RandomGenerator().faker.lorem().sentence();
    }

    public static String generateRandomFirstName() {
        return new RandomGenerator().faker.name().firstName();
    }

    public static String generateRandomLastName() {
        return new RandomGenerator().faker.name().lastName();
    }

    public static String generateRandomSpecialCharactersMessage(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(SPECIAL_CHARACTERS.length());
            char randomChar = SPECIAL_CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public static String generateRandomHyperlink() {
        RandomGenerator randomGenerator = new RandomGenerator();
        String domain = randomGenerator.faker.internet().domainName();
        String path = randomGenerator.generateSlug();
        String url = "http://" + domain + "/" + path;
        return url;
    }

    private String generateSlug() {
        List<String> words = faker.lorem().words(2);
        return String.join("-", words);
    }

    public static String generateRandomEmail() {
        return new RandomGenerator().faker.internet().emailAddress();
    }

    public static String generateRandomUsername() {
        String username = new RandomGenerator().faker.lorem().characters(2, 20, false, false);
        return username.toLowerCase();
    }

    public static String generateRandomPassword(int length) {
        String randomPassword = faker.lorem().characters(length - 2);
        String randomDigit = faker.number().digit();
        String specialChar = faker.regexify("[!@#$%^&*()]");

        randomPassword = randomPassword + randomDigit + specialChar;
        return randomPassword;
    }

    public static String generateRandomUsername(int length) {
        String randomUsername = faker.regexify("[a-zA-Z]{" + length + "}");
        return randomUsername;
    }

    public static String generateSmallLettersRandomString(int length) {
        String characterSet = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = characterSet.toCharArray();
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            randomString.append(chars[random.nextInt(chars.length)]);
        }

        return randomString.toString();
    }
}