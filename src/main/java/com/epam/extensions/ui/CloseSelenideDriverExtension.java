package com.epam.extensions.ui;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class CloseSelenideDriverExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        if (hasWebDriverStarted()) {
            closeWebDriver();
        }
    }
}
