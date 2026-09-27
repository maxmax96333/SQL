package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private final SelenideElement codeField = $x("//*[@data-test-id='code']//input");
    private final SelenideElement verifyButton = $x("//*[@data-test-id='action-verify']");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage verify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        return new DashboardPage();
    }
}
