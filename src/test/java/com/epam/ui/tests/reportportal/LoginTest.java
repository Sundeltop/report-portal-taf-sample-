package com.epam.ui.tests.reportportal;

import com.epam.ui.tests.BaseUiTest;
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
        openPageAndLoginAsDefaultUser();
    }

    @Test
    void checkForgotPasswordShowsRestoreOption() {
        openPage()
                .forgotPassword()
                .isRestoreOptionAvailable();
    }
}
