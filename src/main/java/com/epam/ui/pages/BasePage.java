package com.epam.ui.pages;

import com.epam.config.ConfigurationManager;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Log4j2
public abstract class BasePage {

    public BasePage() {
        if (isFalse(webdriver().driver().hasWebDriverStarted())) {
            String url = String.format("%s/ui/#login", ConfigurationManager.configuration().baseUrl());
            openPage(url);
        }
    }

    @Step("Open page {0}")
    private void openPage(String url) {
        open(url);
        log.info("Open URL {}", url);
    }
}
