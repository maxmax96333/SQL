package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

class LoginTest {
    @BeforeEach
    void openLoginPage() {
        open("/");
        new LoginPage();
    }

    @Test
    void shouldLoginWithVerificationCodeReadFromDatabase() {
        String login = DataHelper.getValidLogin();
        String password = DataHelper.getValidPassword();

        VerificationPage verificationPage = new LoginPage().login(login, password);
        String verificationCode = DbHelper.getVerificationCode(login);
        DashboardPage dashboardPage = verificationPage.verify(verificationCode);

        dashboardPage.shouldBeOpened();
    }

    @AfterAll
    static void cleanDatabaseAfterTests() {
        DbHelper.cleanDatabase();
    }
}
