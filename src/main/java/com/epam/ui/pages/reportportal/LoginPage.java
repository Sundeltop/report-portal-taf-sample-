package com.epam.ui.pages.reportportal;

import com.epam.ui.pages.BasePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.epam.config.ConfigurationManager.configuration;

public class LoginPage extends BasePage {

    public LoginPage loginAsDefaultUser() {
        return login(configuration().defaultUserLogin(), configuration().defaultUserPassword());
    }

    public LoginPage login(String login, String password) {
        $(".loginForm__login-field--2NeYx input")
                .setValue(login)
                .shouldHave(value(login));

        $(".loginForm__password-field--2IH1A input")
                .setValue(password)
                .shouldHave(value(password));

        $(".loginForm__login-button-container--1mHGW")
                .shouldBe(enabled)
                .click();

        return this;
    }

    public void isLoginNotificationDisplayedWithText(String expectedNotificationText) {
        $(".notificationItem__message-container--16jY2")
                .shouldBe(visible)
                .shouldHave(text(expectedNotificationText));
    }

    public LoginPage forgotPassword() {
        $(".loginForm__forgot-pass--2mB6-")
                .shouldBe(enabled)
                .click();

        return this;
    }

    public void isRestoreOptionAvailable() {
        $(".blockHeader__huge-message--_I_zH")
                .shouldBe(visible)
                .shouldHave(text("Forgot password?"));
        $(".inputOutside__input--1Sg9p")
                .shouldBe(enabled);
        $(".forgotPasswordForm__forgot-password-button--1-83Y .bigButton__color-gray-60--2LUP6")
                .shouldBe(enabled);
        $(".forgotPasswordForm__forgot-password-button--1-83Y .bigButton__color-organish--4iYXy")
                .shouldBe(enabled);
    }
}
