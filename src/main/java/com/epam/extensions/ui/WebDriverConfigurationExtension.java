package com.epam.extensions.ui;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.epam.ui.WebDriverStorage.closeBrowser;
import static com.epam.ui.WebDriverStorage.setWebDriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

@Log4j2
public class WebDriverConfigurationExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        chromedriver().setup();
        log.info("Setup WebDriver binary");
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        setWebDriver(new ChromeDriver());
        log.info("Create WebDriver instance");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        closeBrowser();
        log.info("Close browser");
    }
}
