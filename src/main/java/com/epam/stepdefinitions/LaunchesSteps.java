package com.epam.stepdefinitions;

import com.epam.ui.pages.reportportal.LaunchesPage;
import io.cucumber.java8.En;

import static com.codeborne.selenide.Selenide.page;

public class LaunchesSteps implements En {

    public LaunchesSteps() {
        LaunchesPage launchesPage = page(LaunchesPage.class);
        Then("Launches table contains {string} column", launchesPage::isLaunchesTableContainsColumn);
    }
}
