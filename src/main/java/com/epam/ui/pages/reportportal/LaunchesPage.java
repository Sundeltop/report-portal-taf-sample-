package com.epam.ui.pages.reportportal;

import com.epam.ui.pages.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LaunchesPage extends BasePage {

    public void isLaunchesTableContainsColumn(String column) {
        $x(String.format("//span[@class='headerCell__title-full--2CU9W' and text()='%s']" +
                "/ancestor::div[contains(@class,'headerCell__header-cell--hrQit')]", column.toLowerCase()))
                .shouldBe(visible);
    }

}
