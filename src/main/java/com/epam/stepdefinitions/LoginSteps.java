package com.epam.stepdefinitions;

import com.epam.ui.pages.reportportal.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

import static com.codeborne.selenide.Selenide.open;

public class LoginSteps implements En {

    private LoginPage loginPage;

    public LoginSteps() {
        Given("User opens login page", () ->
                loginPage = open("/", LoginPage.class));
        When("User logs in as default user", () ->
                loginPage.loginAsDefaultUser());
        When("User logs in with credentials", (DataTable table) ->
                loginPage.login(
                        table.transpose().asList().get(0),
                        table.transpose().asList().get(1))
        );
        When("User clicks Forgot Password?", () -> loginPage.forgotPassword());
        When("User opens launches tab", () -> loginPage.openLauchesTab());
        Then("Login notification {string} is displayed", (String expectedMessage) ->
                loginPage.isLoginNotificationDisplayedWithText(expectedMessage));
        Then("Restore option should be available", () -> loginPage.isRestoreOptionAvailable());
    }
}
