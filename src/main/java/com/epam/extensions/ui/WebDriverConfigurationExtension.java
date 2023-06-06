package com.epam.extensions.ui;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.epam.ui.webdriver.WebDriverFactory.createDriverInstance;
import static com.epam.ui.webdriver.WebDriverStorage.closeBrowser;
import static com.epam.ui.webdriver.WebDriverStorage.setWebDriver;

@Log4j2
public class WebDriverConfigurationExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        setWebDriver(createDriverInstance(System.getProperty("browser", "chrome")));
        log.info("Create WebDriver instance");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        closeBrowser();
        log.info("Close browser");
    }
}
