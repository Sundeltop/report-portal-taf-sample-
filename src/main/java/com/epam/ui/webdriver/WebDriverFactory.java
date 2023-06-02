package com.epam.ui.webdriver;

import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class WebDriverFactory {

    private WebDriverFactory() throws IllegalAccessException {
        throw new IllegalAccessException("Can't create object of static class");
    }

    public static WebDriver createDriverInstance(String browser) {
        browser = browser == null ? "chrome" : browser;
        BrowserFactory browserFactory = BrowserFactory.valueOf(browser.toUpperCase());

        if ("remote".equals(System.getProperty("target"))) {
            return createRemoteDriver(browserFactory.getDriverOptions());
        } else {
            return browserFactory.createDriver();
        }
    }

    @SneakyThrows
    private static WebDriver createRemoteDriver(MutableCapabilities capabilities) {
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), addSelenoidCapabilities(capabilities));
    }

    private static Capabilities addSelenoidCapabilities(MutableCapabilities capabilities) {
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        return capabilities;
    }
}
