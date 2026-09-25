package ru.netology.test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginField =
            $("[data-test-id=login] input");

    private final SelenideElement passwordField =
            $("[data-test-id=password] input");

    private final SelenideElement loginButton =
            $("[data-test-id=action-login]");

    public LoginPage() {
        loginField.shouldBe(visible);
    }

    public LoginPage login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();

        return this;
    }
}
