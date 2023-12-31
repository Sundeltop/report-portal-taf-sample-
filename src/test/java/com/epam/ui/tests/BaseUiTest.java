package com.epam.ui.tests;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.epam.extensions.ui.SelenideConfigurationExtension;
import com.epam.ui.pages.reportportal.LoginPage;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

@ExtendWith({
        SelenideConfigurationExtension.class,
        ScreenShooterExtension.class,
        BrowserPerTestStrategyExtension.class,
        TextReportExtension.class
})
public class BaseUiTest {

    protected LoginPage openPage() {
        return open("/", LoginPage.class);
    }

    protected <T> T openPage(Class<T> pageObjectClass) {
        return open("/", pageObjectClass);
    }

    protected LoginPage openPage(SelenideDriver selenideDriver) {
        return selenideDriver.open("/", LoginPage.class);
    }

    protected LoginPage openPageAndLoginAsDefaultUser() {
        openPage()
                .loginAsDefaultUser()
                .isLoginNotificationDisplayedWithText("Signed in successfully");

        return page(LoginPage.class);
    }
}
