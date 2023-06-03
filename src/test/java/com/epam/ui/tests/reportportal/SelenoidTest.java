package com.epam.ui.tests.reportportal;

import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.epam.ui.webdriver.WebDriverStorage.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenoidTest extends BaseUiTest {

    @Test
    public void checkReportPortalPageIsOpened() {
        WebDriver driver = getWebDriver();
        driver.get("https://reportportal.io/");
        assertThat(driver.getTitle()).isEqualTo("ReportPortal test automation analytics platform and real-time reporting, powered by Machine Learning");
    }
}
