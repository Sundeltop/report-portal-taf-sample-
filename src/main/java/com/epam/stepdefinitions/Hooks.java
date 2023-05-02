package com.epam.stepdefinitions;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.BeforeAll;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.epam.config.ConfigurationManager.configuration;

@Log4j2
public class Hooks implements En {

    public Hooks() {
        Before((Scenario scenario) ->
                log.info("Starting Scenario '{}'", scenario.getName()));
        After((Scenario scenario) ->
                log.info("Scenario '{}' is finished with status: {}", scenario.getName(), scenario.getStatus()));
        After(WebDriverRunner::closeWebDriver);
    }

    @BeforeAll
    public static void setupSelenide() {
        baseUrl = configuration().baseUrl();
    }
}
