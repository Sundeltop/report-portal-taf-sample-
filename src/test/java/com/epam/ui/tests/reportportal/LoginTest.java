package com.epam.ui.tests.reportportal;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.epam.config.ConfigurationManager;
import com.epam.ui.pages.reportportal.LoginPage;
import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;
import static com.epam.config.ConfigurationManager.configuration;

public class LoginTest extends BaseUiTest {

    @Test
    void checkLoginWithInvalidCredentials() {
        page(LoginPage.class)
                .login("login", "password")
                .isLoginNotificationDisplayedWithText("An error occurred while connecting to server: You do not have enough permissions. Bad credentials");
    }

    @Test
    void checkLoginWithValidCredentials() {
        page(LoginPage.class)
                .loginAsDefaultUser()
                .isLoginNotificationDisplayedWithText("Signed in successfully");
    }

    @Test
    void checkForgotPasswordShowsRestoreOption() {
        page(LoginPage.class)
                .forgotPassword()
                .isRestoreOptionAvailable();
    }

    @Test
    @Disabled("https://github.com/selenide/selenide/issues/2256")
    void checkCanLoginInTwoBrowsers() {
        SelenideDriver secondSession = new SelenideDriver(new SelenideConfig());
        try {
            page(LoginPage.class)
                    .loginAsDefaultUser()
                    .isLoginNotificationDisplayedWithText("Signed in successfully");

            secondSession.open(configuration().baseUrl(), LoginPage.class)
                    .loginAsDefaultUser()
                    .isLoginNotificationDisplayedWithText("Signed in successfully");
        } finally {
            secondSession.close();
        }
    }
}
