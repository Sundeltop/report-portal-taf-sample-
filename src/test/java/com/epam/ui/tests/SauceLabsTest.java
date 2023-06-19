package com.epam.ui.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Configuration.remote;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.assertj.core.api.Assertions.assertThat;

public class SauceLabsTest {

    @Test
    void openSauceLabsPage() {
        remote = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
        browserCapabilities = getSauceLabsCapabilities();

        open("https://saucelabs.com/");
        assertThat(title())
                .isEqualTo("Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing");
    }

    private static ChromeOptions getSauceLabsCapabilities() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPlatformName("Windows 11");
        chromeOptions.setBrowserVersion("latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oleksandr_nelipa");
        sauceOptions.put("accessKey", "a9d487e9-f333-4edc-9ef8-6d2a17bd923d");
        sauceOptions.put("build", "selenium-build-48N0Q");
        sauceOptions.put("name", "test");

        chromeOptions.setCapability("sauce:options", sauceOptions);

        return chromeOptions;
    }
}
