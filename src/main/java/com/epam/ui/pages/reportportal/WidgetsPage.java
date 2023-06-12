package com.epam.ui.pages.reportportal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.ui.utils.WaitUtils.waitFor;
import static com.epam.ui.utils.WebDriverUtils.resize;
import static com.epam.ui.webdriver.WebDriverStorage.getWebDriver;
import static java.lang.Double.parseDouble;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class WidgetsPage {

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS AREA']/ancestor::div[contains(@class,'react-grid-item')]//span[contains(@class,'react-resizable-handle')]")
    private WebElement launchStatisticsWidgetResizeHandler;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS AREA']/ancestor::div[contains(@class,'react-grid-item')]//div[@class='widget__widget--mVI7B']//*[name()='svg']")
    private WebElement launchStatisticsWidget;

    public WidgetsPage() {
        initElements(getWebDriver(), this);
    }

    public WidgetsPage resizeWidget(Integer xOffset, Integer yOffset) {
        resize(launchStatisticsWidgetResizeHandler, xOffset, yOffset);
        return this;
    }

    public Double getWidgetWidth() {
        return parseDouble(waitFor(visibilityOf(launchStatisticsWidget)).getAttribute("width"));
    }
}
