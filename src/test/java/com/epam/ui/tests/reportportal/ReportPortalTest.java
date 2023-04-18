package com.epam.ui.tests.reportportal;

import com.epam.ui.pages.reportportal.HomePage;
import com.epam.ui.tests.BaseUiTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

public class ReportPortalTest extends BaseUiTest {

    @Test
    void checkCanOpenReportPortal() {
        page(HomePage.class)
                .isHeaderDisplayedWithText("AI-powered Test Automation Dashboard");
    }
}
