package com.epam.ui.webdriver;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

@Getter
public class WebDriverInstance {

    private final WebDriver driver;
    private final Actions actions;
    private final JavascriptExecutor jsExecutor;

    public WebDriverInstance(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }
}
