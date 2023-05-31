package com.epam.ui;

import org.openqa.selenium.WebDriver;

public class WebDriverStorage {

    private static final ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    private WebDriverStorage() throws IllegalAccessException {
        throw new IllegalAccessException("Can't create object of static class");
    }

    public static WebDriver getWebDriver() {
        return driverInstance.get();
    }

    public static void setWebDriver(WebDriver driver) {
        driverInstance.set(driver);
    }

    public static void closeBrowser() {
        getWebDriver().quit();
        driverInstance.set(null);
    }

    public static void clearCookies() {
        getWebDriver().manage().deleteAllCookies();
    }
}
