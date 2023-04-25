package com.epam.ui.tests.reportportal;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseUiTest {

    @Test
    void checkLoginWithInvalidCredentials() {
        openPage()
                .login("login", "password")
                .isLoginNotificationDisplayedWithText("An error occurred while connecting to server: You do not have enough permissions. Bad credentials");
    }

    @Test
    void checkLoginWithValidCredentials() {
        openPage()
                .loginAsDefaultUser()
                .isLoginNotificationDisplayedWithText("Signed in successfully");
    }

    @Test
    void checkForgotPasswordShowsRestoreOption() {
        openPage()
                .forgotPassword()
                .isRestoreOptionAvailable();
    }

    @Test
    @Disabled("https://github.com/selenide/selenide/issues/2256")
    void checkCanLoginInTwoBrowsers() {
        SelenideDriver secondSession = new SelenideDriver(new SelenideConfig());
        try {
            openPage()
                    .loginAsDefaultUser()
                    .isLoginNotificationDisplayedWithText("Signed in successfully");

            openPage(secondSession)
                    .loginAsDefaultUser()
                    .isLoginNotificationDisplayedWithText("Signed in successfully");
        } finally {
            secondSession.close();
        }
    }
}
