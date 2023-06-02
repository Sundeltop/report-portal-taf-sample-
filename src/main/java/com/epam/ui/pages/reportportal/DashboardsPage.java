package com.epam.ui.pages.reportportal;

import org.openqa.selenium.By;

import static com.epam.ui.webdriver.WebDriverStorage.getWebDriver;
import static com.epam.ui.utils.WaitUtils.waitFor;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class DashboardsPage {

    public DashboardsPage() {
        initElements(getWebDriver(), this);
    }

    public WidgetsPage openDashboard(String name) {
        String locator = String.format("//div[@class='gridRow__grid-row--1pS-5']/a[text()='%s']", name);
        waitFor(elementToBeClickable(By.xpath(locator))).click();

        return new WidgetsPage();
    }
}
