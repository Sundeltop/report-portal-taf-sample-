package com.epam.extensions.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum BrowserFactory {

    CHROME(ChromeDriver.class),
    FIREFOX(FirefoxDriver.class),
    EDGE(EdgeDriver.class);

    private final Class<? extends WebDriver> driver;

    BrowserFactory(Class<? extends WebDriver> driver) {
        this.driver = driver;
    }

    @SneakyThrows
    public WebDriver createDriver() {
        WebDriverManager.getInstance(driver).setup();
        return driver.getDeclaredConstructor().newInstance();
    }

}
