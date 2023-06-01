package com.epam.ui.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.epam.ui.WebDriverStorage.getWebDriver;
import static java.lang.Boolean.parseBoolean;

public class WebDriverUtils {

    private static final Actions actions = new Actions(getWebDriver());
    private static final JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();

    private WebDriverUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Can't create object of static class");
    }

    public static void jsClick(WebElement element) {
        executor.executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoView(WebElement element) {
        String jsCode = "arguments[0].scrollIntoView();";
        executor.executeScript(jsCode, element);
    }

    public static Boolean isScrolledIntoView(WebElement element) {
        String jsCode = "return arguments[0].getBoundingClientRect().top >= 0 && arguments[0].getBoundingClientRect().bottom <= window.innerHeight";
        return parseBoolean(executor.executeScript(jsCode, element).toString());
    }

    public static void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    public static void resize(WebElement element, Integer xOffset, Integer yOffset) {
        actions
                .clickAndHold(element)
                .moveByOffset(xOffset, yOffset)
                .release()
                .build()
                .perform();
    }
}
