package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        loginPage = new LoginPage();
    }

    @Test
    void shouldLoginSuccessfully() {
        var login = "vasya";
        var password = "qwerty123";

        loginPage.login(login, password);

        var verificationPage = new VerificationPage();

        var code = DbHelper.getVerificationCode(login);

        verificationPage.verify(code);
    }

    @Test
    void shouldBlockUserAfterThreeInvalidPasswords() {
        var login = "vasya";
        var wrongPassword = "wrongPassword";

        for (int i = 0; i < 3; i++) {
            open("http://localhost:9999");

            var page = new LoginPage();
            page.login(login, wrongPassword);
        }

        var status = DbHelper.getUserStatus(login);

        assertEquals("blocked", status);
    }
}