package com.epam.extensions.ui;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.epam.ui.webdriver.WebDriverStorage.getWebDriver;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.openqa.selenium.OutputType.FILE;

@Log4j2
public class ScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isPresent()) {
            captureScreenshot(extensionContext.getRequiredTestMethod().getName());
        }
    }

    private void captureScreenshot(String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot) getWebDriver();
        try {
            String filePath = String.format("build/reports/screenshots/%s.png", fileName);
            copyFile(screenshot.getScreenshotAs(FILE), new File(filePath));
            log.info("Captured screenshot: {}", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Not able to capture screenshot: {}", e.getMessage());
        }
    }
}
