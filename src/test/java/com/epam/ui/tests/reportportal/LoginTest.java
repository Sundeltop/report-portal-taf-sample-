package com.epam.ui.tests.reportportal;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import com.epam.ui.pages.reportportal.LoginPage;
import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
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
        SelenideConfig selenideConfig = new SelenideConfig();
        SelenideDriver firstSession = new SelenideDriver(selenideConfig);
        SelenideDriver secondSession = new SelenideDriver(selenideConfig);

        String url = String.format("%s/ui/#login", configuration().baseUrl());
        firstSession.open(url);
        secondSession.open(url);

        performLogin(firstSession);
        performLogin(secondSession);

        firstSession.close();
        secondSession.close();
    }

    private void performLogin(SelenideDriver driver) {
        String login = configuration().defaultUserLogin();
        String password = configuration().defaultUserPassword();
        String expectedNotificationText = "Signed in successfully";

        driver.$(".loginForm__login-field--2NeYx input")
                .setValue(login)
                .shouldHave(value(login));

        driver.$(".loginForm__password-field--2IH1A input")
                .setValue(password)
                .shouldHave(value(password));

        driver.$(".loginForm__login-button-container--1mHGW")
                .shouldBe(enabled)
                .click();

        driver.$(".notificationItem__message-container--16jY2")
                .shouldBe(visible)
                .shouldHave(text(expectedNotificationText));
    }
}
