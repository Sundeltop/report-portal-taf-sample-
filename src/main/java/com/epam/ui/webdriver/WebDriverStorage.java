package com.epam.ui.webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class WebDriverStorage {

    private static final ThreadLocal<WebDriverInstance> driverInstance = new ThreadLocal<>();

    private WebDriverStorage() throws IllegalAccessException {
        throw new IllegalAccessException("Can't create object of static class");
    }

    public static WebDriver getWebDriver() {
        return driverInstance.get().getDriver();
    }

    public static Actions getActions() {
        return driverInstance.get().getActions();
    }

    public static JavascriptExecutor getJsExecutor() {
        return driverInstance.get().getJsExecutor();
    }

    public static void setWebDriver(WebDriver driver) {
        driverInstance.set(new WebDriverInstance(driver));
        setBrowserSize(1366, 768);
    }

    public static void closeBrowser() {
        if (driverInstance.get() != null) {
            getWebDriver().quit();
            driverInstance.set(null);
        }
    }

    public static void clearCookies() {
        getWebDriver().manage().deleteAllCookies();
    }

    public static void setBrowserSize(Integer width, Integer height) {
        getWebDriver().manage().window().setSize(new Dimension(width, height));
    }
}
