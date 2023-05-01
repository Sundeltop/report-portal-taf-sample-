//package com.epam.extensions.ui;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
//import org.junit.jupiter.api.extension.BeforeAllCallback;
//import org.junit.jupiter.api.extension.ExtensionContext;
//
//import static com.epam.config.ConfigurationManager.configuration;
//
//public class SelenideConfigurationExtension implements BeforeAllCallback {
//
//    @Override
//    public void beforeAll(ExtensionContext extensionContext) {
//        Configuration.baseUrl = configuration().baseUrl();
//        SelenideLogger.addListener("AllureSelenide", allureSelenide());
//    }
//
//    private AllureSelenide allureSelenide() {
//        return new AllureSelenide()
//                .screenshots(true)
//                .savePageSource(true);
//    }
//}
