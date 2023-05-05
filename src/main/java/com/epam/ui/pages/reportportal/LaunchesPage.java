package com.epam.ui.pages.reportportal;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class LaunchesPage {

    public void isLaunchesTableContainsColumn(String column) {
        $x(String.format("//span[@class='headerCell__title-full--2CU9W' and text()='%s']" +
                "/ancestor::div[contains(@class,'headerCell__header-cell--hrQit')]", column.toLowerCase()))
                .shouldBe(visible);
    }

    public void openLaunchDetails(String launchName, Integer launchNumber) {
        $x(String.format("//span[text()='%s']/following::span[text()='%d']", launchName, launchNumber))
                .shouldBe(visible)
                .click();
    }

    public void isLaunchDetailsOpened() {
        $$(".launchSuiteGrid__name-col--_sfp1")
                .shouldBe(sizeGreaterThanOrEqual(1));
    }

}
