package com.epam.ui.tests;

import com.epam.api.RestAssuredLoggingFilter;
import com.epam.api.RestWrapper;
import com.epam.extensions.ui.ScreenshotExtension;
import com.epam.extensions.ui.WebDriverConfigurationExtension;
import com.epam.ui.pages.reportportal.LoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.epam.config.ConfigurationManager.configuration;
import static com.epam.ui.webdriver.WebDriverStorage.getWebDriver;
import static io.restassured.RestAssured.filters;

@ExtendWith({
        WebDriverConfigurationExtension.class,
        ScreenshotExtension.class
})
public class BaseUiTest {

    protected static RestWrapper api;

    @BeforeAll
    static void prepareApiClient() {
        filters(new RestAssuredLoggingFilter());
        api = new RestWrapper();
    }

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
