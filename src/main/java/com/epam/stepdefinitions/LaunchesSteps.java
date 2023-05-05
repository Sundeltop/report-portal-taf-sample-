package com.epam.stepdefinitions;

import com.epam.api.pojos.CreateLaunchResponse;
import com.epam.ui.pages.reportportal.LaunchesPage;
import io.cucumber.java8.En;

import static com.codeborne.selenide.Selenide.page;
import static com.epam.TestExecutionContext.getLaunch;

public class LaunchesSteps implements En {

    public LaunchesSteps() {
        LaunchesPage launchesPage = page(LaunchesPage.class);
        When("User opens this launch", () -> {
            CreateLaunchResponse launch = getLaunch();
            launchesPage.openLaunchDetails(launch.getId(), launch.getNumber());
        });
        Then("Launches table contains {string} column", launchesPage::isLaunchesTableContainsColumn);
        Then("User is able to see launch details", launchesPage::isLaunchDetailsOpened);
    }
}
