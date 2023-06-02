package com.epam.ui.utils;

import org.openqa.selenium.WebElement;

import static com.epam.ui.webdriver.WebDriverStorage.getActions;
import static com.epam.ui.webdriver.WebDriverStorage.getJsExecutor;
import static java.lang.Boolean.parseBoolean;

public class WebDriverUtils {

    private WebDriverUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Can't create object of static class");
    }

    public static void jsClick(WebElement element) {
        getJsExecutor().executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoView(WebElement element) {
        String jsCode = "arguments[0].scrollIntoView();";
        getJsExecutor().executeScript(jsCode, element);
    }

    public static Boolean isScrolledIntoView(WebElement element) {
        String jsCode = "return arguments[0].getBoundingClientRect().top >= 0 && arguments[0].getBoundingClientRect().bottom <= window.innerHeight";
        return parseBoolean(getJsExecutor().executeScript(jsCode, element).toString());
    }

    public static void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        getActions().dragAndDrop(sourceElement, targetElement).perform();
    }

    public static void resize(WebElement element, Integer xOffset, Integer yOffset) {
        getActions()
                .clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release()
                .build()
                .perform();
    }
}
