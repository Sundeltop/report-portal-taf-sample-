package com.epam.ui.tests;

import com.epam.extensions.ui.ScreenshotExtension;
import com.epam.extensions.ui.WebDriverConfigurationExtension;
import com.epam.ui.pages.reportportal.LoginPage;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.epam.config.ConfigurationManager.configuration;
import static com.epam.ui.webdriver.WebDriverStorage.getWebDriver;

@ExtendWith({
        WebDriverConfigurationExtension.class,
        ScreenshotExtension.class
})
public class BaseUiTest {

    protected LoginPage openPage() {
        getWebDriver().get(configuration().baseUrl());
        return new LoginPage();
    }

    protected LoginPage openPageAndLoginAsDefaultUser() {
        LoginPage loginPage = openPage();

        loginPage
                .loginAsDefaultUser()
                .isLoginNotificationDisplayedWithText("Signed in successfully");

        return loginPage;
    }
}
