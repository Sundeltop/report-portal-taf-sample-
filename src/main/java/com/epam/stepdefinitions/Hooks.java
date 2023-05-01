package com.epam.stepdefinitions;

import io.cucumber.java.After;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class Hooks {

    @After
    public void closeSelenideDriver() {
        closeWebDriver();
    }
}
