package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement loginField = $x("//*[@data-test-id='login']//input");
    private final SelenideElement passwordField = $x("//*[@data-test-id='password']//input");
    private final SelenideElement loginButton = $x("//*[@data-test-id='action-login']");

    public LoginPage() {
        loginField.shouldBe(visible);
    }

    public VerificationPage login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
        return new VerificationPage();
    }
}
