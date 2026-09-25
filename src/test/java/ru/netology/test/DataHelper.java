package ru.netology.test;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    public static String generateLogin() {
        return faker.name().username();
    }

    public static String generatePassword() {
        return faker.internet().password(8, 16);
    }

    public static String generateInvalidPassword() {
        return faker.internet().password(8, 16);
    }
}