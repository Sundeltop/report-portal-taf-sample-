package com.epam.ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

@Log4j2
public abstract class BasePage {

    public BasePage() {
        if (!webdriver().driver().hasWebDriverStarted()) {
            openPage(getURL());
        }
    }

    @Step("Open page {0}")
    private void openPage(String url) {
        open(url);
        log.info("Open URL {}", url);
    }

    protected abstract String getURL();
}
