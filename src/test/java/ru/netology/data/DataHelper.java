package ru.netology.data;

/**
 * Test data for the demo user seeded by app-deadline.jar.
 */
public final class DataHelper {
    private static final String VALID_LOGIN = "vasya";
    private static final String VALID_PASSWORD = "qwerty123";

    private DataHelper() {
    }

    public static String getValidLogin() {
        return VALID_LOGIN;
    }

    public static String getValidPassword() {
        return VALID_PASSWORD;
    }
}
