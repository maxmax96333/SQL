package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/** The third page in the login flow: the authenticated dashboard. */
public class DashboardPage {
    private static final String EXPECTED_TITLE = "Личный кабинет";
    private final SelenideElement dashboard = $x("//*[@data-test-id='dashboard']");

    public void shouldBeOpened() {
        dashboard.shouldBe(visible).shouldHave(text(EXPECTED_TITLE));
    }
}
