package com.epam.extensions.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public enum BrowserFactory {

    CHROME(ChromeDriver.class) {
        @Override
        public ChromeOptions getDriverOptions() {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-notifications");

            return chromeOptions;
        }
    },

    FIREFOX(FirefoxDriver.class) {
        @Override
        public FirefoxOptions getDriverOptions() {
            return new FirefoxOptions();
        }
    },

    EDGE(EdgeDriver.class) {
        @Override
        public EdgeOptions getDriverOptions() {
            return new EdgeOptions();
        }
    };

    private final Class<? extends WebDriver> driverClass;

    BrowserFactory(Class<? extends WebDriver> driverClass) {
        this.driverClass = driverClass;
    }

    public abstract AbstractDriverOptions<?> getDriverOptions();

    @SneakyThrows
    public WebDriver createDriver() {
        WebDriverManager.getInstance(driverClass).setup();

        WebDriver driver = driverClass.getDeclaredConstructor().newInstance();
        driver.manage().window().setSize(new Dimension(1366, 768));

        return driver;
    }

}
