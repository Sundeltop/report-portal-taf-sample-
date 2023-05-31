package com.epam.ui.pages.reportportal;

import org.openqa.selenium.By;

import static com.epam.ui.WebDriverStorage.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.PageFactory.initElements;

public class LaunchesPage {

    public LaunchesPage() {
        initElements(getWebDriver(), this);
    }

    public void isLaunchesTableContainsColumn(String column) {
        String locator = ("//span[@class='headerCell__title-full--2CU9W' and text()='%s']" +
                "/ancestor::div[contains(@class,'headerCell__header-cell--hrQit')]")
                .formatted(column.toLowerCase());

        assertThat(getWebDriver().findElement(By.xpath(locator)).isDisplayed()).isTrue();
    }
}
