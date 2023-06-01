package com.epam.ui.pages.reportportal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.ui.WebDriverStorage.getWebDriver;
import static com.epam.ui.utils.WebDriverUtils.resize;
import static org.openqa.selenium.support.PageFactory.initElements;

public class WidgetsPage {

    @FindBy(css = ".react-grid-item:first-of-type .react-resizable-handle")
    private WebElement firstWidgetResizeHandler;

    @FindBy(css = ".react-grid-item:first-of-type .widget__widget--mVI7B svg")
    private WebElement firstWidget;

    public WidgetsPage() {
        initElements(getWebDriver(), this);
    }

    public WidgetsPage resizeWidget(Integer xOffset, Integer yOffset) {
        resize(firstWidgetResizeHandler, xOffset, yOffset);
        return this;
    }

    public Double getWidgetWidth() {
        return Double.parseDouble(firstWidget.getAttribute("width"));
    }
}
