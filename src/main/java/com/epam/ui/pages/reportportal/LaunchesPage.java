package com.epam.ui.pages.reportportal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.ui.WebDriverStorage.getWebDriver;
import static com.epam.ui.utils.WebDriverUtils.isScrolledIntoView;
import static com.epam.ui.utils.WebDriverUtils.scrollIntoView;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.PageFactory.initElements;

public class LaunchesPage {

    @FindBy(css = ".launchFiltersToolbar__add-filter-button--GqSO3 button")
    private WebElement addFilterButton;

    @FindBy(tagName = "footer")
    private WebElement footer;

    public LaunchesPage() {
        initElements(getWebDriver(), this);
    }

    public void isLaunchesTableContainsColumn(String column) {
        String locator = ("//span[@class='headerCell__title-full--2CU9W' and text()='%s']" +
                "/ancestor::div[contains(@class,'headerCell__header-cell--hrQit')]")
                .formatted(column.toLowerCase());

        assertThat(getWebDriver().findElement(By.xpath(locator)).isDisplayed()).isTrue();
    }

    public LaunchesPage scrollToPageBottom() {
        scrollIntoView(footer);

        return this;
    }

    public void isAddFilterButtonNotIntoView() {
        assertThat(isScrolledIntoView(addFilterButton)).isFalse();
    }
}
