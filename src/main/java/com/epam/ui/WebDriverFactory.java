package com.epam.ui;

import com.epam.extensions.ui.BrowserFactory;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class WebDriverFactory {

    private WebDriverFactory() throws IllegalAccessException {
        throw new IllegalAccessException("Can't create object of static class");
    }

    public static WebDriver createDriverInstance(String browser) {
        if ("remote".equals(System.getProperty("target"))) {
            return createRemoteDriverInstance();
        } else {
            if (browser == null) {
                browser = "chrome";
            }
            return BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        }
    }

    @SneakyThrows
    private static WebDriver createRemoteDriverInstance() {
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getSelenoidCapabilities());
    }

    private static Capabilities getSelenoidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        return capabilities;
    }
}
